package com.example.firstspringproject.models.Post;

import com.example.firstspringproject.models.Category.Category;
import com.example.firstspringproject.models.Category.CategoryDTO;
import com.example.firstspringproject.models.Category.CategoryRepository;
import com.example.firstspringproject.models.Tag.Tag;
import com.example.firstspringproject.models.Tag.TagDTO;
import com.example.firstspringproject.models.Tag.TagRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PostService {

    public PostRepository postRepository;
    public CategoryRepository categoryRepository;
    public TagRepository tagRepository;

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public void create(Post post) {
        postRepository.save(post);
    }

    @Transactional
    public Post setCategory(Long postId, CategoryDTO categoryDTO) throws Exception {
        Post post = postRepository.findById(postId).orElseThrow(() -> new Exception("post with id " + postId + " does not exist"));
        Category category = categoryRepository.findByTitle(categoryDTO.getTitle()).orElseThrow(() -> new Exception("category  " + categoryDTO.getTitle() + " does not exist"));
        post.setCategory(category);
        category.getPosts().add(post);
        return post;
    }

    public Post delete(Long id) throws Exception {
        if (!postRepository.existsById(id))
            throw new Exception("post with id " + id + " does not exist");
        Post post = postRepository.getById(id);
        postRepository.deleteById(id);
        return post;
    }

    @Transactional
    public Post update(Long id, Post newPost) throws Exception {
        Post post = postRepository.findById(id).orElseThrow(() -> new Exception("post with id " + id + " does not exist"));

        if (newPost.getTitle() != null)
            post.setTitle(newPost.getTitle());
        if (newPost.getContent() != null)
            post.setContent(newPost.getContent());

        return post;
    }

    public Post findOne(Long id) throws Exception {
        Optional<Post> postOptional = postRepository.findById(id);
        if (postOptional.isEmpty())
            throw new Exception("post with id " + id + " does not exist");
        return postOptional.get();
    }

    @Transactional
    public Post setTags(Long postId, TagDTO tagDTO) throws Exception {
        Post post = postRepository.findById(postId).orElseThrow(() -> new Exception("post with id " + postId + " does not exist"));
        Tag tag = tagRepository.findByTitle(tagDTO.getTitle()).orElseThrow(() -> new Exception("tag  " + tagDTO.getTitle() + " does not exist"));
//        tagDTOs.forEach(tagDTO -> {
//            Optional<Tag> tagOptional = tagRepository.findByTitle(tagDTO.getTitle());
//            if(tagOptional.isEmpty())
//                return;
//            Tag tag = tagOptional.get();
//            post.getTags().add(tag);
//            tag.getPosts().add(post);
//        });
        if(post.getTags().contains(tag))
            throw new Exception("this tag is already defined");

        post.getTags().add(tag);
        tag.getPosts().add(post);

        return post;

    }

    @Transactional
    public Post deleteCategory(Long id) throws Exception {
        Post post = postRepository.findById(id).orElseThrow(() -> new Exception("post with id " + id + " does not exist"));
        post.setCategory(null);
        return post;
    }

    @Transactional
    public Post deleteTag(Long postId, Long tagId) throws Exception{
        Post post = postRepository.findById(postId).orElseThrow(() -> new Exception("post with id " + postId + " does not exist"));
        Tag tag = tagRepository.findById(tagId).orElseThrow(() -> new Exception("tag with id " + tagId + " does not exist"));
        if(!post.getTags().contains(tag))
            throw new Exception("post doesnt have this tag at all");

        post.getTags().remove(tag);
        tag.getPosts().remove(post);

        return post;

    }
}
