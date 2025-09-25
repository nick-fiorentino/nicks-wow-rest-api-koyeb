package com.nickfiorentino.model.erdclasses;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class PlayableClass {

    @NotNull
    private int classId;
    @NotEmpty
    private String className;


    public PlayableClass(int classId, String className) {
        this.classId = classId;
        this.className = className;
    }

    // constructor needed for MapRow
    public PlayableClass() {

    }


    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }


}
