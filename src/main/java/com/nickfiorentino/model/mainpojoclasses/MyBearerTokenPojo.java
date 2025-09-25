package com.nickfiorentino.model.mainpojoclasses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MyBearerTokenPojo {

    //JSON response from Blizzard’s OAuth API must match
    //in Postman you can see they use "access_token"
    @JsonProperty("access_token")
    private String accessToken;
    private String tokenType;
    private Integer expiresIn;
    private String sub;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

}
