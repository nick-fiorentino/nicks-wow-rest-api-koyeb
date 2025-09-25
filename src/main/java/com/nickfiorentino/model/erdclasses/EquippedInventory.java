package com.nickfiorentino.model.erdclasses;

import jakarta.validation.constraints.NotNull;

public class EquippedInventory {

    @NotNull
    private int characterId;
    // Integer allows inserting Nulls into Postgres
    private Integer item1Id;
    private Integer item2Id;
    private Integer item3Id;
    private Integer item4Id;



//    @Override
//    public String toString() {
//        return "User{" +
//                "id=" + id +
//                ", username='" + username + '\'' +
//                ", hashedPassword='" + hashedPassword + '\'' +
//                ", role='" + role + '\'' +
//                '}';
//    }


    public EquippedInventory(int characterId, Integer item1Id, Integer item2Id, Integer item3Id, Integer item4Id) {
        this.characterId = characterId;
        this.item1Id = item1Id;
        this.item2Id = item2Id;
        this.item3Id = item3Id;
        this.item4Id = item4Id;
    }

    // need this constructor for maprow on jdbc
    public EquippedInventory() {

    }


    public int getCharacterId() {
        return characterId;
    }

    public void setCharacterId(int characterId) {
        this.characterId = characterId;
    }

    public Integer getItem1Id() {
        return item1Id;
    }

    public void setItem1Id(Integer item1Id) {
        this.item1Id = item1Id;
    }

    public Integer getItem2Id() {
        return item2Id;
    }

    public void setItem2Id(Integer item2Id) {
        this.item2Id = item2Id;
    }

    public Integer getItem3Id() {
        return item3Id;
    }

    public void setItem3Id(Integer item3Id) {
        this.item3Id = item3Id;
    }

    public Integer getItem4Id() {
        return item4Id;
    }

    public void setItem4Id(Integer item4Id) {
        this.item4Id = item4Id;
    }


}
