package com.mhmt.foodbasket.ui.personlist;

import com.mhmt.foodbasket.domain.Person;

import java.util.List;

public class ListDownloaded {

  private List<Person> body;

  public ListDownloaded(final List<Person> body) {

    this.body = body;
  }

  public List<Person> getPersons() {
    return body;
  }
}
