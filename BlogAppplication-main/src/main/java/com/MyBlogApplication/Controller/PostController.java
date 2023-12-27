package com.MyBlogApplication.Controller;

import com.MyBlogApplication.Payload.PostDto;
import com.MyBlogApplication.Payload.postResponse;
import com.MyBlogApplication.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequestMapping("/api/posts")
public class PostController {
@Autowired
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;

    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> CreatePost(@Valid @RequestBody PostDto postDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        PostDto postDto1 = postService.CreatePost(postDto);
        return new ResponseEntity<>(postDto1, HttpStatus.CREATED);
    }
    // url = http://localhost:8080/api/posts?pageNo=&pageSize=&sortBy=&sortDir=
    @GetMapping
    public postResponse GetAllPost(
            @RequestParam(name = "pageNo",required = false,defaultValue ="0") int pageNo,
            @RequestParam(name = "pageSize",required = false,defaultValue = "5")int pageSize,
            @RequestParam(name = "sortBy",required = false,defaultValue = "id")String sortBy,
            @RequestParam(name = "sortDir",required = false,defaultValue = "acs")String sortDir
    ){

        postResponse allPost = postService.getAllPost(pageNo, pageSize, sortBy, sortDir);
        return allPost;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<PostDto> UpdatePost(@PathVariable long id, @RequestBody PostDto dto){
        PostDto postDto = postService.UpdatePost(id, dto);

        return new ResponseEntity<>(postDto,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long id){
        PostDto postByID = postService.getPostByID(id);
        return new ResponseEntity<>(postByID,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> DeletePostById(@PathVariable long id){
        postService.DeleteById(id);
        return new ResponseEntity<>("post had been deleted ",HttpStatus.OK);
    }
}
