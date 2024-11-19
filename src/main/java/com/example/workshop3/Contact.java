package com.example.workshop3;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

public class Contact {
    
    @Size(min=3, max=64, message="Name must be between 3 and 64 characters")
    @NotEmpty(message="Name required")
    private String name;

    @Email(message="Invalid email")
    @NotEmpty(message="Email required")
    private String email;

    @Size(min=7, message="Phone number has to be at least 7 digits")
    @NotEmpty(message="Phone required")
    private String phone;

    @Past(message="Invalid date")
    @NotNull(message="Date of Birth required")
    private LocalDate dateOfBirth;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "name=%s\nemail=%s\nphone=%s\ndateOfBirth=%s".formatted(this.name, this.email, this.phone, this.dateOfBirth.toString());
    }

    

    
}
