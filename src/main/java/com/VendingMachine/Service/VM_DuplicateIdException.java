package com.VendingMachine.Service;

public class VM_DuplicateIdException extends Exception {
    public VM_DuplicateIdException(String message) {
        super(message);
    }
    public VM_DuplicateIdException(String message, Throwable cause) {
        super(message, cause);
    }
}
