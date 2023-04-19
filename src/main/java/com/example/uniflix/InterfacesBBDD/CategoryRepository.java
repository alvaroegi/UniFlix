package com.example.uniflix.InterfacesBBDD;

import com.example.uniflix.Entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
