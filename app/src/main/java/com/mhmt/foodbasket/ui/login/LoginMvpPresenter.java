package com.mhmt.foodbasket.ui.login;


import com.mhmt.foodbasket.ui.base.MvpPresenter;

public interface LoginMvpPresenter<V extends LoginMvpView> extends MvpPresenter<V> {

  void loginButtonClicked(final String userName, final String password);

}
