package com.MyBlogApplication.Payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostDto {
    private long id;
    @NotEmpty
    private String title;
    @NotEmpty
    @Size(min = 10,message = "Post Content should hava at least 10 characters")
    private String content;
    @NotEmpty
    @Size(min = 10,message = "Post description should hava at least 10 characters")
    private String description;
}
