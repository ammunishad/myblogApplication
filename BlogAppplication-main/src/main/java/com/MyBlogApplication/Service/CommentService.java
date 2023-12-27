package com.MyBlogApplication.Service;

import com.MyBlogApplication.Payload.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto CreateComment(long postId, CommentDto commentDto);

    void DeleteComment(long postId, long commentID);

    List<CommentDto> getAllComments(long postId);

    CommentDto UpdateComment(long postId, long commentId, CommentDto commentDto);
}
