package com.mhmt.foodbasket.ui;

import com.mhmt.foodbasket.AppModule;
import com.mhmt.foodbasket.ui.personlist.PersonViewHolder;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = { AppModule.class })
public interface ViewHolderComponent {

  void inject(PersonViewHolder personViewHolder);
}
