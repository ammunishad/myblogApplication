package com.MyBlogApplication.ServiceImpl;

import com.MyBlogApplication.Entity.Comment;
import com.MyBlogApplication.Entity.Post;
import com.MyBlogApplication.Exception.SourceNotFoundException;
import com.MyBlogApplication.Payload.CommentDto;
import com.MyBlogApplication.Repository.CommentRepository;
import com.MyBlogApplication.Repository.PostRepository;
import com.MyBlogApplication.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;
    @Override
    public CommentDto CreateComment(long postId, CommentDto commentDto) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new SourceNotFoundException("No post found to create Comments at id = " + postId)
        );
        Comment comment = MapToComment(commentDto);
        comment.setPost(post);
        Comment save = commentRepository.save(comment);

        return MapToDto(save);

    }

    @Override
    public void DeleteComment(long postId, long commentID) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new SourceNotFoundException("No post found to delete  Comments at id = " + postId)
        );
        Comment comment = commentRepository.findById(commentID).orElseThrow(
                () -> new SourceNotFoundException("No comment found to Delete where post id = " + postId)
        );
        commentRepository.delete(comment);
    }

    @Override
    public List<CommentDto> getAllComments(long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new SourceNotFoundException("No post found with id = " + postId)
        );
        List<Comment> allCommentsByPostId = commentRepository.findAllCommentsByPostId(postId);

        return allCommentsByPostId.stream().map(this::MapToDto).collect(Collectors.toList());
    }

    @Override
    public CommentDto UpdateComment(long postId, long commentId, CommentDto commentDto) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new SourceNotFoundException("No post found with id = " + postId)
        );
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new SourceNotFoundException("No Comment Found with id =" + commentId)
        );
        comment.setName(commentDto.getName());
        comment.setBody(commentDto.getBody());
        comment.setEmail(commentDto.getEmail());

        comment.setPost(post);
        return MapToDto(comment);
    }

    private CommentDto MapToDto(Comment save) {
        CommentDto dto = new CommentDto();
        dto.setName(save.getName());
        dto.setBody(save.getBody());
        dto.setEmail(save.getEmail());
        return dto;
    }

    Comment MapToComment(CommentDto dto){
        Comment comment = new Comment();
        comment.setName(dto.getName());
        comment.setBody(dto.getBody());
        comment.setEmail(dto.getEmail());
        return comment;
    }
}
