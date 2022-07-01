package com.VendingMachine.Service;

import com.VendingMachine.DAO.VM_AuditDAO;
import com.VendingMachine.DAO.VM_DAO;
import com.VendingMachine.DAO.VM_PersistenceException;
import com.VendingMachine.DTO.CoinMath;
import com.VendingMachine.DTO.Item;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

public class VM_ServiceLayerImpl implements VM_ServiceLayer {
    private final VM_DAO dao;
    private final VM_AuditDAO auditDao;
    private BigDecimal sessionBalance;

    public BigDecimal getSessionBalance() {
        return sessionBalance;
    }

    public BigDecimal setSessionBalance(BigDecimal sessionBalance) {
        this.sessionBalance = this.sessionBalance.add(sessionBalance);
        return this.sessionBalance;
    }

    public VM_ServiceLayerImpl(VM_DAO dao, VM_AuditDAO auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
        sessionBalance = new BigDecimal("0.00").setScale(2, RoundingMode.HALF_UP);
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
    public String buyItem(String itemId) throws VM_PersistenceException, VM_DuplicateIdException,
    VM_DataValidationException{
        Item item = dao.buyItem(itemId);
        String change;
        if (item == null) {
            throw new VM_DuplicateIdException("ERROR: Item " + itemId + " not found");
        }
        if (item.getNoOfItem() == 0){
            auditDao.writeAuditEntry("NO STOCK:" + LocalDate.now() +  " " + item.getItemId() + " "
                    + item.getItemName() + " Stock Available " + item.getNoOfItem());
            throw new VM_DataValidationException("No Stock in " + itemId
                    + " Please resupply item");
        }
        if (item.getPriceTag().compareTo(sessionBalance) > 0) {
            throw new VM_DataValidationException("Insufficient funds for item " + itemId
                    + " Please insert $" + (item.getPriceTag().subtract(sessionBalance)));

        } else {
            this.UpdateItemAmount(item);
            //if audi
            if(item.getNoOfItem() <= 2)
            {
                auditDao.writeAuditEntry("LOW STOCK:" + LocalDate.now() +  " " + item.getItemId() + " "
                        + item.getItemName() + " Stock Available " + item.getNoOfItem());
            }

            //return change as string to be displayed on screen and update session balance
            change = CoinMath.getChange(sessionBalance, item.getPriceTag());
            sessionBalance = sessionBalance.subtract(item.getPriceTag());


            auditDao.writeAuditEntry("SOLD:" + LocalDate.now() +  " " + item.getItemId() + " "
                    + item.getItemName() + " " + item.getPriceTag());
            System.out.println(change);
        }
        return change;
    }

    @Override
    public void UpdateItemAmount(Item item) throws VM_PersistenceException {
        dao.updateItemAmount(item);
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
