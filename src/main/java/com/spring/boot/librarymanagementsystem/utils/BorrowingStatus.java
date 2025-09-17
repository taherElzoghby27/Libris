package com.spring.boot.librarymanagementsystem.utils;

public enum BorrowingStatus {
    BORROWED("BORROWED"),
    RETURNED("RETURNED"),
    OVERDUE("OVERDUE"),
    LOST("LOST");
    private final String status;

    BorrowingStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return this.status;
    }
}
