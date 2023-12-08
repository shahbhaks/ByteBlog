package com.example.byteblog.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

import static jakarta.persistence.GenerationType.AUTO;

@Data
@MappedSuperclass
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

//    @CreatedDate
//    @Temporal(value = TemporalType.TIMESTAMP)
//    private Date createdAt;
//
//    @LastModifiedDate
//    @Temporal(value = TemporalType.TIMESTAMP)
//    private Date updatedAt;
}
