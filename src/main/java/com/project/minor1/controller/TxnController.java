package com.project.minor1.controller;

import com.project.minor1.exception.TxnException;
import com.project.minor1.request.TxnCreateRequest;
import com.project.minor1.request.TxnReturnRequest;
import com.project.minor1.service.TxnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/txn")
public class TxnController {

    @Autowired
    private TxnService txnService;

    @PostMapping("/create")
    public String createTxn(@RequestBody TxnCreateRequest txnCreateRequest) throws TxnException {
        return txnService.createTxn(txnCreateRequest);
    }

    @PutMapping("/return")
    public Integer returnTxn(@RequestBody TxnReturnRequest txnReturnRequest) throws TxnException {
        return txnService.returnBook(txnReturnRequest);
    }
}
