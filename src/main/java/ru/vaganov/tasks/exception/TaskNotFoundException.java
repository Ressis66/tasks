package ru.vaganov.tasks.exception;

public class TaskNotFoundException extends RuntimeException{
  public TaskNotFoundException(Long id){
    super("Could not the user with id: " +id);
  }
}