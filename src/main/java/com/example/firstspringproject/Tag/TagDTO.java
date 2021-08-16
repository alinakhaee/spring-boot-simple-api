package com.example.firstspringproject.Tag;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class TagDTO {
    @NotBlank(message = "title must not be blank")
    @Size(min = 3, max = 30, message = "tag title must be between 3 to 30 chracters")
    private String title;
    private String createTime;
    private List<String> posts;
}
