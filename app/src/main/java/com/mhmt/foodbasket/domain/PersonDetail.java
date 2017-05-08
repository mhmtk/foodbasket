package com.mhmt.foodbasket.domain;

import java.util.Date;

public class PersonDetail {

  private int id;
  private String name;
  private String lastName;
  private String profilePicture;
  private Date birthDate;
  private String cellNo;
  private String workNo;
  private String address;

  public PersonDetail() {
  }

  public PersonDetail(final int personId, final String cellNo, final String workNo, final String address) {
    id = personId;
    this.cellNo = cellNo;
    this.workNo = workNo;
    this.address = address;
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

  public Date getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(final Date birthDate) {
    this.birthDate = birthDate;
  }

  public String getCellNo() {
    return cellNo;
  }

  public void setCellNo(final String cellNo) {
    this.cellNo = cellNo;
  }

  public String getWorkNo() {
    return workNo;
  }

  public void setWorkNo(final String workNo) {
    this.workNo = workNo;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(final String address) {
    this.address = address;
  }
}
