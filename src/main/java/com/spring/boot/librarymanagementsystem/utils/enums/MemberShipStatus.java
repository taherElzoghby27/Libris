package com.spring.boot.librarymanagementsystem.utils.enums;

public enum MemberShipStatus {
    ACTIVE("ACTIVE"),
    NON_ACTIVE("NON_ACTIVE");

    private final String status;

    MemberShipStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return this.status;
    }
}
