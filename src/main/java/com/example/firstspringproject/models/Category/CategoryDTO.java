package com.example.firstspringproject.models.Category;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class CategoryDTO {
    @NotBlank(message = "title must not be blank")
    @Size(min = 3, max = 30, message = "content title must be between 3 to 30 chracters")
    private String title;
    private String createTime;
    private List<String> posts;

}
