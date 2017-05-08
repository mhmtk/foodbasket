package com.mhmt.foodbasket.ui.persondetail;

import com.mhmt.foodbasket.domain.PersonDetail;

public class PersonDetailUploaded {

  private PersonDetail personDetail;

  public PersonDetailUploaded(final PersonDetail personDetail) {

    this.personDetail = personDetail;
  }

  public PersonDetail getPersonDetail() {
    return personDetail;
  }
}
