package com.nickfiorentino.service;

import com.nickfiorentino.model.mainpojoclasses.ClassIndexPojo;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;


/**
 * Service class responsible for retrieving all playable classes from
 * Blizzard's World of Warcraft (Classic) API.
 *
 * <p>This service connects to the <code>/data/wow/playable-class/index</code>
 * endpoint using a {@link RestClient} and retrieves the list of all official
 * playable classes, returning them as a {@link ClassIndexPojo} object.</p>
 *
 * <p>Authentication is handled via a bearer token provided by
 * {@link RestBlizzardAuthenticationService}.</p>
 *
 * <p>This class is typically used by controllers that need to fetch
 * class options for building or displaying WoW characters.</p>
 *
 * @author Nick
 * @see ClassIndexPojo
 * @see RestBlizzardAuthenticationService
 */
@Component
public class RestAllClassesService {


    private final RestBlizzardAuthenticationService blizzardAuthentication;

    // ENDPOINT that works in Postman
    // https://us.api.blizzard.com/data/wow/playable-class/index?namespace=static-classic-us&locale=en_US


    private final String API_BASE_URL = "https://us.api.blizzard.com/data/wow/playable-class";
    private final String ACCESS_TOKEN;
    private final RestClient restClient;


    /**
     * Constructs a new RestAllClassesService with the given authentication service.
     *
     * <p>Fetches an access token from the authentication service and sets up
     * the {@link RestClient} with the proper base URL and authorization headers
     * for future API calls.</p>
     *
     * @param blizzardAuthentication the service used to obtain the bearer token
     */
    public RestAllClassesService(RestBlizzardAuthenticationService blizzardAuthentication) {
        this.blizzardAuthentication = blizzardAuthentication;
        this.ACCESS_TOKEN = blizzardAuthentication.getStringBearerToken();

        this.restClient = RestClient.builder()
                .baseUrl(API_BASE_URL)
                .defaultHeaders(headers -> headers.setBearerAuth(ACCESS_TOKEN))
                .build();

    }



    /**
     * Retrieves a list of all playable classes from Blizzard's WoW Classic API.
     *
     * <p>Sends a GET request to the <code>/data/wow/playable-class/index</code>
     * endpoint and maps the response to a {@link ClassIndexPojo} object.</p>
     *
     * @return a {@link ClassIndexPojo} containing metadata for all available classes
     * @throws RestClientResponseException if the API returns an error (e.g., 404 or 500)
     */
    public ClassIndexPojo getAllClasses() throws RestClientResponseException {

        ClassIndexPojo classIndexPojo = restClient.get()
                .uri("/index?namespace=static-classic-us&locale=en_US")
                .retrieve()
                .body(ClassIndexPojo.class);

        return classIndexPojo;
    }




}
