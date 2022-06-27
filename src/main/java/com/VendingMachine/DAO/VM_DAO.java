public interface VM_DAO {
    /**
     * Adds the given Item to the roster and associates it with the given
     * Item id. If there is already a Item associated with the given
     * Item id it will return that Item object, otherwise it will
     * return null.
     *
     * @param ItemId id with which Item is to be associated
     * @param Item item to be added to the roster
     * @return the Item object previously associated with the given
     * Item id if it exists, null otherwise
     */
    Item addItem(String ItemId, Item item);

    /**
     * Returns a List of all Items in the roster.
     *
     * @return List containing all Items in the roster.
     */
    List<Item> getAllItems();

    /**
     * Returns the Item object associated with the given Item id.
     * Returns null if no such Item exists
     *
     * @param ItemId ID of the Item to retrieve
     * @return the Item object associated with the given Item id,
     * null if no such Item exists
     */
    Item purchaseItem(String ItemId);

    /**
     * Removes from the roster the Item associated with the given id.
     * Returns the Item object that is being removed or null if
     * there is no Item associated with the given id
     *
     * @param ItemId id of Item to be removed
     * @return Item object that was removed or null if no Item
     * was associated with the given Item id
     */
    Item removeItem(String ItemId);
}
