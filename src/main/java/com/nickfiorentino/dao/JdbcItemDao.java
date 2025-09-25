package com.nickfiorentino.dao;

import com.nickfiorentino.exception.DaoException;
import com.nickfiorentino.model.erdclasses.Item;
import com.nickfiorentino.model.erdclasses.Race;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class JdbcItemDao implements ItemDao {


    private final String ITEM_SELECT = "SELECT item_id, item_name FROM items";
    private final String SELECT_ALL_ITEMS = "SELECT * FROM items";
    private JdbcTemplate jdbcTemplate;
    public JdbcItemDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Item getItemById(int itemId) {
        Item item = null;
        String sql = ITEM_SELECT + " WHERE item_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, itemId);
            if (results.next()) {
                item = mapRowToItem(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return item;
    }


    @Override
    public Item getItemByName(String itemName) {
        Item item = null;
        String sql = ITEM_SELECT + " WHERE item_name = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, itemName);
            if (results.next()) {
                item = mapRowToItem(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return item;
    }

    @Override
    public List<Item> getAllItems() {
        List<Item> item = new ArrayList<>();
        String sql = SELECT_ALL_ITEMS;
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                item.add (mapRowToItem(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return item;
    }

    @Override
    public Item createItem(Item item) {
        Race newRace = null;

        try {
            // Insert entry into the races table
            String sql = "INSERT INTO items (item_id, item_name) VALUES (?, ?)";
            jdbcTemplate.update(sql, item.getItemId(), item.getItemName());

            return new Item(item.getItemId(), item.getItemName());

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

    }


    @Override
    public int deleteItemById(int id) {
        int numberOfRows = 0;
        String sql = "DELETE FROM items WHERE item_id = ?";
        try {
            numberOfRows = jdbcTemplate.update(sql, id);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return numberOfRows;
    }


    @Override
    public Item updateItem(Item item) {
        Item updatedItem = null;
        String sql = "UPDATE items SET item_name = ? WHERE item_id = ?";
        try {
            int rowsAffected = jdbcTemplate.update(sql, item.getItemName(), item.getItemId());
            if (rowsAffected == 0) {
                throw new DaoException("Zero rows affected, expected at least one");
            }
            updatedItem = getItemById(item.getItemId());
            if (updatedItem == null) {
                throw new DaoException("Updated item not found, something went wrong");
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return updatedItem;
    }


    private Item mapRowToItem(SqlRowSet rs) {
        Item i = new Item();
        i.setItemId(rs.getInt("item_id"));
        i.setItemName(rs.getString("item_name"));
        return i;
    }


}
