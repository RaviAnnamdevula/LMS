package com.project.minor1.request;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TxnReturnRequest {
    private String studentContact;
    private String bookNo;
    private String txnId;
}
