package com.VendingMachine.DAO;

import com.VendingMachine.DTO.Item;
import java.util.*;


public interface VM_DAO {

    Item addItem(String ItemId, Item item) throws VM_PersistenceException;

    List<Item> getAllItems() throws VM_PersistenceException;

    Item buyItem(String ItemId) throws VM_PersistenceException;

    Item removeItem(String ItemId) throws VM_PersistenceException;

    Item updateItemAmount(String ItemId, Item item) throws VM_PersistenceException;
}
