package com.example.firstspringproject.Tag;

import com.example.firstspringproject.Post.Post;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tag_seq")
    @SequenceGenerator(name = "tag_seq", sequenceName = "tag_seq", allocationSize = 1)
    private Long id;
    @Column(nullable = false, length = 50, unique = true)
    private String title;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @ManyToMany(mappedBy = "tags")
    @JsonIgnore
    private List<Post> posts;

    public Tag(String title) {
        this.title = title;
        this.createdAt = LocalDateTime.now();
    }

}

