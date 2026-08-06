package com.jhonecmd.mailsender.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Data
public class StudentDTO {

    @NotBlank(message = "Name is required.")
    private String name;

    @Email(message = "The email field is invalid.")
    @NotBlank(message = "Email is required.")
    private String email;

    @NotNull(message = "Birthday is required")
    private LocalDate birthday;

    @Length(min = 8, max = 100, message = "The password length must be between 10 and 100 characters.")
    private String password;

}
