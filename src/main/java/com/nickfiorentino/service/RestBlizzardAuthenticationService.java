package com.nickfiorentino.service;

import com.nickfiorentino.model.mainpojoclasses.MyBearerTokenPojo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

@Service
public class RestBlizzardAuthenticationService {

    private final String CLIENT_ID;
    private final String CLIENT_SECRET;
    private static final String ACCESS_TOKEN_URL = "https://oauth.battle.net/token";


    public RestBlizzardAuthenticationService(
            @Value("${BLIZZARD_CLIENT_ID}") String CLIENT_ID,
            @Value("${BLIZZARD_CLIENT_SECRET}") String CLIENT_SECRET
    ) {
        this.CLIENT_ID = CLIENT_ID;
        this.CLIENT_SECRET = CLIENT_SECRET;
    }


    private RestClient restClient;

    @PostConstruct
    private void initRestClient() {
        this.restClient = RestClient.builder()
                .baseUrl(ACCESS_TOKEN_URL)
                .defaultHeaders(headers -> headers.setBasicAuth(CLIENT_ID, CLIENT_SECRET))
                .build();
    }


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
    public String getStringBearerToken() throws RestClientResponseException {
        MyBearerTokenPojo stringBearerTokenPojo = getBearerToken();
        return stringBearerTokenPojo.getAccessToken();
    }


}
