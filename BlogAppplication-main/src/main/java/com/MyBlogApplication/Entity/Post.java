package com.MyBlogApplication.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "Title",nullable = false,unique = true)
    private String title;
    @Column(name = "Content",nullable = false,unique = true)
    private String content;
    @Column(name = "Description",nullable = false,unique = true)
    private String description;
    // Composition (Is a relation).
    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL,orphanRemoval = true)
    List<Comment> comments = new ArrayList<>();
}
