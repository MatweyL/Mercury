package com.example.germes.repo;

import com.example.germes.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDataRepository extends JpaRepository<UserData, Long> {

    UserData getUserDataByUser_id(Long id);

}
