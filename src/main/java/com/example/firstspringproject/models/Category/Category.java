package com.example.firstspringproject.models.Category;

import com.example.firstspringproject.models.Post.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
@Setter
@Getter
@ToString
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_seq")
    @SequenceGenerator(name = "category_seq", sequenceName = "category_seq", allocationSize = 1)
    private Long id;
    @Column(nullable = false, length = 50, unique = true)
    private String title;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts;

    public Category(String title) {
        this.title = title;
        this.createdAt = LocalDateTime.now();
    }
}

