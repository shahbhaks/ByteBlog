package com.example.byteblog.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Post extends BaseModel{

    @Column(name="post_title", length=100, nullable = false)
    private String title;

    @Column(length =1000)
    private String content;

    private String imageName;

    @ManyToOne
    @JoinColumn(name="cat_Id")
    private Category category;

    @ManyToOne
    @JoinColumn(name="usr_Id")
    private User user;

}
