package ks.msx.TaskManager.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
public class LoginController {

    @RequestMapping("/login")
    public String redirectToLoginPage(Authentication auth){
        if (auth != null && auth.isAuthenticated() && auth.getName() != null) return "main";
        return "login";
    }

    @RequestMapping("/register_page")
    public String redirectToRegisterPage(){
        return "register";
    }

    @RequestMapping("/logout")
    public void logout(HttpServletRequest request) throws ServletException {
        request.logout();
    }

    @RequestMapping("success")
    public void afterSuccessLoginRedirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/");
    }
}
