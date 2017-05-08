package com.mhmt.foodbasket.ui.login;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginService {

  String USERNAME = "username";
  String PASSWORD = "password";

  @FormUrlEncoded
  @POST("/login") Call<AccessToken> login(@Field(USERNAME) String username,
                                          @Field(PASSWORD) String password);

//  String CLIENT_ID = "client_id";
//  String CLIENT_SECRET = "client_secret";
//  String PASSWORD = "password";
//  String USERNAME = "username";
//  String GRANT_TYPE = "grant_type";
//  String SCOPE = "scope";
//
//  @FormUrlEncoded
//  @POST("/oauth/token") Call<OAuthToken> fetchToken(@Field(CLIENT_ID) String clientId,
//                                                    @Field(CLIENT_SECRET) String clientSecret,
//                                                    @Field(GRANT_TYPE) String grantType,
//                                                    @Field(USERNAME) String username,
//                                                    @Field(PASSWORD) String password);

}
