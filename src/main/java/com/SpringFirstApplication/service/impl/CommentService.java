package com.SpringFirstApplication.service.impl;

import com.SpringFirstApplication.payload.CommentDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {

    public CommentDto createComment(long post_Id, CommentDto commentDto);

    List<CommentDto> getAllComments(long postId);

    CommentDto getCommentsByIds(long postId, long id);

    CommentDto updateCommentsOnPost(long postId, long id, CommentDto commentDto);

    void deleteData(long postId, long id);
}
