package com.nickfiorentino.dao;

import com.nickfiorentino.model.erdclasses.Item;

import java.util.List;

public interface ItemDao {


    Item getItemById(int itemId);

    Item getItemByName(String itemName);

    List<Item> getAllItems();

    Item createItem(Item item);

    int deleteItemById(int id);

    Item updateItem(Item item);




}
