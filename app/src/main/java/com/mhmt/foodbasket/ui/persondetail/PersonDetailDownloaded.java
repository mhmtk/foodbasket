package com.mhmt.foodbasket.ui.persondetail;

import com.mhmt.foodbasket.domain.PersonDetail;

public class PersonDetailDownloaded {

  private PersonDetail personDetail;

  public PersonDetailDownloaded(final PersonDetail personDetail) {

    this.personDetail = personDetail;
  }

  public PersonDetail getPersonDetail() {
    return personDetail;
  }
}
