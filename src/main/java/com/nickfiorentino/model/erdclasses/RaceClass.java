package com.nickfiorentino.model.erdclasses;

public class RaceClass {



    private int raceId;
    private int classId;



    public RaceClass(int raceId, int classId) {
        this.raceId = raceId;
        this.classId = classId;
    }



    public int getRaceId() {
        return raceId;
    }

    public void setRaceId(int raceId) {
        this.raceId = raceId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }


}
