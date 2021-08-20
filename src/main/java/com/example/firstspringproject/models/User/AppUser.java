package com.example.firstspringproject.models.User;

import com.example.firstspringproject.models.Post.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
@ToString
@NoArgsConstructor
public class AppUser {
    @Id
    private String username;
    @Column(nullable = false, unique = true)
    @Email
    private String email;
    @Size(min = 6)
    @Column(nullable = false)
    private String password;
    @OneToMany(mappedBy = "user")
    private Set<Post> posts;

    public AppUser(String username, @Email String email, @Size(min = 6) String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
