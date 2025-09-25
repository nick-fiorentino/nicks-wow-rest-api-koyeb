package com.nickfiorentino.dao;

import com.nickfiorentino.exception.DaoException;
import com.nickfiorentino.model.erdclasses.Character;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcCharacterDao implements CharacterDao {


    private final String CHARACTER_SELECT = "SELECT user_id, character_id, character_name, race_id, class_id FROM characters";
    private final String SELECT_ALL_CHARACTERS = "SELECT * FROM characters";
    private JdbcTemplate jdbcTemplate;

    public JdbcCharacterDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Character getCharacterById(int characterId) {
        Character character = null;
        String sql = CHARACTER_SELECT + " WHERE character_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, characterId);
            if (results.next()) {
                character = mapRowToCharacter(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return character;
    }


    @Override
    public Character getCharacterByName(String characterName) {
        Character character = null;
        String sql = CHARACTER_SELECT + " WHERE character_name = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, characterName);
            if (results.next()) {
                character = mapRowToCharacter(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return character;
    }


    @Override
    public List<Character> getAllCharacters() {
        List<Character> character = new ArrayList<>();
        String sql = SELECT_ALL_CHARACTERS;
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                character.add(mapRowToCharacter(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return character;
    }


    @Override
    public Character createCharacter(Character character, String username) {
        Character newCharacter = null;
        String sql = "INSERT INTO characters (user_id, character_name, race_id, class_id) VALUES ((SELECT user_id FROM users where username = ?), ?, ?, ?) RETURNING character_id";
        try {
            int characterId = jdbcTemplate.queryForObject(sql, int.class, username, character.getCharacterName(), character.getRaceId(), character.getClassId());
            newCharacter = getCharacterById(characterId);

            // Insert entry into the equipped_inventories table
            String inventorySql = "INSERT INTO equipped_inventories (character_id, item_1_id, item_2_id, item_3_id, item_4_id) VALUES (?, null, null, null, null)";
            jdbcTemplate.update(inventorySql, characterId);

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newCharacter;
    }


    @Override
    public int deleteCharacterById(int id) {
        int numberOfRows = 0;

        try {

            // must delete equipped inventory entry first because of foreign key constraint
            String characterSql = "DELETE FROM equipped_inventories WHERE character_id = ?";
            jdbcTemplate.update(characterSql, id);


            String sql = "DELETE FROM characters WHERE character_id = ?";
            numberOfRows = jdbcTemplate.update(sql, id);


        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return numberOfRows;
    }


    private Character mapRowToCharacter(SqlRowSet rs) {
        Character c = new Character();
        c.setCharacterId(rs.getInt("character_id"));
        c.setUserId(rs.getInt("user_id"));
        c.setCharacterName(rs.getString("character_name"));
        c.setRaceId(rs.getInt("race_id"));
        c.setClassId(rs.getInt("class_id"));
        return c;
    }


}
