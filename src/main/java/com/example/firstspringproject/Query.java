package com.example.firstspringproject;

import com.example.firstspringproject.Category.Category;
import com.example.firstspringproject.Category.CategoryDTO;
import com.example.firstspringproject.Category.CategoryMapper;
import com.example.firstspringproject.Category.CategoryRepository;
import com.example.firstspringproject.Post.Post;
import com.example.firstspringproject.Post.PostDTO;
import com.example.firstspringproject.Post.PostMapper;
import com.example.firstspringproject.Tag.Tag;
import com.example.firstspringproject.Tag.TagDTO;
import com.example.firstspringproject.Tag.TagMapper;
import com.example.firstspringproject.Tag.TagRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RestController
@RequestMapping(path = "query")
@AllArgsConstructor
public class Query {
    @PersistenceContext
    EntityManager em;
    CategoryRepository categoryRepository;

    @GetMapping(path = "tags-with-no-post")
    public ResponseEntity<List<TagDTO>> getAllTagsWithNoPost() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tag> cq = cb.createQuery(Tag.class);
        Root<Tag> tag = cq.from(Tag.class);

        Predicate noPosts = cb.isEmpty(tag.get("posts"));
        cq.where(noPosts);

        TypedQuery<Tag> query = em.createQuery(cq);
        List<TagDTO> tagDTOs = new ArrayList<>();
        query.getResultStream().forEach(tag1 -> tagDTOs.add(TagMapper.INSTANCE.toTagDTO(tag1)));

        return ResponseEntity.ok().body(tagDTOs);

    }

    @GetMapping(path = "categories-with-more-than-n-posts")
    public ResponseEntity<List<CategoryDTO>> getCategoriesWithMoreThanTwoPosts(@RequestParam(name = "num", required = false, defaultValue = "1") int num) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> pcq = cb.createQuery(Tuple.class);
        Root<Post> post = pcq.from(Post.class);
        Join<Post, Category> category = post.join("category");

        pcq.multiselect(category.get("id").alias("id"), category.get("title").alias("title"), cb.count(post).alias("posts number"));
        pcq.groupBy(category.get("id")).having(cb.ge(cb.count(post), num));

        TypedQuery<Tuple> query = em.createQuery(pcq);
        List<Tuple> result = query.getResultList();
        List<CategoryDTO> categoryDTOs = new ArrayList<>();
        for (Tuple tuple : result) {
            System.out.println(tuple.get("id") + " " + tuple.get("title") + " " + tuple.get("posts number"));
            categoryRepository.findById((Long) tuple.get("id")).ifPresent(category1 -> {
                categoryDTOs.add(CategoryMapper.INSTANCE.toCategoryDTO(category1));
            });
        }
        return ResponseEntity.ok().body(categoryDTOs);
    }

    @GetMapping(path = "tags-with-word-in-posts-content")
    public ResponseEntity<List<TagDTO>> getTagsWithWordInPostsContent(@RequestParam(name = "word") String word) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tag> tcq = cb.createQuery(Tag.class);
        Root<Tag> tag = tcq.from(Tag.class);
        Join<Tag, Post> post = tag.join("posts");

        Predicate containsWord = cb.like(post.get("content"), '%' + word + '%');
        tcq.where(containsWord).groupBy(tag.get("id"));

        TypedQuery<Tag> query = em.createQuery(tcq);
        List<TagDTO> tagDTOs = new ArrayList<>();
        query.getResultStream().forEach(tag1 -> tagDTOs.add(TagMapper.INSTANCE.toTagDTO(tag1)));

        return ResponseEntity.ok().body(tagDTOs);
    }

    @GetMapping(path = "categories-which-has-posts-with-tags")
    public ResponseEntity<List<CategoryDTO>> getCategoriesWhichHasPostsWithTags(@RequestParam(value = "tag") String[] tagNames) {
//        EntityGraph<Category> entityGraph = em.createEntityGraph(Category.class);
//        entityGraph.addAttributeNodes("title");
//        entityGraph.addAttributeNodes("createdAt");
//        entityGraph.addSubgraph("posts").addAttributeNodes("title");

        System.out.println(Arrays.toString(tagNames));
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Category> ccq = cb.createQuery(Category.class);
        Root<Tag> tag = ccq.from(Tag.class);
        Join<Tag, Post> post = tag.join("posts");
        Join<Post, Category> category = post.join("category");

        Predicate tagIn = tag.get("title").in(List.of(tagNames));
        ccq.select(category).where(tagIn).groupBy(category.get("id"));

        TypedQuery<Category> query = em.createQuery(ccq);
//        query.setHint("javax.persistence.loadgraph", entityGraph);
        List<CategoryDTO> categoryDTOs = new ArrayList<>();
        query.getResultStream().forEach(category1 -> categoryDTOs.add(CategoryMapper.INSTANCE.toCategoryDTO(category1)));

        return ResponseEntity.ok().body(categoryDTOs);


    }
}
