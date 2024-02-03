package com.frontend.CatFrontend.repo;


import com.frontend.CatFrontend.entitiy.Cat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CatRepo extends JpaRepository<Cat, Integer> {
    Optional<Cat> findByName(String name);
}
