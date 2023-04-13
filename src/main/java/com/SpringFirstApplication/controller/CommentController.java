package com.SpringFirstApplication.controller;

import com.SpringFirstApplication.payload.CommentDto;
import com.SpringFirstApplication.service.impl.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createPost(@PathVariable(name="postId") long postId, @RequestBody CommentDto commentDto){


        CommentDto com = commentService.createComment(postId, commentDto);
        return new ResponseEntity<>(com,  HttpStatus.CREATED);
    }


    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<CommentDto>> getAllCommentsByPost(@PathVariable(name="postId") long postId){
        List<CommentDto> allComments = commentService.getAllComments(postId);
        return new ResponseEntity<>(allComments,HttpStatus.OK);
    }


    @GetMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> getCommentsByCommentId(@PathVariable(name="postId") long postId,@PathVariable(name="id") long id){
        CommentDto commentsByIds = commentService.getCommentsByIds(postId, id);
        return new ResponseEntity<>(commentsByIds,HttpStatus.OK);
    }


    @PutMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> updateCommentsOnPost(@PathVariable(name="postId") long postId,@PathVariable(name="id") long id,CommentDto commentDto){

        CommentDto commentDto1 = commentService.updateCommentsOnPost(postId, id, commentDto);


        return new ResponseEntity<>(commentDto1,HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<String> deleteComments(@PathVariable(name="postId") long postId,@PathVariable(name="id") long id){

        commentService.deleteData(postId,id);
        return new ResponseEntity<>("Deleted record successfully!!!",HttpStatus.OK);
    }



}
