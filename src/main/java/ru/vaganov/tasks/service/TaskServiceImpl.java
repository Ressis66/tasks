package ru.vaganov.tasks.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vaganov.tasks.dto.TaskDto;
import ru.vaganov.tasks.exception.TaskNotFoundException;
import ru.vaganov.tasks.model.Task;
import ru.vaganov.tasks.repository.TaskRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService{

  @Autowired
  private TaskRepository repository;

  @Transactional
  public Task saveTask(TaskDto newTaskDto){
    Task task = new Task();
    task.setTitle(newTaskDto.getTitle());
    task.setDescription(newTaskDto.getDescription());
    task.setDueDate(newTaskDto.getDueDate());
    task.setCompleted(newTaskDto.isCompleted());
    return repository.save(task);
  }

  @Transactional
   public List<Task> findAllTasks(){
     return repository.findAll();
   }

  @Transactional
  public Task findTaskById(Long id){
    return repository.findById(id).orElseThrow(()-> new TaskNotFoundException(id));
  }

  @Transactional
  public Task updateTask(TaskDto newTaskDto, Long id){
    return repository.findById(id).map(task -> {
     task.setTitle(newTaskDto.getTitle());
     task.setDescription(newTaskDto.getDescription());
     task.setDueDate(newTaskDto.getDueDate());
     task.setCompleted(newTaskDto.isCompleted());
      return  repository.save(task);
    }).orElseThrow(()-> new TaskNotFoundException(id));
  }

  @Transactional
  public String deleteTask(Long id){
    if(!repository.existsById(id)){
      throw  new TaskNotFoundException(id);
    }
    repository.deleteById(id);
    return"Id " +id +" deleted successfully!";
  }
}

