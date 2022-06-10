package com.example.kreditbot.repository;

import com.example.kreditbot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {


    Optional<User> findByChatId(String chatId);
}
