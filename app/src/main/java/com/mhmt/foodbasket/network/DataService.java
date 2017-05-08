package com.mhmt.foodbasket.network;

import com.mhmt.foodbasket.domain.Person;
import com.mhmt.foodbasket.domain.PersonDetail;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface DataService {

  @GET("/persons") Call<List<Person>> fetchPersons();

  @GET("person/{id}") Call<PersonDetail> fetchPersonDetail(@Path("id") int id);

  @PUT("person/{id}") Call<PersonDetail> updatePersonDetail(@Path("id") int id, @Body PersonDetail personDetail);
}
