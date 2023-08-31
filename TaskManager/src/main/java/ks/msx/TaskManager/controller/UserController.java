package ks.msx.TaskManager.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ks.msx.TaskManager.dto.UserDTO;
import ks.msx.TaskManager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @RequestMapping("/register")
    public void registerUser(@RequestParam(name = "username")String username,
                             @RequestParam(name = "password")String password,
                             HttpServletRequest request,
                             HttpServletResponse response) throws ServletException, IOException {
        userService.saveUser(UserDTO.builder()
                        .username(username)
                        .password(password)
                .build(), request);
        response.sendRedirect("/api/v1/main");
    }
}
