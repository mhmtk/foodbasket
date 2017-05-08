package com.mhmt.foodbasket.network;

import android.text.TextUtils;

import com.mhmt.foodbasket.Session;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public final class RestRequestInterceptor implements Interceptor {


  @Inject
  public RestRequestInterceptor() {

  }

  @Override public Response intercept(final Chain chain) throws IOException {
    final Request originalRequest = chain.request();
    final Request.Builder requestBuilder = originalRequest.newBuilder()
                                                          .header("Accept", "application/json; charset=UTF-8")
                                                          .method(originalRequest.method(), originalRequest.body());
    if (!TextUtils.isEmpty(Session.TOKEN)) {
      requestBuilder.header("Auth-token", String.format("Bearer %s", Session.TOKEN));
    }
    return chain.proceed(requestBuilder.build());
  }
}
