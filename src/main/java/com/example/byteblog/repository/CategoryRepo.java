package com.example.byteblog.repository;

import com.example.byteblog.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface CategoryRepo extends JpaRepository<Category,Long> {
}
