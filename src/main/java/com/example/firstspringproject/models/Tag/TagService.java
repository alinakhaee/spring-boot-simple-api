package com.example.firstspringproject.models.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TagService {
    TagRepository tagRepository;

    @Autowired
    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public List<Tag> findAll(){
        return tagRepository.findAll();
    }

    public void create(Tag tag){
        tagRepository.save(tag);
    }

    public Tag delete(Long id) throws Exception {
        if(!tagRepository.existsById(id))
            throw new Exception("tag with id " + id + " does not exist");
        Tag tag = tagRepository.getById(id);
        tagRepository.deleteById(id);
        return tag;
    }

    @Transactional
    public Tag update(Long id, Tag newTag) throws Exception {
        Tag tag = tagRepository.findById(id).orElseThrow(() -> new Exception("tag with id " + id + " does not exist"));

        if( newTag.getTitle() != null )
            tag.setTitle(newTag.getTitle());

        return tag;
    }

    public Tag findOne(Long id) throws Exception {
        Optional<Tag> tagOptional = tagRepository.findById(id);
        if(tagOptional.isEmpty())
            throw new Exception("tag with id " + id + " does not exist");
        return tagOptional.get();
    }
}
