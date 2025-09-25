package com.nickfiorentino.dao;

import com.nickfiorentino.exception.DaoException;
import com.nickfiorentino.model.erdclasses.PlayableClass;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcClassDao implements ClassDao {

    private final String CLASS_SELECT = "SELECT class_id, class_name FROM classes";
    private final String SELECT_ALL_CLASSES = "SELECT * FROM classes";
    private JdbcTemplate jdbcTemplate;
    public JdbcClassDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public PlayableClass getClassById(int classId) {
        PlayableClass playableClass = null;
        String sql = CLASS_SELECT + " WHERE class_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, classId);
            if (results.next()) {
                playableClass = mapRowToClass(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return playableClass;
    }


    @Override
    public PlayableClass getClassByName(String className) {
        PlayableClass playableClass = null;
        String sql = CLASS_SELECT + " WHERE class_name = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, className);
            if (results.next()) {
                playableClass = mapRowToClass(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return playableClass;
    }


    @Override
    public List<PlayableClass> getAllClasses() {
        List<PlayableClass> playableClass = new ArrayList<>();
        String sql = SELECT_ALL_CLASSES;
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                playableClass.add(mapRowToClass(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return playableClass;
    }


    @Override
    public PlayableClass createClass(PlayableClass playableClass) {
        PlayableClass newClass = null;

        try {
            // Insert entry into the classes table
            String sql = "INSERT INTO classes (class_id, class_name) VALUES (?, ?)";
            jdbcTemplate.update(sql, playableClass.getClassId(), playableClass.getClassName());

            return new PlayableClass(playableClass.getClassId(), playableClass.getClassName());

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

    }


    @Override
    public int deleteClassById(int id) {
        int numberOfRows = 0;
        String sql = "DELETE FROM classes WHERE class_id = ?";
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
    public PlayableClass updateClass(PlayableClass playableClass) {
        PlayableClass updatedClass = null;
        String sql = "UPDATE classes SET class_name = ? WHERE id = ?";
        try {
            int rowsAffected = jdbcTemplate.update(sql, playableClass.getClassName(), playableClass.getClassId());
            if (rowsAffected == 0) {
                throw new DaoException("Zero rows affected, expected at least one");
            }
            updatedClass = getClassById(playableClass.getClassId());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return updatedClass;
    }


    private PlayableClass mapRowToClass(SqlRowSet rs) {
        PlayableClass pc = new PlayableClass();
        pc.setClassId(rs.getInt("class_id"));
        pc.setClassName(rs.getString("class_name"));
        return pc;
    }


}
