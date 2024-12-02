package com.shoestore.client.dto.request;


/*
    @author: Đào Thanh Phú
    Date: 11/26/2024
    Time: 7:58 PM
    ProjectName: Client
*/

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ReviewDTO {

    private int reviewID;
    private String contents;
    private int start;
    private LocalDateTime createDate;
    private ProductDTO product;
    private UserDTO user;
}
