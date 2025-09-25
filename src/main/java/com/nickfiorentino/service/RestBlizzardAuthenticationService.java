package com.nickfiorentino.service;

import com.nickfiorentino.model.mainpojoclasses.MyBearerTokenPojo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;



/**
 * Service class responsible for authenticating with the Blizzard Battle.net OAuth server
 * and retrieving an access token required to make authorized requests to the World of Warcraft API.
 *
 * <p>This class uses the client credentials OAuth 2.0 flow to obtain a bearer token, which
 * is then used in all external API calls to Blizzard's servers (e.g., to retrieve official
 * WoW data like classes, races, and items).</p>
 *
 * <p>The bearer token is retrieved once per service lifecycle and reused across API calls.
 * This service is used internally by other service classes that need authenticated access to Blizzard's endpoints.</p>
 *
 * <p>Environment variables <code>BLIZZARD_CLIENT_ID</code> and <code>BLIZZARD_CLIENT_SECRET</code>
 * must be set for this service to function properly. These are injected using Spring's @Value annotation.</p>
 *
 * @author Nick
 * @see <a href="https://develop.battle.net/documentation/guides/using-oauth">Blizzard OAuth Guide</a>
 */
@Service
public class RestBlizzardAuthenticationService {

    private final String CLIENT_ID;
    private final String CLIENT_SECRET;
    private static final String ACCESS_TOKEN_URL = "https://oauth.battle.net/token";


    /**
     * Constructs a new instance of the authentication service using
     * environment variables for client credentials.
     *
     * @param CLIENT_ID the client ID provided by Blizzard
     * @param CLIENT_SECRET the client secret provided by Blizzard
     */
    public RestBlizzardAuthenticationService(
            @Value("${BLIZZARD_CLIENT_ID}") String CLIENT_ID,
            @Value("${BLIZZARD_CLIENT_SECRET}") String CLIENT_SECRET
    ) {
        this.CLIENT_ID = CLIENT_ID;
        this.CLIENT_SECRET = CLIENT_SECRET;
    }


    private RestClient restClient;

    /**
     * Initializes the RestClient with base URL and Basic Auth headers
     * using the Blizzard client ID and secret.
     *
     * <p>This method is called automatically after the class is instantiated,
     * thanks to the @PostConstruct annotation.</p>
     */
    @PostConstruct
    private void initRestClient() {
        this.restClient = RestClient.builder()
                .baseUrl(ACCESS_TOKEN_URL)
                .defaultHeaders(headers -> headers.setBasicAuth(CLIENT_ID, CLIENT_SECRET))
                .build();
    }


    /**
     * Retrieves a new bearer token from Blizzard's OAuth server using the
     * client credentials flow.
     *
     * <p>The returned token is needed to authenticate requests to Blizzard's
     * World of Warcraft API.</p>
     *
     * @return a {@link MyBearerTokenPojo} object containing the access token
     * @throws RestClientResponseException if the HTTP request fails (e.g., 401 or 500)
     */
    public MyBearerTokenPojo getBearerToken() throws RestClientResponseException {

        //https://datatracker.ietf.org/doc/html/rfc6749#section-1.3.4
        //Content-Type: application/x-www-form-urlencoded
        //https://develop.battle.net/documentation/guides/using-oauth/client-credentials-flow
        //grant_type=client_credentials

        MyBearerTokenPojo myBearerTokenPojo = restClient.post()
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                // request body must be sent as form data, not json
                .body("grant_type=client_credentials")
                // Blizzard expects body to contain a key value pair
                .retrieve()
                .body(MyBearerTokenPojo.class);

        return myBearerTokenPojo;

    }



    // Convert the previously returned MyBearerTokenPojo object to a String
    // that can be used in other Rest Classes

    /**
     * A helper method that returns the raw access token as a String.
     * This method internally calls {@link #getBearerToken()} and extracts the token.
     *
     * @return the bearer token as a plain String
     * @throws RestClientResponseException if token retrieval fails
     */
    public String getStringBearerToken() throws RestClientResponseException {
        MyBearerTokenPojo stringBearerTokenPojo = getBearerToken();
        return stringBearerTokenPojo.getAccessToken();
    }


}
