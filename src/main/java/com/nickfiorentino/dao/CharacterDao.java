package com.nickfiorentino.dao;

import com.nickfiorentino.model.erdclasses.Character;

import java.util.List;

public interface CharacterDao {

  Character getCharacterById(int characterId);

  Character getCharacterByName(String characterName);

  List<Character> getAllCharacters();

  Character createCharacter(Character character, String username);

  int deleteCharacterById(int id);


}
