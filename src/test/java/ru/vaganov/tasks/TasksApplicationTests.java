package ru.vaganov.tasks;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import ru.vaganov.tasks.exception.TaskNotFoundException;
import ru.vaganov.tasks.model.Task;
import ru.vaganov.tasks.repository.TaskRepository;
import ru.vaganov.tasks.service.TaskServiceImpl;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class TasksApplicationTests {

	@Mock
	private TaskRepository taskRepository;

	@InjectMocks
	private TaskServiceImpl taskService;

	private Task task;

	@BeforeEach
	public void setup(){
		task = Task.builder()
				.id(1L)
				.title("Ramesh")
				.description("Fadatare")
				.dueDate(LocalDate.now())
				.build();
	}

	// JUnit test for saveTask method
	@DisplayName("JUnit test for saveTask method")
	@Test
	public void givenTaskObject_whenSaveTask_thenReturnTaskObject(){
		// given - precondition or setup
		given(taskRepository.findById(task.getId()))
				.willReturn(Optional.empty());

		given(taskRepository.save(task)).willReturn(task);

		System.out.println(taskRepository);
		System.out.println(taskService);

		// when -  action or the behaviour that we are going test
		Task savedTask = taskService.saveTask(task);

		System.out.println(savedTask);
		// then - verify the output
		assertThat(savedTask).isNotNull();
	}

	// JUnit test for saveTask method
	@DisplayName("JUnit test for saveTask method which throws exception")
	@Test
	public void givenExistingID_whenSaveTask_thenThrowsException(){
		// given - precondition or setup
		given(taskRepository.findById(task.getId()))
				.willReturn(Optional.of(task));

		System.out.println(taskRepository);
		System.out.println(taskService);

		// when -  action or the behaviour that we are going test
		org.junit.jupiter.api.Assertions.assertThrows(TaskNotFoundException.class, () -> {
			taskService.saveTask(task);
		});

		// then
		verify(taskRepository, never()).save(any(Task.class));
	}

	// JUnit test for findAllTasks method
	@DisplayName("JUnit test for getAllTasks method")
	@Test
	public void givenTasksList_whenGetAllTasks_thenReturnTasksList(){
		// given - precondition or setup

		Task task1 = Task.builder()
				.id(2L)
				.title("Ramesh2")
				.description("Faddatare")
				.dueDate(LocalDate.now())
				.build();

		given(taskRepository.findAll()).willReturn(List.of(task,task1));

		// when -  action or the behaviour that we are going test
		List<Task> taskList = taskService.findAllTasks();

		// then - verify the output
		assertThat(taskList).isNotNull();
		assertThat(taskList.size()).isEqualTo(2);
	}

	// JUnit test for findAllTasks method
	@DisplayName("JUnit test for getAllTasks method (negative scenario)")
	@Test
	public void givenEmptyTasksList_whenGetAllTasks_thenReturnEmptyTasksList(){
		// given - precondition or setup

		Task task1 = Task.builder()
				.id(2L)
				.title("Ramesh2")
				.description("Faddatare")
				.dueDate(LocalDate.now())
				.build();

		given(taskRepository.findAll()).willReturn(Collections.emptyList());

		// when -  action or the behaviour that we are going test
		List<Task> taskList = taskService.findAllTasks();

		// then - verify the output
		assertThat(taskList).isEmpty();
		assertThat(taskList.size()).isEqualTo(0);
	}

	// JUnit test for findTaskById method
	@DisplayName("JUnit test for getTaskById method")
	@Test
	public void givenTaskId_whenGetTaskById_thenReturnTaskObject(){
		// given
		given(taskRepository.findById(1L)).willReturn(Optional.of(task));

		// when
		Task savedTask = taskService.findTaskById(task.getId());

		// then
		assertThat(savedTask).isNotNull();

	}

	// JUnit test for updateTask method
	@DisplayName("JUnit test for updateTask method")
	@Test
	public void givenTaskObject_whenUpdateTask_thenReturnUpdatedTask(){
		// given - precondition or setup
		given(taskRepository.save(task)).willReturn(task);
		task.setTitle("ram");
		task.setDescription("Rad vic");
		// when -  action or the behaviour that we are going test
		Task updatedTask = taskService.updateTask(task, task.getId());

		// then - verify the output
		assertThat(updatedTask.getTitle()).isEqualTo("ram");
		assertThat(updatedTask.getDescription()).isEqualTo("Rad vic");
	}

	// JUnit test for deleteTask method


	@DisplayName("JUnit test for deleteTask method")
	@Test
	public void givenTaskId_whenDeleteTask_thenNothing(){
		// given - precondition or setup
		long taskId = 1L;

		willDoNothing().given(taskRepository).deleteById(taskId);

		// when -  action or the behaviour that we are going test
		taskService.deleteTask(taskId);

		// then - verify the output
		verify(taskRepository, times(1)).deleteById(taskId);
	}

}
/*https://www.javaguides.net/2022/03/spring-boot-unit-testing-service-layer.html*/