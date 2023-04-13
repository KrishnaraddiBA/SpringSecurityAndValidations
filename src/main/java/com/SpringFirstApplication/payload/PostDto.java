package com.SpringFirstApplication.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PostDto {

    private long id;
    @NotEmpty
    @Size(min = 2,message = "size must be min atleast 2 chartacter")
    private String title;
    @NotNull
    @NotEmpty(message = "the description must not be empty")
    @Size(min = 2,message = "the description size must be atleast 2 characters")
    private String description;


    @NotBlank(message = "the content cannot be blank")
    private String content;
}
