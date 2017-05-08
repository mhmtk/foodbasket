package com.mhmt.foodbasket.ui.login;

public class LoggedIn {

  private AccessToken accessToken;

  public LoggedIn(final AccessToken accessToken) {

    this.accessToken = accessToken;
  }

  public AccessToken getAccessToken() {
    return accessToken;
  }
}
