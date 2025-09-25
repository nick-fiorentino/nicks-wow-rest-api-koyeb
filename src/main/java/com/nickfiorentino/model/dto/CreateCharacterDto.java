package com.nickfiorentino.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class CreateCharacterDto {



    @NotNull
    private int raceId;
    @NotNull
    private int classId;
    @NotEmpty
    private String characterName;



    public CreateCharacterDto(int raceId, int classId, String characterName) {
        this.raceId = raceId;
        this.classId = classId;
        this.characterName = characterName;
    }



    public int getRaceId() {
        return raceId;
    }

    public int getClassId() {
        return classId;
    }

    public String getCharacterName() {
        return characterName;
    }



    public void setRaceId(int raceId) {
        this.raceId = raceId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }


}
