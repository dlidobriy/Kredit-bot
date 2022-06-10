package com.example.kreditbot.component;

import com.example.kreditbot.entity.Category;
import com.example.kreditbot.entity.Product;
import com.example.kreditbot.repository.CategoryRepository;
import com.example.kreditbot.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
@RequiredArgsConstructor
public class Dataloader implements CommandLineRunner {


    @Value("${spring.sql.init.mode}")
    private String mode;

    final CategoryRepository categoryRepository;
    final ProductRepository productRepository;


    @Override
    public void run(String... args) throws Exception {
        if (mode.equals("always")) {
            Category category1 = categoryRepository.save(new Category(1, "Kompyutorlar", "zor", true));

            Optional<Category> categoryy1 = categoryRepository.findById(1);
            Category category = categoryy1.get();


            Product product1 = productRepository.save(new Product(1, "acer", category));
            Product product2 = productRepository.save(new Product(2, "dell", category));
            Product product3 = productRepository.save(new Product(3, "hp", category));
            Product product4 = productRepository.save(new Product(4, "lenova", category));

        }

    }
}

