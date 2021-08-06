package com.example.firstspringproject.Post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    public PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    public List<Post> findAll(){
        return postRepository.findAll();
    }

    public void create(Post post){
        postRepository.save(post);
    }

    public void delete(Long id) throws Exception {
        if(!postRepository.existsById(id))
            throw new Exception("post with id " + id + " does not exist");
        postRepository.deleteById(id);
    }

    @Transactional
    public void update(Long id, Post newPost) throws Exception {
        Post post = postRepository.findById(id).orElseThrow(() -> new Exception("post with id " + id + " does not exist"));

        if( newPost.getTitle() != null )
            post.setTitle(newPost.getTitle());
        if(newPost.getContent() != null)
            post.setContent(newPost.getContent());
    }

    public Post findOne(Long id) throws Exception {
        Optional<Post> postOptional = postRepository.findById(id);
        if(postOptional.isEmpty())
            throw new Exception("post with id " + id + " does not exist");
        return postOptional.get();
    }
}
