package com.example.germes.dto;

import com.sun.istack.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class UserDto {

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String matchingPassword;

    @NotNull
    private String email;

    // standard getters and setters
}