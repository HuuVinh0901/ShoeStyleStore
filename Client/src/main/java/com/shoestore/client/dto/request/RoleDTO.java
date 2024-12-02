package com.shoestore.client.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoleDTO {
  private int roleID;
  private String name;
  private String description;
  // Getters và Setters
  public int getRoleID() {
    return roleID;
  }

  public void setRoleID(int roleID) {
    this.roleID = roleID;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
