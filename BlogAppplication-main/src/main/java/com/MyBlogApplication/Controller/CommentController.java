package com.MyBlogApplication.Controller;

import com.MyBlogApplication.Payload.CommentDto;
import com.MyBlogApplication.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentDto> createComment(@RequestParam long postId, @RequestBody CommentDto commentDto){
        CommentDto dto = commentService.CreateComment(postId, commentDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    // url = http://localhost:8080/api/Comments?postId=&commentId=
    @DeleteMapping
    public ResponseEntity<String> DeleteComment(@RequestParam long postId, @RequestParam long commentId){
        commentService.DeleteComment(postId,commentId);

        return new ResponseEntity<>("Comment had been deleted successfully",HttpStatus.OK
        );
    }

    @GetMapping
     public List<CommentDto> getAllComments(@RequestParam long postId){
        return commentService.getAllComments(postId);
    }

    @PutMapping
    public ResponseEntity<CommentDto> UpdateComment(@RequestParam long postId, @RequestParam long commentId, @RequestBody CommentDto commentDto){
        CommentDto dto = commentService.UpdateComment(postId, commentId, commentDto);
        return new ResponseEntity<>(dto,HttpStatus.ACCEPTED);
    }
}
