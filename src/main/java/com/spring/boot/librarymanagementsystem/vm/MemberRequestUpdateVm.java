package com.spring.boot.librarymanagementsystem.vm;

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
    private String fullName;
    @Email(message = "email format")
    private String email;
    @Pattern(regexp = "^[0-9]{10,15}$", message = "phone number must be valid")
    private String phone;
    private String address;
    private LocalDateTime memberShipEndDate;
    @Enumerated(EnumType.STRING)
    private MemberShipStatus memberShipStatus;
}
