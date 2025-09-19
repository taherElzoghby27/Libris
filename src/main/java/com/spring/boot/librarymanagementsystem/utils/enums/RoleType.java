package com.spring.boot.librarymanagementsystem.utils.enums;

public enum RoleType {
    STAFF("STAFF"),
    ADMIN("ADMIN"),
    LIBRARIAN("LIBRARIAN");

    private final String role;

    RoleType(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return this.role;
    }
}
