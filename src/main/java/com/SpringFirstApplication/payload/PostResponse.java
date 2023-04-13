package com.SpringFirstApplication.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostResponse {
    private List<PostDto> content;
    private int totalPages;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private boolean last;
}
