package com.nickfiorentino.dao;

import com.nickfiorentino.model.erdclasses.EquippedInventory;

public interface EquippedInventoryDao {


    EquippedInventory getEquippedInventoryById(int characterId);

    EquippedInventory getInventoryUserIdByCharacterId(int characterId);


    EquippedInventory updateInventory(EquippedInventory equippedInventory);


}
