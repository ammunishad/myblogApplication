package com.MyBlogApplication.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "Body",nullable = false)
    private String body;
    @Column(name = "Email",nullable = false)
    private  String email;
    @Column(name = "Name",nullable = false)
    private String name;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

}
