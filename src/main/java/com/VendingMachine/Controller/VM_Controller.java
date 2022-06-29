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

import java.util.*;


public class VM_Controller {

    private final UserIO io = new UserIOConsoleImpl();
    private final VM_View view;
    private final VM_DAO dao;
    private final VM_ServiceLayer service;
    public VM_Controller(VM_ServiceLayer service, VM_View view, VM_DAO dao){
        this.service = service;
        this.view = view;
        this.dao = dao;
    }


        public void run() {
            boolean keepGoing = true;
            int menuSelection = 0;
            try {
            while (keepGoing) {

                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        listItems();
                        break;
                    case 2:
                        purchaseItem();
                        break;
                    case 3:
                        io.print("RESUPPLY ITEM");
                        break;
                    case 4:
                        createItem();
                        break;
                    case 5:
                        removeItem();
                        break;
                    case 6:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }

            }
            exitMessage();
        } catch (VM_PersistenceException e){
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
                view.displayErrorMessage(e.getMessage());}
            } while (hasErrors);
        }
    private void listItems() throws VM_PersistenceException {
        //view.displayDisplayAllBanner();
        List<Item> itemList = dao.getAllItems();
        view.displayItemList(itemList);
    }
    private void purchaseItem() throws VM_PersistenceException {
        //view.displayDisplayItemBanner();
        String itemId = view.getItemIdChoice();
        Item item = dao.buyItem(itemId);
        view.displayItem(item);
    }
    private void removeItem() throws VM_PersistenceException {
        view.displayRemoveItemBanner();
        String itemId = view.getItemIdChoice();
        service.removeItem(itemId);
        view.displayRemoveSuccessBanner();
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

}