package com.spring.boot.librarymanagementsystem.utils;

public enum MemberShipStatus {
    ACTIVE("ACTIVE"),
    NON_ACTIVE("NON-ACTIVE");

    private final String status;

    MemberShipStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return this.status;
    }
}
