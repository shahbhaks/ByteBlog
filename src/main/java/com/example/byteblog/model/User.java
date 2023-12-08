package com.example.byteblog.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity(name="users")
public class User extends BaseModel{

    @Column(name="user_name", nullable = false)
    private String name;

    private String email;
    private String password;
    private String about;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Post> posts;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name="user_role",
            joinColumns=@JoinColumn(name = "user",referencedColumnName = "id" ),
            inverseJoinColumns=@JoinColumn(name="role", referencedColumnName = "id"))
    private List<Role> roles;


}
