package com.VendingMachine.DAO;

public interface VM_AuditDAO {
    public void writeAuditEntry(String entry) throws VM_PersistenceException;
}
