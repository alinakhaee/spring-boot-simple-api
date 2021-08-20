package com.example.firstspringproject;

import com.example.firstspringproject.models.User.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@AllArgsConstructor
public class Controller {
    UserService userService;

    @PostMapping(path = "refresh-token")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws Exception {
        userService.refreshToken(request, response);

    }
}
