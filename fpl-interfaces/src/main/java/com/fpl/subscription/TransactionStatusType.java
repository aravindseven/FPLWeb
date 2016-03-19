package com.fpl.subscription;

public enum TransactionStatusType {
    RD("Request in draft mode"),  
    IF("Payment failed via instant options"),
    UC("User Cancelled Payment via instant options"),
    PR("Payment received"),
    CI("Cheque to be issued"),
    CC("Cheque Inward Collected"),
    CH("Cheque not Honored"),
    CA("Cancelled by Admin"),
    AI("Admin Inactivated")
    ; 


    private final String status;

    TransactionStatusType(String status) {
        this.status = status;
    }
    
    public String getStatus() {
        return this.status;
    }
    
}

