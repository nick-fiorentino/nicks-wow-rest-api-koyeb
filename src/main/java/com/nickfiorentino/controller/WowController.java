package com.nickfiorentino.controller;

import com.nickfiorentino.dao.*;
import com.nickfiorentino.exception.DaoException;
import com.nickfiorentino.model.User;
import com.nickfiorentino.model.dto.CreateCharacterDto;
import com.nickfiorentino.model.erdclasses.*;
import com.nickfiorentino.model.erdclasses.Character;
import com.nickfiorentino.model.mainpojoclasses.ClassIndexPojo;
import com.nickfiorentino.model.mainpojoclasses.ItemPojo;
import com.nickfiorentino.model.mainpojoclasses.RaceIndexPojo;
import com.nickfiorentino.service.RestAllClassesService;
import com.nickfiorentino.service.RestAllItemsService;
import com.nickfiorentino.service.RestAllRacesService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.*;

@RestController
@RequestMapping("/nickswowapi")
public class WowController {


    //initialize Daos for use
    private CharacterDao characterDao;
    private RaceDao raceDao;
    private ClassDao classDao;
    //    private RaceClassDao raceClassDao;
    private ItemDao itemDao;
    private EquippedInventoryDao equippedInventoryDao;

    private UserDao userDao;


    // initialize the Rest Classes for use
    private final RestAllItemsService restAllItemsService;
    private final RestAllClassesService restAllClassesService;
    private final RestAllRacesService restAllRacesService;


    public WowController(CharacterDao characterDao, RaceDao raceDao, ClassDao classDao, ItemDao itemDao, EquippedInventoryDao equippedInventoryDao, UserDao userDao, RestAllItemsService restAllItemsService, RestAllClassesService restAllClassesService, RestAllRacesService restAllRacesService) {
        this.characterDao = characterDao;
        this.raceDao = raceDao;
        this.classDao = classDao;
        this.itemDao = itemDao;
        this.equippedInventoryDao = equippedInventoryDao;
        this.userDao = userDao;
        this.restAllItemsService = restAllItemsService;
        this.restAllClassesService = restAllClassesService;
        this.restAllRacesService = restAllRacesService;
    }


    @PreAuthorize("permitAll")
    @RequestMapping(path = "/officialraces", method = RequestMethod.GET)
    public RaceIndexPojo getAllOfficialRaces() {

        return restAllRacesService.getAllRaces();

    }

    @PreAuthorize("permitAll")
    @RequestMapping(path = "/officialclasses", method = RequestMethod.GET)
    public ClassIndexPojo getAllOfficialClasses() {

        return restAllClassesService.getAllClasses();

    }


    @PreAuthorize("permitAll")
    @RequestMapping(path = "/officialitems", method = RequestMethod.GET)
    public List<ItemPojo> getItemsInRange(int startId, int endId) {
        List<ItemPojo> itemList = new ArrayList<>();

        for (int itemId = startId; itemId <= endId; itemId++) {
            try {
                ItemPojo item = restAllItemsService.getItem(itemId);

                if (item != null && item.getName() != null) {
                    itemList.add(item);

                }
// Used ignored in the catch so it would continue to iterate over item numbers that return 404
            } catch (RestClientResponseException ignored) {

            }
        }

        return itemList;
    }


    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/characters", method = RequestMethod.POST)
    public Character createCharacter(@Valid @RequestBody CreateCharacterDto incomingCharacter, Principal principal) {


        User user = userDao.getUserByUsername(principal.getName());

        try {
            if (characterDao.getCharacterByName(incomingCharacter.getCharacterName()) != null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Character name already exists.");
            }

            return characterDao.createCharacter(new Character(user.getId(), incomingCharacter.getCharacterName(), incomingCharacter.getRaceId(), incomingCharacter.getClassId()), principal.getName());

        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DAO error - " + e.getMessage());
        }

    }


    @PreAuthorize("permitAll")
    @RequestMapping(path = "/characters", method = RequestMethod.GET)
    public List<Character> getAllCharacters() {

        return characterDao.getAllCharacters();

    }

