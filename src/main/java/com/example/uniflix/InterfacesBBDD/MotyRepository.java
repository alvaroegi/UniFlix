package com.example.uniflix.InterfacesBBDD;

import com.example.uniflix.Entities.Category;
import com.example.uniflix.Entities.Moty;
import org.springframework.data.jpa.repository.JpaRepository;
public interface MotyRepository extends JpaRepository<Moty, Long> {
}
