package com.example.byteblog.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Entity
@Data
public class Post extends BaseModel{

    @Column(name="post_title", length=100, nullable = false)
    private String title;
    @Column(length =1000)
    private String content;
    private String imageName;
    // private Date addedDate;
    @ManyToOne
    @JoinColumn(name="categoryId")
    private Category category;

    @ManyToOne
    @JoinColumn(name="userId")
    private User user;

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
    private List<Comment> comments;


}
