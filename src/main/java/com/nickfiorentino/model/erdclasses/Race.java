package com.nickfiorentino.model.erdclasses;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class Race {


    @NotNull
    private int raceId;
    @NotEmpty
    private String raceName;


    public Race(int raceId, String raceName) {
        this.raceId = raceId;
        this.raceName = raceName;
    }

    // constructor needed for MapRow
    public Race() {

    }


    public int getRaceId() {
        return raceId;
    }

    public void setRaceId(int raceId) {
        this.raceId = raceId;
    }

    public String getRaceName() {
        return raceName;
    }

    public void setRaceName(String raceName) {
        this.raceName = raceName;
    }

}
