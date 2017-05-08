package com.mhmt.foodbasket.network;

import com.mhmt.foodbasket.ui.login.LoginService;

import dagger.Module;
import dagger.Provides;
import dagger.Reusable;
import retrofit2.Retrofit;

@Module(includes = { NetworkModule.class })
public final class NetworkServiceModule {

  @Reusable
  @Provides static LoginService loginService(final Retrofit retrofit) {
    return retrofit.create(LoginService.class);
  }

  @Reusable
  @Provides static DataService dataService(final Retrofit retrofit) {
    return retrofit.create(DataService.class);
  }
}
