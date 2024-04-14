package ru.vaganov.tasks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.vaganov.tasks.dto.TaskDto;
import ru.vaganov.tasks.model.Task;
import ru.vaganov.tasks.service.TaskService;

import java.util.List;


@RestController
public class TaskController {

  @Autowired
  private TaskService taskService;

  @PostMapping("/task")
  public Task newTask(@RequestBody TaskDto newTaskDto){
    return taskService.saveTask(newTaskDto);
  }

  @GetMapping("/tasks")
  public List<Task> getAllTasks() {
    return  taskService.findAllTasks();
  }

  @GetMapping("/task/{id}")
  public Task getTaskById(@PathVariable Long id){
    return taskService.findTaskById(id);
  }

  @PutMapping("/task/{id}")
  public Task updateTask(@RequestBody TaskDto newTaskDto, @PathVariable Long id) {
    return taskService.updateTask(newTaskDto, id);
  }

  @DeleteMapping("/task/{id}")
  public String deleteTask(@PathVariable Long id){
     return taskService.deleteTask(id);
  }
}
