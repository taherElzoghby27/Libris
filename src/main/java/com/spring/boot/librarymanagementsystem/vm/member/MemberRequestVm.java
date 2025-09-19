package com.spring.boot.librarymanagementsystem.vm.member;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.boot.librarymanagementsystem.utils.enums.MemberShipStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
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
public class MemberRequestVm {
    @NotEmpty(message = "full name must be not empty")
    @JsonProperty("full_name")
    private String fullName;
    @NotEmpty(message = "email must be not empty")
    @Email(message = "email format")
    private String email;
    @NotEmpty(message = "phone must be not empty")
    @Pattern(regexp = "^[0-9]{10,15}$", message = "phone number must be valid")
    private String phone;
    @NotEmpty(message = "address must be not empty")
    private String address;
    @NotNull(message = "membership end date must be not empty")
    @JsonProperty("membership_end_date")
    private LocalDateTime memberShipEndDate;
    @Enumerated(EnumType.STRING)
    @JsonProperty("membership_status")
    private MemberShipStatus memberShipStatus;
}
