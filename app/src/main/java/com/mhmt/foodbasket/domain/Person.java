package com.mhmt.foodbasket.domain;

public class Person {

  private int id;
  private String name;
  private String lastName;
  private String profilePicture;

  public Person() {

  }

  public int getId() {
    return id;
  }

  public void setId(final int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(final String lastName) {
    this.lastName = lastName;
  }

  public String getProfilePicture() {
    return profilePicture;
  }

  public void setProfilePicture(final String profilePicture) {
    this.profilePicture = profilePicture;
  }
}
