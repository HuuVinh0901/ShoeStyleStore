package com.shoestore.client.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum UserStatus {
    ACTIVE, INACTIVE;

    // Optionally, map string values like "Active" to enum values
    @JsonCreator
    public static UserStatus fromString(String status) {
        if (status == null) {
            return null;
        }
        switch (status.toLowerCase()) {
            case "active":
                return ACTIVE;
            case "inactive":
                return INACTIVE;
            default:
                throw new IllegalArgumentException("Unknown status: " + status);
        }
    }
}
