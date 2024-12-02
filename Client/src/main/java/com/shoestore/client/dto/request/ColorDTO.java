package com.shoestore.client.dto.request;


/*
    @author: Đào Thanh Phú
    Date: 11/22/2024
    Time: 3:11 PM
    ProjectName: Client
*/


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@RequiredArgsConstructor
public enum ColorDTO {
    RED("Red"),
    GREEN("Green"),
    BLUE("Blue"),
    YELLOW("Yellow"),
    BLACK("Black"),
    WHITE("White"),
    PINK("Pink");


    private final String colorName;

    @Override
    public String toString() {
        return colorName;
    }
}
