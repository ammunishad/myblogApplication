package com.MyBlogApplication.Service;

import com.MyBlogApplication.Payload.PostDto;
import com.MyBlogApplication.Payload.postResponse;

import java.util.List;

public interface PostService {
    PostDto CreatePost(PostDto postDto);

    postResponse getAllPost(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDto UpdatePost(long id, PostDto dto);

    PostDto getPostByID(Long id);

    void DeleteById(long id);
}
