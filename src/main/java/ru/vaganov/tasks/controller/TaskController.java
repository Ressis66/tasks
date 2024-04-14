package ru.vaganov.tasks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.vaganov.tasks.model.Task;
import ru.vaganov.tasks.service.TaskService;

import java.util.List;


@RestController
public class TaskController {

  @Autowired
  private TaskService taskService;

  @PostMapping("/task")
  Task newTask(@RequestBody Task newTask){
    return taskService.saveTask(newTask);
  }

  @GetMapping("/tasks")
  List<Task> getAllTasks() {
    return  taskService.findAllTasks();
  }

  @GetMapping("/task/{id}")
  Task getTaskById(@PathVariable Long id){
    return taskService.findTaskById(id);
  }

  @PutMapping("/task/{id}")
  Task updateTask(@RequestBody Task newTask, @PathVariable Long id) {
    return taskService.updateTask(newTask, id);
  }

  @DeleteMapping("/task/{id}")
  String deleteTask(@PathVariable Long id){
     return taskService.deleteTask(id);
  }
}
