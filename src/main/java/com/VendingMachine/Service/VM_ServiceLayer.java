package com.VendingMachine.Service;
import com.VendingMachine.DAO.VM_PersistenceException;
import com.VendingMachine.DTO.Item;

import java.math.BigDecimal;
import java.util.List;

public interface VM_ServiceLayer {
    void addItem(Item item) throws VM_DuplicateIdException,
            VM_DataValidationException,
            VM_PersistenceException;

    List<Item> getAllItems() throws VM_DuplicateIdException,
            VM_DataValidationException,
            VM_PersistenceException;

    String buyItem(String itemId) throws VM_PersistenceException, VM_DuplicateIdException, VM_DataValidationException;

    void UpdateItemAmount(Item item) throws VM_PersistenceException;

    Item removeItem(String itemId) throws VM_PersistenceException;

    BigDecimal setSessionBalance(BigDecimal bigDecimal);
    BigDecimal getSessionBalance();
}
