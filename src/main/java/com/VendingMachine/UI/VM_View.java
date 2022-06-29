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
        io.print("2. Purchase Item");
        io.print("3. Resupply Selected Item");
        io.print("4. Create New Item or Edit Current Item");
        io.print("5. Remove a Item");
        io.print("6. Exit");

        return io.readInt("Please select from the above choices.", 1, 6);
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
            String studentInfo = String.format("ID: %s | %s | £ %s | %s",
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

    public void displayItem(Item item) {
        if (item != null) {
            io.print("ID: " + item.getItemId() + " || Item Name: " + item.getItemName() +
                   " || £" + item.getPriceTag() + " || Amount: " + item.getNoOfItem());
            io.print("");
        } else {
            io.print("No such item.");
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayRemoveItemBanner () {
        io.print("=== Remove Student ===");
    }

    public void displayRemoveSuccessBanner() {
        io.readString("Item successfully remoted.  Please hit enter to continue");
    }
    public void displayRemoveResult(Item itemRecord) {
        if(itemRecord != null){
            io.print("Item successfully removed.");
        }else{
            io.print("No such Item.");
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayExitBanner() {
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