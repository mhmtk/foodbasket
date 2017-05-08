package com.mhmt.foodbasket.ui.login;

import com.mhmt.foodbasket.Injector;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import retrofit2.Response;

public class LogIn implements Runnable {


  private final String userName;
  private final String password;

  //  @Inject protected transient Context context;
  //  @Inject protected transient AccountManager accountManager;
  @Inject protected transient LoginService loginService;
  @Inject protected transient EventBus eventBus;

  public LogIn(final String userName, final String password) {
    this.userName = userName;
    this.password = password;
    Injector.getNetworkJobComponent().inject(this);
  }

  @Override public void run() {
    try {
      final Response<AccessToken>
          response =
          loginService.login(userName, password).execute();
      if (!response.isSuccessful()) {
        eventBus.postSticky(new LoginFailed());
      } else {
        eventBus.postSticky(new LoggedIn(response.body()));
      }
    } catch (Throwable e) {
      eventBus.postSticky(new LoginFailed());
      e.printStackTrace();
    }
  }
}
