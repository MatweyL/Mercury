package com.example.germes.entity.dto;

import com.example.germes.entity.UserData;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserDataDto {

    private String name;

    private String surname;

    private String phoneNumber;

    private String additionalInfo;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;


    public static UserDataDto fromUserData(UserData userData) {
        UserDataDto userDataDto = new UserDataDto();
        if (userData != null) {
            userDataDto.setName(userData.getName());
            userDataDto.setSurname(userData.getSurname());
            userDataDto.setPhoneNumber(userData.getPhoneNumber());
            userDataDto.setAdditionalInfo(userData.getAdditionalInfo());
            userDataDto.setBirthday(userData.getBirthday());
        }
        return userDataDto;
    }

}
