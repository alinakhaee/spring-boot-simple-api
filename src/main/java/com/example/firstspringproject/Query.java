package com.example.firstspringproject;

import com.example.firstspringproject.Category.Category;
import com.example.firstspringproject.Category.CategoryDTO;
import com.example.firstspringproject.Category.CategoryMapper;
import com.example.firstspringproject.Post.Post;
import com.example.firstspringproject.Post.PostDTO;
import com.example.firstspringproject.Post.PostMapper;
import com.example.firstspringproject.Tag.Tag;
import com.example.firstspringproject.Tag.TagRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Service
@RestController
@RequestMapping(path = "query")
@AllArgsConstructor
public class Query {
    @PersistenceContext
    EntityManager em;

    @GetMapping(path = "tags-with-no-post")
    public ResponseEntity<List<Tag>> getAllTagsWithNoPost(){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tag> cq = cb.createQuery(Tag.class);
        Root<Tag> tag = cq.from(Tag.class);

        Predicate noPosts = cb.isEmpty(tag.get("posts"));
        cq.where(noPosts);

        TypedQuery<Tag> query = em.createQuery(cq);
        return ResponseEntity.ok().body(query.getResultList());

    }

    @GetMapping(path = "categories-with-more-than-2-posts")
    public ResponseEntity<List<CategoryDTO>> getCategoriesWithMoreThanTwoPosts(){
        // select title from Category as C
        //      inner join ( select category_id, count(*) as count
        //                   from Post
        //                   group by category_id
        //                   having count>2 ) as Sub
        //      on C.id = Sub.category_id
        //


        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Category> cq = cb.createQuery(Category.class);
        Root<Category> category = cq.from(Category.class);
        Subquery<Long> postSubquery = cq.subquery(Long.class);
        Root<Post> post = postSubquery.from(Post.class);

        postSubquery.select(cb.count(post)).groupBy(post.get("category"));


        cq.select(category).where(category.get("id").in(postSubquery));

        TypedQuery<Category> query = em.createQuery(cq);
        query.getResultStream().forEach(category1 -> System.out.println(category1.getTitle()));
//        List<CategoryDTO> postDTOS = new ArrayList<>();
//        List<Category> posts =query.getResultList();
//        posts.forEach(post1 -> postDTOS.add(CategoryMapper.INSTANCE.toCategoryDTO(post1)));
        return ResponseEntity.ok().body(List.of(new CategoryDTO()));
    }
}
