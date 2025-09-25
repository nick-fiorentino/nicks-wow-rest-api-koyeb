package com.nickfiorentino.service;

import com.nickfiorentino.model.mainpojoclasses.RaceIndexPojo;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

@Component
public class RestAllRacesService {

    // initialize RestBlizzardAuthenticationService so I can access the returned token String
    private final RestBlizzardAuthenticationService blizzardAuthentication;


    // ENDPOINT that works in Postman
    // https://us.api.blizzard.com/data/wow/playable-race/index?namespace=static-classic-us&locale=en_US

    private final String API_BASE_URL = "https://us.api.blizzard.com/data/wow/playable-race";
    private final String ACCESS_TOKEN;
    private final RestClient restClient;

    public RestAllRacesService(RestBlizzardAuthenticationService blizzardAuthentication) {
        this.blizzardAuthentication = blizzardAuthentication;
        this.ACCESS_TOKEN = blizzardAuthentication.getStringBearerToken();

        this.restClient = RestClient.builder()
                .baseUrl(API_BASE_URL)
                .defaultHeaders(headers -> headers.setBearerAuth(ACCESS_TOKEN))
                .build();

    }

    //throws RestClientResponseException tells me if there is
    // a 404 Not Found or 500 Server Error
    public RaceIndexPojo getAllRaces() throws RestClientResponseException {

        RaceIndexPojo raceIndexPojo = restClient.get()
                .uri("/index?namespace=static-classic-us&locale=en_US")
                .retrieve()
                .body(RaceIndexPojo.class);

        return raceIndexPojo;
    }


    // Convert the previously returned MyBearerTokenPojo object to a String
    // that can be used in other Rest Classes
  //  public String getStringBearerToken() throws RestClientResponseException {
 //       MyBearerTokenPojo stringBearerTokenPojo = getBearerToken();
  //      return stringBearerTokenPojo.getAccessToken();
 //   }


    // ignore comments below unless needed


    //  public static void main(String[] args) {
   //     RaceIndexPojo raceIndexPojo = new AllRacesDemo().getAllRaces();

        //for each loop iterates through each racePojo object in the List<racePojo> that's in RaceIndex
        //each object is temporarily stored in the variable racePojo
        //then the getName() method in racePojo is called to return the name to be printed
        //each iteration it prints the next race name
   //     for (RacePojo racePojo : raceIndexPojo.getRaces()) {
    //        System.out.println(racePojo.getName());
   //     }
  //  }


}
