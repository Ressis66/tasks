package ru.vaganov.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.vaganov.tasks.controller.TaskController;
import ru.vaganov.tasks.dto.TaskDto;
import ru.vaganov.tasks.model.Task;
import ru.vaganov.tasks.service.TaskService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class TasksApplicationTests {


	@Mock
	private TaskService taskService;

	@InjectMocks
	private TaskController taskController;

	private List<Task> tasks;

	private TaskDto taskDto;

	@BeforeEach
	void setUp() {
		tasks = Arrays.asList(new Task(1L, "Task 1", "Description 1", LocalDate.now(), true),
				new Task(2L, "Task 2", "Description 2", LocalDate.now(), false));

	}

	@Test
	void getTasks_ShouldReturnAllTasks() {
		when(taskService.findAllTasks()).thenReturn(tasks);
		List<Task> response = taskController.getAllTasks();
		assertEquals(2, response.size());
	}
	
	@Test
	void getTask_ShouldReturnTaskById() {
		when(taskService.findTaskById(anyLong())).thenReturn(tasks.get(0));
		Task response = taskController.getTaskById(1L);
		assertEquals(tasks.get(0), response);
	}

	// Тест для метода createTask
	@Test
	void createTask_ShouldSaveNewTask() {
		taskDto= TaskDto.builder().build();
		taskDto.setTitle("Task 1");
		taskDto.setDescription("Description 1");
		taskDto.setDueDate(LocalDate.now());
		taskDto.setCompleted(true);
		Task newTask = new Task(1L, "Task 1", "Description 1", LocalDate.now(), true);
		when(taskService.saveTask(any(TaskDto.class))).thenReturn(newTask);
		assertEquals(taskDto.getDescription(), taskController.newTask(taskDto).getDescription());

	}

	// Тест для метода updateTask
	@Test
	void updateTask_ShouldUpdateExistingTask() {
		Task existingTask = tasks.get(0);
		taskDto= TaskDto.builder().build();
		taskDto.setTitle("Task 1");
		taskDto.setDescription("Description 1");
		taskDto.setDueDate(LocalDate.now());
		taskDto.setCompleted(true);
		when(taskService.updateTask(any(TaskDto.class), eq(existingTask.getId()))).thenReturn(existingTask);
		assertEquals(taskDto.getDescription(), taskController.updateTask(taskDto,tasks.get(0).getId()).getDescription());
	}

	// Тест для метода deleteTask
	@Test
	void deleteTask_ShouldDeleteTaskById() {
		Long id = anyLong();
		when(taskService.deleteTask(id)).thenReturn("Id " + id +" deleted successfully!");
		String result = taskController.deleteTask(1L);
		assertEquals("Id " + 0 +" deleted successfully!", result);
	}



}
