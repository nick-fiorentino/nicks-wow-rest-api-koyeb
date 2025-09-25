package com.nickfiorentino.service;

import com.nickfiorentino.model.mainpojoclasses.ItemPojo;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;



/**
 * Service class responsible for retrieving item data from Blizzard's
 * World of Warcraft (Classic) API.
 *
 * <p>This class uses a {@link RestClient} to send authorized requests to Blizzard's
 * <code>/data/wow/item/{itemId}</code> endpoint. The service returns item data
 * as a {@link ItemPojo} object.</p>
 *
 * <p>It retrieves and applies a bearer token from {@link RestBlizzardAuthenticationService}
 * for authenticated access.</p>
 *
 * <p>This service is typically used by controllers to fetch detailed
 * item data by ID when building or displaying a WoW character's inventory.</p>
 *
 * @author Nick
 * @see ItemPojo
 * @see RestBlizzardAuthenticationService
 */
@Component
public class RestAllItemsService {

    // initialize RestBlizzardAuthenticationService so I can access the returned token String
    private final RestBlizzardAuthenticationService blizzardAuthentication;

    // ENDPOINT that works in Postman
    // https://us.api.blizzard.com/data/wow/item/19019?namespace=static-classic-us&locale=en_US
    private final String API_BASE_URL = "https://us.api.blizzard.com/data/wow/item";
    private final String ACCESS_TOKEN;
    private final RestClient restClient;



    /**
     * Constructs a new RestAllItemsService with the given authentication service.
     *
     * <p>This constructor fetches a bearer token from Blizzard and initializes
     * the {@link RestClient} with the required base URL and authorization headers.</p>
     *
     * @param blizzardAuthentication the service used to obtain the bearer token
     */
    public RestAllItemsService(RestBlizzardAuthenticationService blizzardAuthentication) {
        this.blizzardAuthentication = blizzardAuthentication;
        this.ACCESS_TOKEN = blizzardAuthentication.getStringBearerToken();

        this.restClient = RestClient.builder()
                .baseUrl(API_BASE_URL)
                .defaultHeaders(headers -> headers.setBearerAuth(ACCESS_TOKEN))
                .build();

    }


    /**
     * Retrieves a single item from Blizzard's WoW API by its unique item ID.
     *
     * <p>The request is sent to the <code>/data/wow/item/{itemId}</code> endpoint,
     * and the response is mapped to a {@link ItemPojo}.</p>
     *
     * @param itemId the unique ID of the item to fetch
     * @return an {@link ItemPojo} representing the item's data
     * @throws RestClientResponseException if the API returns an error (e.g., 404 or 500)
     */
    public ItemPojo getItem(int itemId) throws RestClientResponseException {

        ItemPojo itemPojo = restClient.get()
                .uri("/" + itemId + "?namespace=static-classic-us&locale=en_US")
                .retrieve()
                .body(ItemPojo.class);

        return itemPojo;
    }



}
