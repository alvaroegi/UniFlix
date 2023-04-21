package com.example.uniflix.InterfacesBBDD;

import com.example.uniflix.Entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}