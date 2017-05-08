package com.mhmt.foodbasket.ui.personlist;

import com.mhmt.foodbasket.ui.base.MvpView;
import com.mhmt.foodbasket.domain.Person;

import java.util.List;

public interface PersonListMvpView extends MvpView {

  void initiateUI();

  void populateList(List<Person> persons);

  void hideNonBlockingProgressDialog();

  void startPersonDetailActivity(int personId);

  // Methods called by the Presenter that are to be implemented in MainActivity go here

}
