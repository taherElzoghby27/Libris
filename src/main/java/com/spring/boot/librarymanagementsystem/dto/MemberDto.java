package com.spring.boot.librarymanagementsystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.boot.librarymanagementsystem.utils.MemberShipStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class MemberDto {
    private Long id;
    @JsonProperty("full_name")
    private String fullName;
    private String email;
    private String phone;
    private String address;
    @JsonProperty("membership_start-date")
    private LocalDateTime memberShipStartDate;
    @JsonProperty("membership_end_date")
    private LocalDateTime memberShipEndDate;
    @Enumerated(EnumType.STRING)
    @JsonProperty("membership_status")
    private MemberShipStatus memberShipStatus;
}
