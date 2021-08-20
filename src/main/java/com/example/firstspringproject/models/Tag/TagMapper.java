package com.example.firstspringproject.models.Tag;

import com.example.firstspringproject.models.Post.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface TagMapper {
    TagMapper INSTANCE = Mappers.getMapper(TagMapper.class);

    @Mapping(source = "createdAt", target = "createTime", dateFormat = "YYYY/MM/DD-hh:mm:ss")
    TagDTO toTagDTO(Tag tag);

    default List<String> postListToStringList(Set<Post> posts){
        List<String> stringList = new ArrayList<>();
        if(posts != null)
            posts.stream().forEach(post -> stringList.add(post.getTitle()+" - "+post.getContent()));
        return stringList;
    }

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "posts", ignore = true)
    Tag toTag(TagDTO tagDTO);
}
