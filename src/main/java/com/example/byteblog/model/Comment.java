package com.example.byteblog.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="comments")
@Data
public class Comment extends BaseModel{


    private String content;
    @ManyToOne
    private Post post;



}
