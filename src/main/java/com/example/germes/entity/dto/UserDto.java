package com.example.germes.entity.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserDto {

    private String username;

    private String password;

    private String email;

}
