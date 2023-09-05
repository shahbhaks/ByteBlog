package com.example.byteblog.model;

import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity (name="categories")
@Data
public class Category extends BaseModel {

    @Column(length=100, nullable = false)
    private String title;

    private String description;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL,fetch = FetchType.LAZY) //cascade(All)--->if we remove parent,child also gets removed
    private List<Post> posts;

}
