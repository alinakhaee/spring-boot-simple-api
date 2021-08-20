package com.example.firstspringproject.models.User;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    public PasswordEncoder passwordEncoder;
    public UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userRepository.findByUsername(username);
        if(user == null)
            throw new UsernameNotFoundException("username not found");
        return new User(user.getUsername(), user.getPassword(), new ArrayList<SimpleGrantedAuthority>());
    }

    public List<AppUser> findAll() {
        return userRepository.findAll();
    }

    public void create(AppUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public AppUser delete(String username) throws Exception {
        if (!userRepository.existsById(username))
            throw new Exception("post with username " + username + " does not exist");
        AppUser post = userRepository.getById(username);
        userRepository.deleteById(username);
        return post;
    }

    @Transactional
    public AppUser update(String username, AppUser newUser) throws Exception {
        AppUser user = userRepository.findById(username).orElseThrow(() -> new Exception("user with username " + username + " does not exist"));

        if (newUser.getUsername() != null)
            user.setUsername(newUser.getUsername());
        if (newUser.getPassword() != null)
            user.setPassword(newUser.getPassword());
        if (newUser.getEmail() != null)
            user.setEmail(newUser.getEmail());

        return user;
    }

    public AppUser findOne(String username) throws Exception {
        Optional<AppUser> postOptional = userRepository.findById(username);
        if (postOptional.isEmpty())
            throw new Exception("user with username " + username + " does not exist");
        return postOptional.get();
    }

    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String authorizedHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(authorizedHeader != null){
            try{
                String refreshToken = authorizedHeader;
                Algorithm algorithm = Algorithm.HMAC256("secret key".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refreshToken);
                String username = decodedJWT.getSubject();
                String accesToken = JWT.create()
                        .withSubject(username)
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10*60*1000))
                        .withIssuer(request.getRequestURL().toString())
                        .sign(algorithm);
                response.setHeader("access-token", accesToken);
                response.setHeader("refresh-token", refreshToken);

            } catch (Exception e){
                response.setHeader("error", e.getMessage());
                response.setStatus(403);

            }
        }
        else throw new Exception("didnt provide refresh token");
    }


}
