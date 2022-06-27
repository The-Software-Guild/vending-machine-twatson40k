
public class Item {
    private String itemId;
    private String itemName;
    private String priceTag;
    private String noOfItem;

    public Item(String itemId) {
        this.itemId = itemId;
    }
	
	public String getItemID() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getPriceTag() {
        return priceTag;
    }

    public void setPriceTag(int priceTag) {
        this.priceTag = priceTag;
    }

    public String getNoOfItem() {
        return noOfItem;
    }

    public void setNoOfItem(String noOfItem) {
        this.noOfItem = noOfItem;
    }   
}