package com.nickfiorentino.service;

import com.nickfiorentino.model.mainpojoclasses.RaceIndexPojo;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;



/**
 * Service class responsible for retrieving the full list of playable races
 * from Blizzard's World of Warcraft API using a bearer token for authentication.
 *
 * <p>This service uses a {@link RestClient} to make a GET request to the
 * <code>/playable-race/index</code> endpoint provided by Blizzard's public API.
 * It requires a valid bearer token, which is retrieved from the
 * {@link RestBlizzardAuthenticationService}.</p>
 *
 * <p>The returned data is mapped to a {@link RaceIndexPojo} model, which contains
 * the structure of the races available in the Classic version of the game.</p>
 *
 * <p>Used by controllers to provide race data for character creation or display purposes.</p>
 *
 * @author Nick
 * @see RestBlizzardAuthenticationService
 * @see RaceIndexPojo
 */
@Component
public class RestAllRacesService {

    // initialize RestBlizzardAuthenticationService so I can access the returned token String
    private final RestBlizzardAuthenticationService blizzardAuthentication;


    // ENDPOINT that works in Postman
    // https://us.api.blizzard.com/data/wow/playable-race/index?namespace=static-classic-us&locale=en_US

    private final String API_BASE_URL = "https://us.api.blizzard.com/data/wow/playable-race";
    private final String ACCESS_TOKEN;
    private final RestClient restClient;


    /**
     * Constructs a new RestAllRacesService using the provided authentication service.
     *
     * <p>This constructor obtains a bearer token from Blizzard and initializes
     * the {@link RestClient} with the appropriate base URL and authorization header.</p>
     *
     * @param blizzardAuthentication the service used to obtain the bearer token
     */
    public RestAllRacesService(RestBlizzardAuthenticationService blizzardAuthentication) {
        this.blizzardAuthentication = blizzardAuthentication;
        this.ACCESS_TOKEN = blizzardAuthentication.getStringBearerToken();

        this.restClient = RestClient.builder()
                .baseUrl(API_BASE_URL)
                .defaultHeaders(headers -> headers.setBearerAuth(ACCESS_TOKEN))
                .build();

    }


    /**
     * Sends a GET request to Blizzard's /playable-race/index endpoint to fetch
     * all available playable races for World of Warcraft (Classic version).
     *
     * <p>This method maps the API response into a {@link RaceIndexPojo} object
     * which contains a list of all races and their metadata.</p>
     *
     * @return a {@link RaceIndexPojo} object containing the full race index
     * @throws RestClientResponseException if the API request fails (e.g., 404 or 500 error)
     */
    public RaceIndexPojo getAllRaces() throws RestClientResponseException {

        RaceIndexPojo raceIndexPojo = restClient.get()
                .uri("/index?namespace=static-classic-us&locale=en_US")
                .retrieve()
                .body(RaceIndexPojo.class);

        return raceIndexPojo;
    }



}
