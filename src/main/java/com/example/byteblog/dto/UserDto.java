package com.example.byteblog.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Data
public class UserDto {
    private Long id;

    //@NotEmpty //blank & not-null
   // @Size(min = 3, message = "Username must be min of 3 characters !!")
    private String name;

    @Email(message = "Email address is not valid !!")
    private String email;

    @NotEmpty
    @Size(min=4, max=10, message="Password must be min of 3 chars and max of 10 chars !!")
   // @Pattern(regexp = )--> can provide the combination
    private String password;

    @NotEmpty
    private String about;
}
