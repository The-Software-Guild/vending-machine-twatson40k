package com.VendingMachine.Service;

public class VM_DataValidationException extends Exception{
    public VM_DataValidationException(String message) {super(message);}
    public VM_DataValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
