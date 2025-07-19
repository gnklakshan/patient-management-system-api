package com.patientmanagement.patientservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class patientRequestDTO {
    @NotBlank
    @Size(max=100,message = "Name can not exceed 1000 charcters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Address required")
    private String address;

    @NotNull(message = "Date of Birth is required")
    private String dateOfBirth;

    @NotNull(message = "Registerd date is required")
    private String registerDate;


}
