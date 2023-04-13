package com.SpringFirstApplication.controller;

import com.SpringFirstApplication.payload.PostDto;
import com.SpringFirstApplication.payload.PostResponse;
import com.SpringFirstApplication.service.impl.PostService;
import com.SpringFirstApplication.util.AppConstants;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/createPost")
    //localhost:8080/api/posts/createPost
    public ResponseEntity<Object> createPost(@Valid @RequestBody PostDto postDto, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }


    @GetMapping("/getAll")
    //localhost:8080/api/posts/getAll
    public PostResponse getAllPosts(@RequestParam(value="pageNo",defaultValue = AppConstants.DEFAULT_PAGE_NO,required = false)int pageNo,
                                     @RequestParam(value="pageSize",defaultValue=AppConstants.DEFAULT_PAGE_SIZE,required = false)int pageSize,
                                    @RequestParam(value="sortBy",defaultValue = AppConstants.DEFAULT_SORT_BY,required = false)String sortBy,
                                    @RequestParam(value="sortDir",defaultValue = AppConstants.DEFAULT_SORT_DIR,required = false) String sortDir){
        return postService.getAllPosts(pageNo,pageSize,sortBy,sortDir);
    }

    @GetMapping("/{id}")
    //localhost:8080/api/posts/1
    public PostDto getByIds(@PathVariable(name="id") long id){
        return postService.getByIdes(id);
    }

    @PutMapping("/updatePost/{id}")
    //localhost:8080/api/posts/updatePost/1
    public ResponseEntity<PostDto> updatePost(@PathVariable(name="id") long id,@RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.updateposts(postDto,id),HttpStatus.OK);
    }


    @DeleteMapping("/deletePost/{id}")
    //localhost:8080/api/posts/deletePost/1
    public ResponseEntity<String> deletePosts(@PathVariable(name="id") long id){
        postService.deletePosts(id);

        return  new ResponseEntity<>("Post deleted successfully!!!",HttpStatus.OK);

    }

    //Implementing a pagination

//    @GetMapping("/pagination")
//    public PostResponse getAllPosts(@RequestParam(value="pageNo",defaultValue="0",required = false )int pageNo,
//                                    @RequestParam(value="pageSize",defaultValue="10",required=false)int pageSize,
//                                    @RequestParam(value = "sortBy",defaultValue = "id",required = false) String sortBy,
//                                    @RequestParam(value="sortDir",defaultValue = "asc",required = false)String sortDir){
//        postService.getAllPostsOnPagination(pageNo,pageSize,sortBy,sortDir);
//
//
//        return null;
//
//    }


}
