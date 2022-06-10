package com.example.kreditbot.repository;

import com.example.kreditbot.entity.Category;
import com.example.kreditbot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Integer> {


}
