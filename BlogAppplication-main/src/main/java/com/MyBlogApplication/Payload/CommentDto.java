package com.MyBlogApplication.Payload;

import com.MyBlogApplication.Entity.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

    private String body;
    private String email;
    private String name;
}
