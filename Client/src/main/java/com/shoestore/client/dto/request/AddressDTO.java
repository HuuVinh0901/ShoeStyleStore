package com.shoestore.client.dto.request;


import lombok.Data;

@Data
public class AddressDTO {
  private int addressID;
  private String street;
  private String city;
  private String ward;
  private String district;
  public int getAddressID() {
    return addressID;
  }

  public String getDistrict() {
    return district;
  }

  public String getStreet() {
    return street;
  }

  public String getCity() {
    return city;
  }

  public String getWard() {
    return ward;
  }

  public void setAddressID(int addressID) {
    this.addressID = addressID;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public void setWard(String ward) {
    this.ward = ward;
  }

  public void setDistrict(String district) {
    this.district = district;
  }
}
