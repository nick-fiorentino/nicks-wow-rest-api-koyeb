package com.nickfiorentino.dao;

import com.nickfiorentino.model.erdclasses.PlayableClass;

import java.util.List;

public interface ClassDao {


    PlayableClass getClassById(int classId);

    PlayableClass getClassByName(String className);

    List<PlayableClass> getAllClasses();

    PlayableClass createClass(PlayableClass playableClass);

    int deleteClassById(int id);

    PlayableClass updateClass(PlayableClass playableClass);


}
