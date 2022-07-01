package com.VendingMachine.Controller;

import com.VendingMachine.DAO.VM_DAO;
import com.VendingMachine.DAO.VM_PersistenceException;
import com.VendingMachine.DTO.Item;
import com.VendingMachine.Service.VM_DataValidationException;
import com.VendingMachine.Service.VM_DuplicateIdException;
import com.VendingMachine.Service.VM_ServiceLayer;
import com.VendingMachine.UI.UserIO;
import com.VendingMachine.UI.UserIOConsoleImpl;
import com.VendingMachine.UI.VM_View;

import java.math.BigDecimal;
import java.util.*;


public class VM_Controller {

    private final UserIO io = new UserIOConsoleImpl();
    private final VM_View view;
    private final VM_ServiceLayer service;
    public VM_Controller(VM_ServiceLayer service, VM_View view){
        this.service = service;
        this.view = view;
    }

        public void run() {
            boolean keepGoing = true;
            int menuSelection = 0;
            try {
                listItems();
            while (keepGoing) {


                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        listItems();
                        break;
                    case 2:
                        insertCoin();
                        break;
                    case 3:
                        purchaseItem();
                        break;
                    case 4:
                        displayBalance();
                        break;
                    case 5:
                        io.print("Restock Item");
                        break;
                    case 6:
                        createItem();
                        break;
                    case 7:
                        removeItem();
                        break;
                    case 8:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }

            }
            exitMessage();
        } catch (VM_PersistenceException | VM_DuplicateIdException | VM_DataValidationException e){
        view.displayErrorMessage(e.getMessage());
    }
    }
	
	private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }
    private void createItem() throws VM_PersistenceException {
        view.displayCreateItemBanner();
        Boolean hasErrors = false;
        do {
            Item currentItem = view.getNewItemInfo();
            try{
                service.addItem(currentItem);
                view.displayCreateSuccessBanner();
                hasErrors = false;
            } catch (VM_DuplicateIdException | VM_DataValidationException e){
                hasErrors = true;
                view.displayErrorMessage(e.getMessage());
            }
            } while (hasErrors);
        }
    private void listItems() throws VM_PersistenceException, VM_DuplicateIdException, VM_DataValidationException {
        //view.displayDisplayAllBanner();
        List<Item> itemList = service.getAllItems();
        view.displayItemList(itemList);
    }
    private void purchaseItem() throws VM_PersistenceException, VM_DuplicateIdException,
            VM_DataValidationException {
        String itemId = view.getItemIdChoice();
        String item = service.buyItem(itemId);
        view.displayItem(item);
    }
    private void removeItem() throws VM_PersistenceException {
        view.displayRemoveItemBanner();
        String itemId = view.getItemIdChoice();
        service.removeItem(itemId);
        view.displayRemoveResult();
    }

    private void displayBalance() throws VM_PersistenceException {
        view.displayChange(service.getSessionBalance());
    }

    //private void restockItem(){}

    private void insertCoin() throws VM_PersistenceException{
        String money = view.getMoney();
        try {
            BigDecimal coinBalance = service.setSessionBalance(new BigDecimal(money));
            view.displayMoneySuccess(coinBalance);
        } catch (Exception e) {
            view.displayErrorMessage("Invalid Input, please try again.");
        }
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner(service.getSessionBalance());
    }

}