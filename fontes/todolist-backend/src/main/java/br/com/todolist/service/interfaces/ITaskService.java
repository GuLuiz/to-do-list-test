package br.com.todolist.service.interfaces;

import java.util.List;
import java.util.Optional;

import br.com.todolist.entities.StatusTask;
import br.com.todolist.entities.TaskEntity;

public interface ITaskService {

    List<TaskEntity> listAll();

    Optional<TaskEntity> findById(Integer id);

    TaskEntity save(TaskEntity task);

    void delete(Integer id);

    List<TaskEntity> filterByStatus(StatusTask status);

    TaskEntity updateTask(TaskEntity newTaskData);

}