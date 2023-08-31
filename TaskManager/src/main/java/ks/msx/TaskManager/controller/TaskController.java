package ks.msx.TaskManager.controller;

import jakarta.servlet.http.HttpServletResponse;
import ks.msx.TaskManager.dto.TaskDTO;
import ks.msx.TaskManager.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @RequestMapping("/api/v1/main/completed/{id}")
    public void markAsCompleted(@PathVariable(name = "id")Long id, HttpServletResponse response) throws IOException {
        taskService.completedTask(id);
        response.sendRedirect("/");
    }

    @RequestMapping("/api/v1/main/create/task")
    public String redirectToCreateTaskPage(){
        return "create";
    }


    @RequestMapping("/api/v1/main/create/new/task")
    public void createNewTask(@RequestParam(name = "content")String content,
                              Principal principal,
                              HttpServletResponse response) throws IOException {
        taskService.createNewTask(TaskDTO.builder()
                        .task(content)
                .build(), principal.getName());
        response.sendRedirect("/");
    }

    @RequestMapping("/api/v1/main/task/delete/{id}")
    public void deleteTask(@PathVariable(name = "id")Long id, HttpServletResponse response) throws IOException {
        taskService.deleteCompletedTask(id);
        response.sendRedirect("/");
    }
}
