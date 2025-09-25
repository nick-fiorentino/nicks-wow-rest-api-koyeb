package com.nickfiorentino.service;

import com.nickfiorentino.model.mainpojoclasses.ClassIndexPojo;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

@Component
public class RestAllClassesService {


    private final RestBlizzardAuthenticationService blizzardAuthentication;

    // ENDPOINT that works in Postman
    // https://us.api.blizzard.com/data/wow/playable-class/index?namespace=static-classic-us&locale=en_US


    private final String API_BASE_URL = "https://us.api.blizzard.com/data/wow/playable-class";
    private final String ACCESS_TOKEN;
    private final RestClient restClient;

    public RestAllClassesService(RestBlizzardAuthenticationService blizzardAuthentication) {
        this.blizzardAuthentication = blizzardAuthentication;
        this.ACCESS_TOKEN = blizzardAuthentication.getStringBearerToken();

        this.restClient = RestClient.builder()
                .baseUrl(API_BASE_URL)
                .defaultHeaders(headers -> headers.setBearerAuth(ACCESS_TOKEN))
                .build();

    }



    //throws RestClientResponseException tells me if there is
    // a 404 Not Found or 500 Server Error
    public ClassIndexPojo getAllClasses() throws RestClientResponseException {

        ClassIndexPojo classIndexPojo = restClient.get()
                .uri("/index?namespace=static-classic-us&locale=en_US")
                .retrieve()
                .body(ClassIndexPojo.class);

        return classIndexPojo;
    }


    // Convert the previously returned MyBearerTokenPojo object to a String
    // that can be used in other Rest Classes
 //   public String getStringBearerToken() throws RestClientResponseException {
  //      MyBearerTokenPojo stringBearerTokenPojo = getBearerToken();
  //      return stringBearerTokenPojo.getAccessToken();
  //  }


    // ignore comments below unless needed


   // public static void main(String[] args) {
    //    ClassIndexPojo classIndexPojo = new AllClassesDemo().getAllClasses();

        //for each loop iterates through each OneClass object in the List<OneClass> that's in ClassIndex
        //each object is temporarily stored in the variable oneClass
        //then the getName() method in OneClass is called to return the name to be printed
        //each iteration it prints the next class name
     //   for (OneClassPojo oneClassPojo : classIndexPojo.getClasses()) {
     //       System.out.println(oneClassPojo.getName());
    //    }


 //   }

}
