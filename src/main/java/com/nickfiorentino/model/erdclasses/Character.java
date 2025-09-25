package com.nickfiorentino.model.erdclasses;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class Character {


    @NotNull
    private int characterId;
    @NotNull
    private int userId;
    @NotEmpty
    private String characterName;
    @NotNull
    private int raceId;
    @NotNull
    private int classId;


    // need this constructor for maprow on jdbc
    public Character() { }

    // Constructor without characterId
    public Character(int userId, String characterName, int raceId, int classId) {
        this.userId = userId;
        this.characterName = characterName;
        this.raceId = raceId;
        this.classId = classId;
    }

    // Full constructor if needed elsewhere
    public Character(int characterId, int userId, String characterName, int raceId, int classId) {
        this.characterId = characterId;
        this.userId = userId;
        this.characterName = characterName;
        this.raceId = raceId;
        this.classId = classId;
    }


//    @Override
//    public String toString() {
//        return "User{" +
//                "id=" + id +
//                ", username='" + username + '\'' +
//                ", hashedPassword='" + hashedPassword + '\'' +
//                ", role='" + role + '\'' +
//                '}';
//    }


    public int getCharacterId() {
        return characterId;
    }

    public void setCharacterId(int characterId) {
        this.characterId = characterId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

}
