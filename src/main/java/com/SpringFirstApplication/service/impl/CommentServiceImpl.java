package com.SpringFirstApplication.service.impl;

import com.SpringFirstApplication.entity.Comment;
import com.SpringFirstApplication.entity.Post;
import com.SpringFirstApplication.exception.BlogApiException;
import com.SpringFirstApplication.exception.ResourceNotFoundException;
import com.SpringFirstApplication.payload.CommentDto;
import com.SpringFirstApplication.repository.CommentRepository;
import com.SpringFirstApplication.repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ModelMapper mapper;


    public CommentDto createComment(long post_Id, CommentDto commentDto){

        Post post = postRepository.findById(post_Id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", post_Id));

        Comment comment = mapToEntity(commentDto);
        comment.setPost(post);

        Comment comme = commentRepository.save(comment);

        return mapToDto(comme);
    }

    @Override
    public List<CommentDto> getAllComments(long postId) {


        List<Comment> comments = commentRepository.getCommentsByPostId(postId);

        List<CommentDto> collect = comments.stream().map(s -> mapToDto(s)).collect(Collectors.toList());

        return collect;
    }

    @Override
    public CommentDto getCommentsByIds(long postId, long id) {

        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("PostId", "id", postId));

        Comment comment = commentRepository.findById(id).orElseThrow(() -> new BlogApiException(HttpStatus.BAD_REQUEST, "comments not found!!!"));


        CommentDto commentDto = mapToDto(comment);

        return commentDto;
    }

    @Override
    public CommentDto updateCommentsOnPost(long postId, long id, CommentDto commentDto) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));


        Comment comment = commentRepository.findById(id).orElseThrow(() -> new BlogApiException(HttpStatus.BAD_REQUEST, "Comment not found!!!"));
        Comment comment1 = mapToEntity(commentDto);

        Comment save = commentRepository.save(comment1);


        CommentDto commentDto1 = mapToDto(save);
        return commentDto1;

    }

    @Override
    public void deleteData(long postId, long id) {

        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        Comment comment = commentRepository.findById(id).orElseThrow(() -> new BlogApiException(HttpStatus.BAD_REQUEST, "Comment not found To delete!!!"));

        commentRepository.delete(comment);


    }

    Comment mapToEntity(CommentDto commentDto){


        Comment comment = mapper.map(commentDto, Comment.class);
//        Comment comment= new Comment();
//        comment.setId(commentDto.getId());
//        comment.setName(commentDto.getName());
//        comment.setBody(commentDto.getBody());
//        comment.setEmail(commentDto.getEmail());
        return  comment;
    }

    CommentDto mapToDto(Comment comment){


        CommentDto commentDto = mapper.map(comment, CommentDto.class);
//        CommentDto commentDto= new CommentDto();
//        commentDto.setId(comment.getId());
//        commentDto.setBody(comment.getBody());
//        commentDto.setName(comment.getName());
//        commentDto.setEmail(comment.getEmail());
        return commentDto;
    }

}
