package com.example.firstspringproject.Tag;

import com.example.firstspringproject.Post.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TagMapper {
    TagMapper INSTANCE = Mappers.getMapper(TagMapper.class);

    @Mapping(source = "createdAt", target = "createTime", dateFormat = "YYYY/MM/DD-hh:mm:ss")
    TagDTO toTagDTO(Tag tag);

    default List<String> postListToStringList(List<Post> posts){
        List<String> stringList = new ArrayList<>();
        if(posts != null)
            posts.stream().forEach(post -> stringList.add(post.getTitle()+" - "+post.getContent()));
        return stringList;
    }

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "posts", ignore = true)
    Tag toTag(TagDTO tagDTO);
}
