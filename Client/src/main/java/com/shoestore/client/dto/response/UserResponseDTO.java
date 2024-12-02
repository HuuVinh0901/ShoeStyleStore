package com.shoestore.client.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.shoestore.client.dto.request.UserDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonPropertyOrder(alphabetic = false)
@ToString
public class UserResponseDTO {
  private int totalItems;
  private int totalPages;
  @JsonProperty("user")
  private List<UserDTO> userDTOs;

  public int getTotalItem() {
    return totalItems;
  }
  public void setTotalItem(int totalItems) {
    this.totalItems = totalItems;
  }
  public int getTotalPage() {
    return totalPages;
  }
  public void setTotalPage(int totalPage) {
    this.totalPages = totalPage;
  }
  public List<UserDTO> getUsers() {
    return userDTOs;
  }
  public void setUsers(List<UserDTO> users) {
    this.userDTOs = users;
  }
}
