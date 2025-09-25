package com.nickfiorentino.model.mainpojoclasses;

import com.nickfiorentino.model.extrapojoclassandraceclasses.*;
import com.nickfiorentino.model.extrapojoitemclasses.Media;

import java.util.List;

public class SpecificClassPojo {

    private Links links;
    private Integer id;
    private String name;
    private GenderName genderName;
    private PowerType powerType;
    private Media media;
    private PvpTalentSlots pvpTalentSlots;
    private List<RacePojo> racePojos;

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GenderName getGenderName() {
        return genderName;
    }

    public void setGenderName(GenderName genderName) {
        this.genderName = genderName;
    }

    public PowerType getPowerType() {
        return powerType;
    }

    public void setPowerType(PowerType powerType) {
        this.powerType = powerType;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public PvpTalentSlots getPvpTalentSlots() {
        return pvpTalentSlots;
    }

    public void setPvpTalentSlots(PvpTalentSlots pvpTalentSlots) {
        this.pvpTalentSlots = pvpTalentSlots;
    }

    public List<RacePojo> getPlayableRaces() {
        return racePojos;
    }

    public void setPlayableRaces(List<RacePojo> racePojos) {
        this.racePojos = racePojos;
    }

}
