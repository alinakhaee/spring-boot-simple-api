package com.example.firstspringproject.models.Post;

import com.example.firstspringproject.models.Category.Category;
import com.example.firstspringproject.models.Tag.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Mapper
public interface PostMapper {
    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "tags", ignore = true)
    @Mapping(target = "id", ignore = true)
    Post toPost(PostDTO postDTO);

    @Mapping(source = "createdAt", target = "createTime", dateFormat = "YYYY/MM/DD-hh:mm:ss")
    PostDTO toPostDTO(Post post);

    default String toString(Category category){return category == null ? " " : category.getTitle();}

    default List<String> toStringList(Set<Tag> tags){
        List<String> stringList = new ArrayList<>();
        if(tags != null)
            tags.stream().forEach(tag -> stringList.add(tag.getTitle()));
        return stringList;
    }
}
