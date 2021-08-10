package com.example.firstspringproject.Post;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class PostDTO {
    private String createTime;
    private String title;
    private String content;
    private String category;
    private List<String> tags;
}
