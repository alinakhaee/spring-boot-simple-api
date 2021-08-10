package com.example.firstspringproject.Category;

import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class CategoryDTO {
    private String title;
    private String createTime;
    private List<String> posts;

}
