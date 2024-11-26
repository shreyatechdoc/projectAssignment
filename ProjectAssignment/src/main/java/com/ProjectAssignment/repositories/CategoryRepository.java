package com.ProjectAssignment.repositories;

import com.ProjectAssignment.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, String> {
}
