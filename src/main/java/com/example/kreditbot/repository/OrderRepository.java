package com.example.kreditbot.repository;

import com.example.kreditbot.entity.Order;
import com.example.kreditbot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Integer> {


}
