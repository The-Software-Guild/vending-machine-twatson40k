package com.VendingMachine.UI;

import com.VendingMachine.DTO.Item;

import java.math.BigDecimal;
import java.util.*;

public class VM_View {

    private final UserIO io;

    public VM_View(UserIO io){
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. List Items");
        io.print("2. Insert Money");
        io.print("3. Purchase Item");
        io.print("4. Display Balance");
        io.print("5. Restock Item");
        io.print("6. Create New Item");
        io.print("7. Remove a Item");
        io.print("8. Exit");

        return io.readInt("Please select from the above choices.", 1, 8);
    }

    public Item getNewItemInfo() {
        String itemId = io.readString("Please enter Item ID");
        String itemName = io.readString("Please enter Item Name");
        String priceTag = io.readString("Please enter Price Tag(£)");
        int noOfItem = io.readInt("Please enter item amount");
        Item currentItem = new Item(itemId);
        currentItem.setItemName(itemName);
        currentItem.setPriceTag(new BigDecimal(priceTag));
        currentItem.setNoOfItem(noOfItem);
        return currentItem;
    }
    public void displayCreateItemBanner() {
        io.print("=== Create Item ===");
    }

    public void displayCreateSuccessBanner() {
        io.readString("Item successfully created.  Please hit enter to continue");
    }

    public void displayItemList(List<Item> itemList) {
        for (Item currentItem : itemList) {
            String studentInfo = String.format("%s | %s | £ %s | %s",
                    currentItem.getItemId(),
                    currentItem.getItemName(),
                    currentItem.getPriceTag(),
                    currentItem.getNoOfItem());
            io.print(studentInfo);
        }
        io.readString("Please hit enter to continue.");
    }
    //public void displayDisplayAllBanner() {io.print("=== Display All Items ===");}

    //public void displayDisplayItemBanner () {io.print("=== Display Item ===");}

    public String getItemIdChoice() {
        return io.readString("Please enter the Item ID.");
    }

    public void displayItem(String item) {
       /* if (item != null) {
            io.print("ID: " + item. + " || Item Name: " + item.getItemName() +
                   " || £" + item.getPriceTag() + " || Amount: " + item.getNoOfItem());
            io.print("");
        } else {
            io.print("No such item.");
        }*/
       System.out.println(item);
    }

    public String getMoney(){return io.readString("=== Insert Money amount ===");}
    public void displayMoneySuccess(BigDecimal coinBalance){
        if(coinBalance != null){
            io.print("=== " + coinBalance + " Was Added ===");}
        else{
            io.print("Unsuccessfully.");
        }
        io.readString("Please hit enter to continue.");
    }
        //io.print("=== Money successfully Added.  Please hit enter to continue===");}
        public void displayRestockItemBanner() {
            io.print("=== Restock Item ===");
        }

    public void displayRemoveItemBanner () {
        io.print("=== Remove Item ===");
    }

    //public void displayRemoveSuccessBanner() {io.readString("Item successfully removed.  Please hit enter to continue");}
    public void displayRemoveResult() {
        Item itemRecord = null;
        if(itemRecord != null){
            io.print("Item successfully removed.");
        }else{
            io.print("No such Item.");
        }
        io.readString("Please hit enter to continue.");
    }
    public void displayChange(BigDecimal sessionBalance){
        io.readString("Your current change is £" + sessionBalance + " Please hit enter to continue.");
    }

    public void displayExitBanner(BigDecimal sessionBalance) {
        io.print("Your overall change is " + sessionBalance);
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }
    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }

}