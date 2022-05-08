package com.example.germes.repo;

import com.example.germes.entity.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CargoRepository extends JpaRepository<Cargo, Long> {
}
