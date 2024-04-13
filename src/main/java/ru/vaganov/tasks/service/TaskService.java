package ru.vaganov.tasks.service;

import ru.vaganov.tasks.model.Task;
import java.util.List;

public interface TaskService {
  Task saveTask(Task task);
  Task findTaskById(Long id);
  List<Task> findAllTasks();
  Task updateTask(Task newTask, Long id);
  String deleteTask(Long id);
}