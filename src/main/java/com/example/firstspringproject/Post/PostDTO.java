package com.example.firstspringproject.Post;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.*;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class PostDTO {
    private String createTime;
    @NotBlank(message = "title must not be blank")
    @Size(min = 5, max = 30, message = "title must be between 5 to 30 chracters")
    private String title;
    @NotBlank(message = "content must not be blank")
    private String content;
    private String category;
    private List<String> tags;
}
