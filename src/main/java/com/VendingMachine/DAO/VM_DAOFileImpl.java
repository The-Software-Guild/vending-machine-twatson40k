public class VM_DAOFileImpl implements VM_DAO {
    private Map<String, Item> items = new HashMap<>();
    @Override
    public Item addItem(String itemId, Item item) {
        Item prevItem = items.put(itemId, item);
        return prevItem;
    }

    @Override
    public List<Item> getAllItems() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Item getItem(String itemId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Item removeItem(String itemId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
