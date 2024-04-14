package ru.vaganov.tasks.service;

import ru.vaganov.tasks.dto.TaskDto;
import ru.vaganov.tasks.model.Task;
import java.util.List;

public interface TaskService {
  Task saveTask(TaskDto newTaskDto);
  Task findTaskById(Long id);
  List<Task> findAllTasks();
  Task updateTask(TaskDto newTaskDto, Long id);
  String deleteTask(Long id);
}