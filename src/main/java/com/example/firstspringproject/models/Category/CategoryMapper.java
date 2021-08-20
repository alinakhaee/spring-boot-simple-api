package com.example.firstspringproject.models.Category;

import com.example.firstspringproject.models.Post.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    @Mapping(source = "createdAt", target = "createTime", dateFormat = "YYYY/MM/DD-hh:mm:ss")
    CategoryDTO toCategoryDTO(Category category);

    default List<String> postListToStringList(List<Post> posts){
        List<String> stringList = new ArrayList<>();
        if(posts != null)
            posts.stream().forEach(post -> stringList.add(post.getTitle()+" - "+post.getContent()));
        return stringList;
    }

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "posts", ignore = true)
    Category toCategory(CategoryDTO categoryDTO);
}