    @PreAuthorize("permitAll")
    @RequestMapping(path = "/characters/{id}", method = RequestMethod.GET)
    public Map<String, Object> getSpecificCharacter(@PathVariable("id") int id) {

        try {
        Character character = characterDao.getCharacterById(id);


        if (character == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Character Id does not exist.");
        }

            // initializes the Class variable names for use in the LinkedHashMap
            Race race = raceDao.getRaceById(character.getRaceId());
            PlayableClass playableClass = classDao.getClassById(character.getClassId());

            // LinkedHashMap preserves the order while HashMap can display the order at random
            Map<String, Object> characterRaceClassMap = new LinkedHashMap<>();
            characterRaceClassMap.put("character", character);
            characterRaceClassMap.put("race", race);
            characterRaceClassMap.put("class", playableClass);

            return characterRaceClassMap;

        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DAO error - " + e.getMessage());
        }

    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/characters/{id}", method = RequestMethod.DELETE)
    public void deleteExistingCharacter(@PathVariable int id, Principal principal) {
        try {
            Character character = characterDao.getCharacterById(id);

            if(character == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Character Id does not exist.");
            }

            User user = userDao.getUserByUsername(principal.getName());

            if (user.getId() != character.getUserId()) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You do not have permission to delete this character.");
            }



            int charactersDeleted = characterDao.deleteCharacterById(id);

            if (charactersDeleted == 0) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to delete character.");
            }
        }
        catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DAO Error - " + e.getMessage());
        }
    }


    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/equippedinventory/{id}", method = RequestMethod.PUT)
    public EquippedInventory updateInventory(@Valid @RequestBody EquippedInventory incomingInventory, @PathVariable int id, Principal principal) {

        User user = userDao.getUserByUsername(principal.getName());
        Character character = characterDao.getCharacterById(id);


        if (character == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Character Id does not exist.");
        }

        if (character.getUserId() != user.getId()){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You do not own this character.");
        }


        try {

            return equippedInventoryDao.updateInventory(new EquippedInventory(incomingInventory.getCharacterId(), incomingInventory.getItem1Id(), incomingInventory.getItem2Id(), incomingInventory.getItem3Id(), incomingInventory.getItem4Id()));

        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DAO error - " + e.getMessage());
        }

    }



    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/equippedinventory/{id}", method = RequestMethod.GET)
    public Map<String, Object> getSpecificInventory(@PathVariable("id") int id) {

        try {
            EquippedInventory equippedInventory = equippedInventoryDao.getEquippedInventoryById(id);

            if (equippedInventory == null){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipped Inventory Id does not exist.");
            }

    // initializes the Class variable names for use in the LinkedHashMap
            Character character = characterDao.getCharacterById(equippedInventory.getCharacterId());
            Item item1 = itemDao.getItemById(equippedInventory.getItem1Id());
            Item item2 = itemDao.getItemById(equippedInventory.getItem2Id());
            Item item3 = itemDao.getItemById(equippedInventory.getItem3Id());
            Item item4 = itemDao.getItemById(equippedInventory.getItem4Id());


    // LinkedHashMap preserves the order while HashMap can display the order at random
            Map<String, Object> equippedItemsMap = new LinkedHashMap<>();
            equippedItemsMap.put("equipped inventory", equippedInventory);
            equippedItemsMap.put("character", character);
            equippedItemsMap.put("item 1", item1);
            equippedItemsMap.put("item 2", item2);
            equippedItemsMap.put("item 3", item3);
            equippedItemsMap.put("item 4", item4);

            return equippedItemsMap;


        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DAO error - " + e.getMessage());
        }

    }




    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/races", method = RequestMethod.POST)
    public Race createRace(@Valid @RequestBody Race incomingRace) {

        try {
            if (raceDao.getRaceByName(incomingRace.getRaceName()) != null && raceDao.getRaceById(incomingRace.getRaceId()) != null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Race name or Race Id already exists.");
            }

            return raceDao.createRace(new Race(incomingRace.getRaceId(), incomingRace.getRaceName()));

        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DAO error - " + e.getMessage());
        }

    }

    @PreAuthorize("permitAll")
    @RequestMapping(path = "/races", method = RequestMethod.GET)
    public List<Race> getAllRaces() {

        return raceDao.getAllRaces();

    }


    @PreAuthorize("permitAll")
    @RequestMapping(path = "/races/{id}", method = RequestMethod.GET)
    public Race getSpecificRace(@PathVariable("id") int id) {

        try {
            Race race = raceDao.getRaceById(id);

            if (race == null){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Race Id does not exist.");
            }


            return race;

        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DAO error - " + e.getMessage());
        }

    }


    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/classes", method = RequestMethod.POST)
    public PlayableClass createClass(@Valid @RequestBody PlayableClass incomingClass) {

        try {
            if (classDao.getClassByName(incomingClass.getClassName()) != null && classDao.getClassById(incomingClass.getClassId()) != null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Race name or Race Id already exists.");
            }

            return classDao.createClass(new PlayableClass(incomingClass.getClassId(), incomingClass.getClassName()));

        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DAO error - " + e.getMessage());
        }

    }


    @PreAuthorize("permitAll")
    @RequestMapping(path = "/classes", method = RequestMethod.GET)
    public List<PlayableClass> getAllClasses() {

        return classDao.getAllClasses();

    }



    @PreAuthorize("permitAll")
    @RequestMapping(path = "/classes/{id}", method = RequestMethod.GET)
    public PlayableClass getSpecificClass(@PathVariable("id") int id) {

        try {
            PlayableClass playableClass = classDao.getClassById(id);

            if (playableClass == null){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Character Id does not exist.");
            }


            return playableClass;

        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DAO error - " + e.getMessage());
        }

    }



    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/items", method = RequestMethod.POST)
    public Item createItem(@Valid @RequestBody Item item) {

        try {
            if (itemDao.getItemByName (item.getItemName()) != null && itemDao.getItemById(item.getItemId()) != null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Item name or Item Id already exists.");
            }

            return itemDao.createItem(new Item(item.getItemId(), item.getItemName()));

        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DAO error - " + e.getMessage());
        }

    }

    @PreAuthorize("permitAll")
    @RequestMapping(path = "/items", method = RequestMethod.GET)
    public List<Item> getAllItems() {

        return itemDao.getAllItems();

    }



    @PreAuthorize("permitAll")
    @RequestMapping(path = "/items/{id}", method = RequestMethod.GET)
    public Item getSpecificItem(@PathVariable("id") int id) {

        try {
            Item item = itemDao.getItemById(id);

            if (item == null){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item Id does not exist.");
            }


            return item;

        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DAO error - " + e.getMessage());
        }

    }


}

