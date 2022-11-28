package com.example.merkury.service;

import com.example.merkury.entity.User;
import com.example.merkury.entity.UserData;
import com.example.merkury.entity.dto.UserDataDto;
import com.example.merkury.repo.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

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

    public UserDataDto getUserDataDto(Long userId) {
        return UserDataDto.fromUserData(userDataRepository.getUserDataByUser_id(userId));
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
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
