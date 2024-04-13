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
  private TaskService userService;

  @PostMapping("/user")
  Task newTask(@RequestBody Task newTask){
    return userService.saveTask(newTask);
  }

  @GetMapping("/users")
  List<Task> getAllTasks() {
    return  userService.findAllTasks();
  }

  @GetMapping("/user/{id}")
  Task getTaskById(@PathVariable Long id){
    return userService.findTaskById(id);
  }

  @PutMapping("/user/{id}")
  Task updateTask(@RequestBody Task newTask, @PathVariable Long id) {
    return userService.updateTask(newTask, id);
  }

  @DeleteMapping("/user/{id}")
  String deleteTask(@PathVariable Long id){
     return userService.deleteTask(id);
  }
}
