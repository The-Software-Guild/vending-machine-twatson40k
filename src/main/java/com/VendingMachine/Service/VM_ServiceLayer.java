package com.VendingMachine.Service;
import com.VendingMachine.DAO.VM_PersistenceException;
import com.VendingMachine.DTO.Item;
import java.util.List;

public interface VM_ServiceLayer {
    void addItem(Item item) throws VM_DuplicateIdException,
            VM_DataValidationException,
            VM_PersistenceException;

    List<Item> getAllItems() throws VM_PersistenceException;

    Item buyItem(String itemId) throws VM_PersistenceException;

    Item removeItem(String itemId) throws VM_PersistenceException;

}
