package com.project.minor1.request;

import com.project.minor1.model.Book;
import com.project.minor1.model.Student;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TxnCreateRequest {

    private String studentContact;
    private String bookNo;
    private Integer amount;

}
