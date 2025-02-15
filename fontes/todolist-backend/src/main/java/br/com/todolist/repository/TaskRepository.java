package br.com.todolist.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.todolist.entities.StatusTask;
import br.com.todolist.entities.TaskEntity;

public interface TaskRepository extends JpaRepository <TaskEntity, Integer> {
    List<TaskEntity> findByStatus(StatusTask status);

}
