package com.mhmt.foodbasket.ui.persondetail;

import com.mhmt.foodbasket.domain.PersonDetail;
import com.mhmt.foodbasket.ui.base.MvpView;

public interface PersonDetailMvpView extends MvpView {

  void initiateUI();

  void hideNonBlockingProgressDialog();

  void updateUI(PersonDetail personDetail);

  // Methods called by the Presenter that are to be implemented in MainActivity go here

}
