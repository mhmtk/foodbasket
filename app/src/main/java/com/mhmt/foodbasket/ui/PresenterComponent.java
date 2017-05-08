package com.mhmt.foodbasket.ui;

import com.mhmt.foodbasket.AppModule;
import com.mhmt.foodbasket.annotations.PerPresenter;
import com.mhmt.foodbasket.ui.persondetail.PersonDetailPresenter;
import com.mhmt.foodbasket.ui.personlist.PersonListPresenter;
import com.mhmt.foodbasket.ui.login.LoginPresenter;

import javax.inject.Singleton;

import dagger.Component;

@PerPresenter
@Singleton
@Component(modules = { AppModule.class })
public interface PresenterComponent {

  void inject(LoginPresenter presenter);

  void inject(PersonListPresenter presenter);

  void inject(PersonDetailPresenter personDetailPresenter);
}