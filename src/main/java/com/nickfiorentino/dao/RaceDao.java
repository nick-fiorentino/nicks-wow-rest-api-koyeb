package com.nickfiorentino.dao;

import com.nickfiorentino.model.erdclasses.Race;

import java.util.List;

public interface RaceDao {

    Race getRaceById(int raceId);

    Race getRaceByName(String raceName);

    List<Race> getAllRaces();

    Race createRace(Race race);

    int deleteRaceById(int id);

    Race updateRace(Race race);


}
