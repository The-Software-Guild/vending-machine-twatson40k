public class VM_View {

    private UserIO io = new UserIOConsoleImpl();

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
        String itemName = io.readString("Please enter First Name");
        int priceTag = io.readString("Please enter Price Tag(£)");
        String noOfItem = io.readString("Please enter Cohort");
        Item currentItem = new Item(itemId);
        currentItem.setItemName(itemName);
        currentItem.setPriceTag(priceTag);
        currentItem.setNoOfItem(noOfItem);
        return currentItem;
    }
}