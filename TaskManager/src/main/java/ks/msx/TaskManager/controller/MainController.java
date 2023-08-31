package ks.msx.TaskManager.controller;

import jakarta.servlet.http.HttpServletResponse;
import ks.msx.TaskManager.service.TaskService;
import ks.msx.TaskManager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.security.Principal;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class MainController {
    private final UserService userService;
    private final TaskService taskService;

    @GetMapping
    public void redirectToMainPage(HttpServletResponse response) throws IOException {
            response.sendRedirect("/api/v1/main");
            response.setStatus(200);
    }

    @GetMapping("api/v1/main")
    public String getMainPage(Model model, Principal principal){
        model.addAttribute("user", userService.loadUserByUsername(principal.getName()));
        model.addAttribute("todo", taskService.getTaskListTODO(principal.getName()));
        model.addAttribute("done", taskService.getTaskListDONE(principal.getName()));
        return "main";
    }
}
