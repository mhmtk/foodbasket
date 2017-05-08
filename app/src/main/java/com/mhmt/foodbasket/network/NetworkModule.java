package com.mhmt.foodbasket.network;

import android.content.Context;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mhmt.foodbasket.AppModule;
import com.mhmt.foodbasket.JsonObjectMapper;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ir.mirrajabi.okhttpjsonmock.interceptors.OkHttpMockInterceptor;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Module(includes = { AppModule.class })
public final class NetworkModule {

  private static final long TIMEOUT = 120L;
  private static final long WRITE_TIMEOUT = TIMEOUT;
  private static final long READ_TIMEOUT = TIMEOUT;
  private static final long CACHE_SIZE = 10L;

  private static final int NETWORK_CALL_FAIL_PERCENTAGE = 0;

  private static OkHttpClient OK_HTTP_CLIENT;
  private static Retrofit RETROFIT;

  @Singleton
  @Provides
  static OkHttpClient okHttp(final Context context, final RestRequestInterceptor requestInterceptor) {
    if (OK_HTTP_CLIENT == null) {
      final File cacheDir = context.getCacheDir();
      long cacheSize = CACHE_SIZE * 1024L * 1024L;
      final Interceptor loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC);
      final OkHttpClient.Builder builder = new OkHttpClient.Builder()
                                               .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                                               .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                                               .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                                               .cache(new Cache(cacheDir, cacheSize))
                                               .addInterceptor(new OkHttpMockInterceptor(context, NETWORK_CALL_FAIL_PERCENTAGE))
                                               .addInterceptor(loggingInterceptor)
                                               .addInterceptor(requestInterceptor);
      OK_HTTP_CLIENT = builder.build();
    }
    return OK_HTTP_CLIENT;
  }

  @Singleton
  @Provides
  static Retrofit retrofit(final OkHttpClient client, final ObjectMapper objectMapper) {
    if (RETROFIT == null) {
      RETROFIT = new Retrofit.Builder().baseUrl("https://staging.foodbasket.com")
                                       .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                                       .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                                       .client(client)
                                       .build();
    }
    return RETROFIT;
  }

  @Singleton
  @Provides
  static ObjectMapper objectMapper() {
    return new JsonObjectMapper();
  }
}

