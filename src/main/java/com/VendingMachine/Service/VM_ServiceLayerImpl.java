package com.VendingMachine.Service;

import com.VendingMachine.DAO.VM_AuditDAO;
import com.VendingMachine.DAO.VM_DAO;
import com.VendingMachine.DAO.VM_PersistenceException;
import com.VendingMachine.DTO.Item;

import java.math.BigDecimal;
import java.util.List;

public class VM_ServiceLayerImpl implements VM_ServiceLayer {
    VM_DAO dao;
    private VM_AuditDAO auditDao;

    public VM_ServiceLayerImpl(VM_DAO dao) {
        this.dao = dao;
    }

    public VM_ServiceLayerImpl(VM_DAO dao, VM_AuditDAO auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    @Override
    public void addItem(Item item) throws
            VM_DuplicateIdException,
            VM_DataValidationException,
            VM_PersistenceException {
        if (dao.buyItem(item.getItemId()) != null) {
            throw new VM_DuplicateIdException(
                    "ERROR: Could not create item.  Item Id "
                            + item.getItemId()
                            + " already exists");
        }

        validateItemData(item);

        dao.addItem(item.getItemId(), item);
        auditDao.writeAuditEntry(
                "Item " + item.getItemId() + " CREATED.");
    }

    @Override
    public List<Item> getAllItems() throws VM_PersistenceException {
        return dao.getAllItems();
    }

    @Override
    public Item buyItem(String itemId) throws VM_PersistenceException {
        return dao.buyItem(itemId);
    }

    @Override
    public Item removeItem(String itemId) throws VM_PersistenceException {
        Item removedItem = dao.removeItem(itemId);
        auditDao.writeAuditEntry("Item " + itemId + " REMOVED.");
        return dao.removeItem(itemId);
    }


   private void validateItemData(Item item) throws VM_DataValidationException {

        if (item.getItemName() == null
                || item.getItemName().trim().length() == 0
                || item.getPriceTag() == null
                || item.getPriceTag().toString().trim().length() == 0
                || item.getNoOfItem() == 0
                || Integer.toString(item.getNoOfItem()).trim().length() == 0) {

            throw new VM_DataValidationException(
                    "ERROR: All fields [Item Name, Price Tag, No of Item] are required.");
        }
    }


}
