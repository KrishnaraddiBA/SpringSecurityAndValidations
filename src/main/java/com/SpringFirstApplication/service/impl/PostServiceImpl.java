package com.SpringFirstApplication.service.impl;

import com.SpringFirstApplication.entity.Post;
import com.SpringFirstApplication.exception.ResourceNotFoundException;
import com.SpringFirstApplication.payload.PostDto;
import com.SpringFirstApplication.payload.PostResponse;
import com.SpringFirstApplication.repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class PostServiceImpl implements PostService{

    @Autowired
    private PostRepository postRepository;
    
    @Autowired
    private ModelMapper mapper;
    @Override
    public PostDto createPost(PostDto postDto) {


        Post post = mapToentity(postDto);
        postRepository.save(post);
        PostDto postDto1 = mapToDto(post);
        return postDto1;
    }
//localhost:8080/api/posts/getAll?pageNo=0&pageSize=6
    @Override
    public PostResponse getAllPosts(int pageNo,int pageSize,String sortBy,String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo,pageSize,sort);
        Page<Post> posts = postRepository.findAll(pageable);
        List<Post> content = posts.getContent();

        List<PostDto> contents = content.stream().map(c -> mapToDto(c)).collect(Collectors.toList());

        PostResponse postResponse= new PostResponse();
        postResponse.setContent(contents);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());


//        List<Post> posts = postRepository.findAll();
//        List<PostDto> postDtos = posts.stream().map(p -> mapToDto(p)).collect(Collectors.toList());

        return postResponse;
    }


    @Override
    public PostDto getByIdes(long id) {

        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return mapToDto(post);
    }

    @Override
    public PostDto updateposts(PostDto postDto, long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("post", "id", id));
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        Post save = postRepository.save(post);
        PostDto postDto1 = mapToDto(save);

        return postDto1;
    }

    @Override
    public void deletePosts(long id) {

        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        postRepository.delete(post);

    }



    private Post mapToentity(PostDto postDto){

        Post p = mapper.map(postDto, Post.class);
//        Post p= new Post();
//        p.setId(postDto.getId());
//        p.setDescription(postDto.getDescription());
//        p.setContent(postDto.getContent());
//        p.setTitle(postDto.getTitle());
        return p ;

    }

    private PostDto mapToDto(Post post){


        PostDto pd = mapper.map(post, PostDto.class);

//        PostDto pd= new PostDto();
//        pd.setId(post.getId());
//        pd.setTitle(post.getTitle());
//        pd.setContent(post.getDescription());
//        pd.setDescription(post.getDescription());
        return pd;
    }
}
