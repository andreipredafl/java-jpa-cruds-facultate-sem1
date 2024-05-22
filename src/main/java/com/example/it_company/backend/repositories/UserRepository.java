package com.example.it_company.backend.repositories;

import com.example.it_company.backend.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
