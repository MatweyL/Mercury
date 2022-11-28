package com.example.merkury.repo;

import com.example.merkury.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDataRepository extends JpaRepository<UserData, Long> {

    UserData getUserDataByUser_id(Long id);

}
