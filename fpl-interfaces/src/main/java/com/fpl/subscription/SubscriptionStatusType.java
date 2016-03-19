package com.fpl.subscription;

public enum SubscriptionStatusType {
	    IP("IN Progress"),  
	    AC("Active"),
	    IN("In Active"),
	    CI("Cheque Payment in Progress"),
   	    AI("Admin Inactivated")

	    ; 


	    private final String status;

	    SubscriptionStatusType(String status) {
	        this.status = status;
	    }
	    
	    public String getStatus() {
	        return this.status;
	    }
	    
	}

