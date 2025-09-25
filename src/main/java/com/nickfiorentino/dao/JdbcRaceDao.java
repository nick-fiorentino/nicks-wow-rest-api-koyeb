package com.nickfiorentino.dao;

import com.nickfiorentino.exception.DaoException;
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
public class JdbcRaceDao implements RaceDao {

    private final String RACE_SELECT = "SELECT race_id, race_name FROM races";

    private final String SELECT_ALL_RACES = "SELECT * FROM races";
    private JdbcTemplate jdbcTemplate;
    public JdbcRaceDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Race getRaceById(int raceId) {
        Race race = null;
        String sql = RACE_SELECT + " WHERE race_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, raceId);
            if (results.next()) {
                race = mapRowToRace(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return race;
    }


    @Override
    public Race getRaceByName(String raceName) {
        Race race = null;
        String sql = RACE_SELECT + " WHERE race_name = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, raceName);
            if (results.next()) {
                race = mapRowToRace(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return race;
    }

    @Override
    public List<Race> getAllRaces() {
        List<Race> race = new ArrayList<>();
        String sql = SELECT_ALL_RACES;
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                race.add(mapRowToRace(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return race;
    }

    @Override
    public Race createRace(Race race) {
        Race newRace = null;

        try {
            // Insert entry into the races table
            String sql = "INSERT INTO races (race_id, race_name) VALUES (?, ?)";
            jdbcTemplate.update(sql, race.getRaceId(), race.getRaceName());

            return new Race(race.getRaceId(), race.getRaceName());

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

    }


    @Override
    public int deleteRaceById(int id) {
        int numberOfRows = 0;
        String sql = "DELETE FROM races WHERE race_id = ?";
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
    public Race updateRace(Race race) {
        Race updatedRace = null;
        String sql = "UPDATE races SET race_name = ? WHERE id = ?";
        try {
            int rowsAffected = jdbcTemplate.update(sql, race.getRaceName(), race.getRaceId());
            if (rowsAffected == 0) {
                throw new DaoException("Zero rows affected, expected at least one");
            }
            updatedRace = getRaceById(race.getRaceId());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return updatedRace;
    }


    private Race mapRowToRace(SqlRowSet rs) {
        Race r = new Race();
        r.setRaceId(rs.getInt("race_id"));
        r.setRaceName(rs.getString("race_name"));
        return r;
    }


}
