package com.spring.boot.librarymanagementsystem.vm.member;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.boot.librarymanagementsystem.utils.MemberShipStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class MemberRequestUpdateVm {
    @NotNull(message = "id must be not null")
    private Long id;
    @JsonProperty("full_name")
    private String fullName;
    @Email(message = "email format")
    private String email;
    @Pattern(regexp = "^[0-9]{10,15}$", message = "phone number must be valid")
    private String phone;
    private String address;
    @JsonProperty("membership_end_date")
    private LocalDateTime memberShipEndDate;
    @Enumerated(EnumType.STRING)
    @JsonProperty("membership_status")
    private MemberShipStatus memberShipStatus;
}
