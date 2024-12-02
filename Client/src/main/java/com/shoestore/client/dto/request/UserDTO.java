package com.shoestore.client.dto.request;

import com.shoestore.client.dto.request.AddressDTO;
import com.shoestore.client.dto.request.CartDTO;
import com.shoestore.client.dto.request.RoleDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import com.shoestore.client.dto.request.UserStatus;
@Data
@NoArgsConstructor
public class UserDTO {
  private int userID;
  private String name;
  private String email;
  private String password;
  private String phoneNumber;
  private String userName;
  private String status;
  private RoleDTO role;
  private List<AddressDTO> addresses;
//  private CartDTO cart;
  private String ci;




  public List<AddressDTO> getAddress() {
    return addresses;
  }

  public void setAddress(List<AddressDTO> addresses) {
    this.addresses = addresses;
  }

  @Override
  public String toString() {
    return "UserDTO{" +
            "role=" + role +
            ", userID=" + userID +
            ", CI=" + ci +
            ", email='" + email + '\'' +
            ", name='" + name + '\'' +
            ", password='" + password + '\'' +
            ", phoneNumber='" + phoneNumber + '\'' +
            ", status=" + status +
            ", userName='" + userName + '\'' +
            ", address=" + addresses +
            '}';
  }

  public UserDTO(RoleDTO role, int userID, String ci, String email, String name, String password, String phoneNumber, UserStatus status, String userName, List<AddressDTO> address) {
    this.role = role;
    this.userID = userID;
    this.ci = ci;
    this.email = email;
    this.name = name;
    this.password = password;
    this.phoneNumber = phoneNumber;
    this.userName = userName;
    this.addresses = address;
  }


  // Getter và setter
  public RoleDTO getRole() {
    return role;
  }

  public void setRole(RoleDTO role) {
    this.role = role;
  }

  public int getUserID() {
    return userID;
  }

  public void setUserID(int userID) {
    this.userID = userID;
  }

  public String getCI() {
    return ci;
  }

  public void setCI(String ci) {
    this.ci = ci;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public void setRoleName(String name) {
  }

  public void setRoleDescription(String description) {

  }
}

