package com.example.byteblog.repository;

import com.example.byteblog.model.Category;
import com.example.byteblog.model.Post;
import com.example.byteblog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {

   // custom methods
    List<Post> findAllByCategory(Category category);
    List<Post> findAllByUser(User user);
}
