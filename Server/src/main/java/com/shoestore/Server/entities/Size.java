package com.shoestore.Server.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Size {
    SIZE_36(36),
    SIZE_37(37),
    SIZE_38(38),
    SIZE_39(39),
    SIZE_40(40),
    SIZE_41(41),
    SIZE_42(42),
    SIZE_43(43),
    SIZE_44(44);

    private final int sizeValue;

    @Override
    public String toString() {
        return String.valueOf(sizeValue);
    }
}

