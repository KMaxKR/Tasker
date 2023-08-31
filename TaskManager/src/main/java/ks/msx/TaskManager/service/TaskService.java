package ks.msx.TaskManager.service;

import ks.msx.TaskManager.dto.TaskDTO;
import ks.msx.TaskManager.entity.Task;
import ks.msx.TaskManager.entity.TaskType;
import ks.msx.TaskManager.entity.User;
import ks.msx.TaskManager.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserService userService;

    public List<Task> getAllTasksById(String username){
        User user = userService.getCurrentUser(username);
        List<Long> idList = taskRepository.findAll().stream().filter(task -> task.getUser_id_task().equals(user.getId())).map(Task::getId).toList();
        return taskRepository.findAllById(idList);
    }

    public List<Task> getTaskListTODO(String username){
        return getAllTasksById(username).stream().filter(task -> task.getType().equals(TaskType.TODO)).toList();
    }

    public List<Task> getTaskListDONE(String username){
        return getAllTasksById(username).stream().filter(task -> task.getType().equals(TaskType.DONE)).toList();
    }

    public void createNewTask(TaskDTO dto, String username){
        taskRepository.save(
                Task.builder()
                        .content(dto.getTask())
                        .type(TaskType.TODO)
                        .date(new Date())
                        .user_id_task(userService.getCurrentUser(username).getId())
                        .build()
        );
    }

    public void completedTask(Long task_id){
        Task task = taskRepository.findById(task_id).orElseThrow();
        task.setType(TaskType.DONE);
        taskRepository.save(task);
    }

    public void deleteCompletedTask(Long task_id){
        taskRepository.deleteById(task_id);
    }
}
