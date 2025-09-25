package com.nickfiorentino.service;

import com.nickfiorentino.model.mainpojoclasses.ItemPojo;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

@Component
public class RestAllItemsService {

    // initialize RestBlizzardAuthenticationService so I can access the returned token String
    private final RestBlizzardAuthenticationService blizzardAuthentication;

    // ENDPOINT that works in Postman
    // https://us.api.blizzard.com/data/wow/item/19019?namespace=static-classic-us&locale=en_US
    private final String API_BASE_URL = "https://us.api.blizzard.com/data/wow/item";
    private final String ACCESS_TOKEN;
    private final RestClient restClient;

    public RestAllItemsService(RestBlizzardAuthenticationService blizzardAuthentication) {
        this.blizzardAuthentication = blizzardAuthentication;
        this.ACCESS_TOKEN = blizzardAuthentication.getStringBearerToken();

        this.restClient = RestClient.builder()
                .baseUrl(API_BASE_URL)
                .defaultHeaders(headers -> headers.setBearerAuth(ACCESS_TOKEN))
                .build();

    }

    //throws RestClientResponseException tells me if there is
    // a 404 Not Found or 500 Server Error
    public ItemPojo getItem(int itemId) throws RestClientResponseException {

        ItemPojo itemPojo = restClient.get()
                .uri("/" + itemId + "?namespace=static-classic-us&locale=en_US")
                .retrieve()
                .body(ItemPojo.class);

        return itemPojo;
    }


    // Convert the previously returned MyBearerTokenPojo object to a String
    // that can be used in other Rest Classes


     //   public String getStringBearerToken() throws RestClientResponseException {
     //   MyBearerTokenPojo stringBearerTokenPojo = getBearerToken();
    //    return stringBearerTokenPojo.getAccessToken();
   // }





    //-----------------experimentation below
    //    public List<ItemPojo> getAllItems() throws RestClientResponseException {
   //     int itemId;
    //    List<String> itemNames = new ArrayList<>();
   //
   //     for (itemId = 19002; itemId <= 19100; itemId++) {

            // Used try incase some ItemId numbers don't exist
    //        try {
    //            ItemPojo itemPojo = new RestIAllItemsService().getItem(itemId);

    //           if (itemPojo != null && itemPojo.getName() != null) {

    //                itemNames.add(itemPojo.getName());
   //             }
// Used ignored in the catch so it would continue to iterate over item numbers that return 404
    //        }catch (RestClientResponseException ignored) {

   //         }
    //    }
   //         return itemNames;
  //  }

}
