package com.example.firstspringproject.Tag;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class TagDTO {
    private String title;
    private String createTime;
    private List<String> posts;
}
