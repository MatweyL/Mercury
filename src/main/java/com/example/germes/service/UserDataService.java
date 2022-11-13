package com.example.germes.service;

import com.example.germes.entity.User;
import com.example.germes.entity.UserData;
import com.example.germes.entity.dto.UserDataDto;
import com.example.germes.repo.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class UserDataService {

    @Autowired
    private final UserDataRepository userDataRepository;

    public UserDataService(UserDataRepository userDataRepository) {
        this.userDataRepository = userDataRepository;
    }

    public UserDataDto getUserDataDto() {
        return UserDataDto.fromUserData(userDataRepository.getUserDataByUser_id(getCurrentUser().getId()));
    }

    public void update(UserDataDto userDataDto) {
        UserData userData = userDataRepository.getUserDataByUser_id(getCurrentUser().getId());
        if (userData == null) {
            userData = new UserData();
            userData.setUser(getCurrentUser());
        }
        userData.setBirthday(userDataDto.getBirthday());
        userData.setAdditionalInfo(userDataDto.getAdditionalInfo());
        userData.setPhoneNumber(userDataDto.getPhoneNumber());
        userData.setName(userDataDto.getName());
        userData.setSurname(userDataDto.getSurname());
        userDataRepository.save(userData);
    }

    public User getCurrentUser() {
        //        System.out.println(currentUser.getId() + " " + currentUser.getUsername());
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
