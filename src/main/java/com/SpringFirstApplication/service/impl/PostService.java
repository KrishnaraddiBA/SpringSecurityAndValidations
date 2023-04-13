package com.SpringFirstApplication.service.impl;

import com.SpringFirstApplication.payload.PostDto;
import com.SpringFirstApplication.payload.PostResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {


    PostDto createPost(PostDto postDto);

    PostResponse getAllPosts(int pageNo,int pageSize,String sortBy,String sortDir);

    PostDto getByIdes(long id);

    PostDto updateposts(PostDto postDto, long id);

    void deletePosts(long id);


}
