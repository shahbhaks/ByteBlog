package com.example.byteblog.repository;

import com.example.byteblog.model.Category;
import com.example.byteblog.model.Post;
import com.example.byteblog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface PostRepository extends JpaRepository<Post,Long> {

   // custom methods
    List<Post> findByCategory(Category category);
    List<Post> findByUser(User user);

    //Internally->uses like query
  //custom query->@Query("select p from Post p where p.title like:key")
   List<Post> findByTitleContaining(String title);

    List<Post> findByContentContaining(String content);
}
