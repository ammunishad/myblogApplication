package com.MyBlogApplication.ServiceImpl;

import com.MyBlogApplication.Entity.Post;
import com.MyBlogApplication.Exception.SourceNotFoundException;
import com.MyBlogApplication.Payload.PostDto;
import com.MyBlogApplication.Payload.postResponse;
import com.MyBlogApplication.Repository.PostRepository;
import com.MyBlogApplication.Service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private ModelMapper modelMapper;

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository,ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PostDto CreatePost(PostDto postDto) {
        Post post = MapToPost(postDto);

        Post save = postRepository.save(post);
        return MapTODto(save);
    }

    @Override
    public postResponse getAllPost(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Post> pagePostObjects = postRepository.findAll(pageable);
        List<Post> all = pagePostObjects.getContent();
        List<PostDto> collect = all.stream().map(this::MapTODto).collect(Collectors.toList());

        postResponse PostResponse = new postResponse();
        PostResponse.setDto(collect);
        PostResponse.setPageNo(pagePostObjects.getNumber());
        PostResponse.setTotalPage(pagePostObjects.getTotalPages());
        PostResponse.setLastPage(pagePostObjects.isLast());
        PostResponse.setPageSize(pagePostObjects.getSize());
        return  PostResponse;
    }

    @Override
    public PostDto UpdatePost(long id, PostDto dto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new SourceNotFoundException("Post not found")
        );
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
       post.setDescription(dto.getDescription());
        Post save = postRepository.save(post);
        return MapTODto(save);
    }

    @Override
    public PostDto getPostByID(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new SourceNotFoundException("post didn't find by these id =" + id)
        );
        return MapTODto(post);
    }

    @Override
    public void DeleteById(long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new SourceNotFoundException("Post not found with id to delete" + id)
        );
        postRepository.delete(post);
    }

    // after Using ModdelMapper concept.
    PostDto MapTODto(Post post){
    //    PostDto dto = new PostDto();
        PostDto dto = modelMapper.map(post,PostDto.class);
//        dto.setId(post.getId());
//        dto.setTitle(post.getTitle());
//        dto.setContent(post.getContent());
//        dto.setDescription(post.getDescription());
        return dto;
    }

    Post MapToPost(PostDto postDto){
      //  Post post = new Post();
       Post post = modelMapper.map(postDto,Post.class);
//        post.setId(postDto.getId());
//        post.setTitle(postDto.getTitle());
//        post.setContent(postDto.getContent());
//        post.setDescription(postDto.getDescription());
        return post;
    }
}
