package com.project.minor1.service;

import com.project.minor1.exception.TxnException;
import com.project.minor1.model.*;
import com.project.minor1.repository.StudentRepository;
import com.project.minor1.repository.TxnRepository;
import com.project.minor1.request.TxnCreateRequest;
import com.project.minor1.request.TxnReturnRequest;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class TxnService {

    @Autowired
    private TxnRepository txnRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private StudentService studentService;

    @Value("${student.valid.days}")
    private Integer validUpTo;

    @Value("${student.delayed.finePerDay}")
    private Integer finePerDay;


    private Student filterStudent(StudentFilterType type, Operator operator, String value) throws TxnException {
        //1) want to see if student is valid or not
        List<Student> studentList = studentService.filter(type, operator, value);
        if (studentList == null || studentList.isEmpty()) {
            throw new TxnException("Student doesn't not belong to my library");
        }
        return studentList.get(0);
    }

    private Book filterBook(FilterType type, Operator operator, String value) throws TxnException {
        List<Book> bookList = bookService.filter(type, operator, value);
        if (bookList == null || bookList.isEmpty()) {
            throw new TxnException("Book doesn't not belong to my library");
        }
        return bookList.get(0);
    }


    @Transactional
    public String createTxn(TxnCreateRequest txnCreateRequest) throws TxnException {
        //1) want to see if student is valid or not
        Student studentFromDb = filterStudent(StudentFilterType.CONTACT, Operator.EQUALS, txnCreateRequest.getStudentContact());

        Book bookFromLib = filterBook(FilterType.BOOK_NO, Operator.EQUALS, txnCreateRequest.getBookNo());

        if (bookFromLib.getStudent() != null) {
            throw new TxnException("Book has already assigned to someone else");
        }

        Txn txn = Txn.builder().student(studentFromDb).
                book(bookFromLib).
                txnId(UUID.randomUUID().toString()).
                paidAmount(txnCreateRequest.getAmount()).
                status(TxnStatus.ISSUED).
                build();

        txn = txnRepository.save(txn);

        bookFromLib.setStudent(studentFromDb);
        bookService.saveUpdate(bookFromLib);
        return txn.getTxnId();
    }
    private Integer calculateSettlementAmount(Txn txn){
        long issuedTime = txn.getCreatedOn().getTime();
        long returnTime = System.currentTimeMillis();
        long timeDiff = returnTime-issuedTime;
        int daysPassed = (int) TimeUnit.DAYS.convert(timeDiff ,TimeUnit.MILLISECONDS);

        if(daysPassed > validUpTo)  {
            int fineAmount = (daysPassed-validUpTo)*finePerDay;
            return txn.getPaidAmount()-fineAmount;
        }
        return txn.getPaidAmount();
    }

    @org.springframework.transaction.annotation.Transactional(rollbackFor = {TxnException.class})
    public Integer returnBook(TxnReturnRequest txnReturnRequest) throws TxnException {
        Student studentFromDb = filterStudent(StudentFilterType.CONTACT, Operator.EQUALS, txnReturnRequest.getStudentContact());
        Book bookFromLib = filterBook(FilterType.BOOK_NO, Operator.EQUALS, txnReturnRequest.getBookNo());

        if (bookFromLib.getStudent() != null && bookFromLib.getStudent().equals(studentFromDb)) {
           Txn txnFromDb = txnRepository.findByTxnId(txnReturnRequest.getTxnId());
           if(txnFromDb == null){
               throw  new TxnException("No Txn is found with this TxnId");
           }
           Integer amount = calculateSettlementAmount(txnFromDb);
           if(amount == txnFromDb.getPaidAmount()){
               txnFromDb.setStatus(TxnStatus.RETURNED);
           }else{
               txnFromDb.setStatus(TxnStatus.FINED);
           }
           txnFromDb.setPaidAmount(amount);

           //Update the book by marking Student Null
            bookFromLib.setStudent(null);
            bookService.saveUpdate(bookFromLib);
            return amount;
        } else {
            throw new TxnException("The book is either not assigned to anyone or assigned to someone else");
        }
    }
}
