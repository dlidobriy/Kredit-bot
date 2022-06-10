package com.example.kreditbot.repository;

import com.example.kreditbot.entity.Product;
import com.example.kreditbot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Integer> {


}
