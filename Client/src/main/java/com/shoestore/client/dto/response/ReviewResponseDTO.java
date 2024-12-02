package com.shoestore.client.dto.response;


/*
    @author: Đào Thanh Phú
    Date: 11/26/2024
    Time: 8:01 PM
    ProjectName: Client
*/

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shoestore.client.dto.request.ReviewDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ReviewResponseDTO {

    private int totalItems;
    private int totalPages;
    @JsonProperty("reviews")
    private List<ReviewDTO> reviewDTOs;
}
