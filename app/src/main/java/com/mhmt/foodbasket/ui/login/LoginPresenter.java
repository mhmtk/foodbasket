package com.mhmt.foodbasket.ui.login;

import android.text.TextUtils;

import com.mhmt.foodbasket.Injector;
import com.mhmt.foodbasket.R;
import com.mhmt.foodbasket.Session;
import com.mhmt.foodbasket.ui.base.BasePresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.concurrent.Executor;

import javax.inject.Inject;

public class LoginPresenter extends BasePresenter<LoginMvpView> implements LoginMvpPresenter<LoginMvpView> {

  @Inject protected EventBus eventBus;
  @Inject protected Executor executor;
  private boolean busy = false;

  public LoginPresenter() {
    Injector.getPresenterComponent().inject(this);
  }

  @Override public void onCreate(final LoginMvpView mvpView) {
    super.onCreate(mvpView);
  }

  @Override public void onResume() {
    eventBus.register(this);
  }

  @Override public void onPause() {
    eventBus.unregister(this);
  }

  @Override public void onDestroy() {
    super.onDestroy();
  }

  @Override public void loginButtonClicked(final String userName, final String password) {
    if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(password)) {
      getMvpView().showProgressDialog();
      executor.execute(new LogIn(userName, password));
    } else {
      getMvpView().displayError(R.string.credentials_error);
    }
  }

  @SuppressWarnings("UnusedDeclaration")
  @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
  public void loggedIn(final LoggedIn event) {
    eventBus.removeStickyEvent(event);
    saveTokenToAccount(event.getAccessToken());
    getMvpView().dismissProgressDialog();
    getMvpView().startListActivity();
  }

  /**
   * Normally I would prefer using account manager and saving this into an account,
   * but in this small app this should suffice
   */
  private void saveTokenToAccount(final AccessToken accessToken) {
    Session.TOKEN = accessToken.getToken();
  }

  @SuppressWarnings("UnusedDeclaration")
  @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
  public void loginFailed(final LoginFailed event) {
    eventBus.removeStickyEvent(event);
    getMvpView().dismissProgressDialog();
    getMvpView().displayError(R.string.login_failed);
  }
}
