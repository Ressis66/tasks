package ru.vaganov.tasks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vaganov.tasks.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
