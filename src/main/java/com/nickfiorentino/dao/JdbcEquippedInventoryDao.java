package com.nickfiorentino.dao;

import com.nickfiorentino.exception.DaoException;
import com.nickfiorentino.model.erdclasses.EquippedInventory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcEquippedInventoryDao implements EquippedInventoryDao {

    private final String EQUIPPED_INVENTORY_SELECT = "SELECT character_id, item_1_id, item_2_id, item_3_id, item_4_id FROM equipped_inventories";
    private JdbcTemplate jdbcTemplate;

    public JdbcEquippedInventoryDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public EquippedInventory getEquippedInventoryById(int characterId) {
        EquippedInventory equippedInventory = null;
        String sql = EQUIPPED_INVENTORY_SELECT + " WHERE character_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, characterId);
            if (results.next()) {
                equippedInventory = mapRowToInventory(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return equippedInventory;
    }



    @Override
    public EquippedInventory getInventoryUserIdByCharacterId(int characterId) {
        EquippedInventory equippedInventory = null;
        String sql = "SELECT user_id FROM equipped_inventories WHERE character_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, characterId);
            if (results.next()) {
                equippedInventory = mapRowToInventory(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return equippedInventory;
    }


    @Override
    public EquippedInventory updateInventory(EquippedInventory equippedInventory) {
        EquippedInventory updatedInventory = null;
        String sql = "UPDATE equipped_inventories SET item_1_id = ?, item_2_id = ?, item_3_id = ?, item_4_id = ? WHERE character_id = ?";

        try {
            int rowsAffected = jdbcTemplate.update(sql, equippedInventory.getItem1Id(), equippedInventory.getItem2Id(), equippedInventory.getItem3Id(), equippedInventory.getItem4Id(), equippedInventory.getCharacterId());
            if (rowsAffected == 0) {
                throw new DaoException("Zero rows affected, expected at least one");
            }
            updatedInventory = getEquippedInventoryById(equippedInventory.getCharacterId());


        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return updatedInventory;

        }


    private EquippedInventory mapRowToInventory (SqlRowSet rs){
        EquippedInventory e = new EquippedInventory();
        e.setCharacterId(rs.getInt("character_id"));
        e.setItem1Id(rs.getInt("item_1_id"));
        e.setItem2Id(rs.getInt("item_2_id"));
        e.setItem3Id(rs.getInt("item_3_id"));
        e.setItem4Id(rs.getInt("item_4_id"));
        return e;

    }

    }



