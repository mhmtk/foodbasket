package com.mhmt.foodbasket.ui.login;

public class AccessToken {

  private String userName;
  private String token;

  public AccessToken() {

  }

  public void setToken(final String token) {
    this.token = token;
  }

  public String getToken() {
    return token;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(final String userName) {
    this.userName = userName;
  }
}
