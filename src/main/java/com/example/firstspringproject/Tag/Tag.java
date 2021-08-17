package com.example.firstspringproject.Tag;

import com.example.firstspringproject.Post.Post;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

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
    private Set<Post> posts;

    public Tag(String title) {
        this.title = title;
        this.createdAt = LocalDateTime.now();
    }

}

