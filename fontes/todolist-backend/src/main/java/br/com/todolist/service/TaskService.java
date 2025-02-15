package br.com.todolist.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.todolist.entities.StatusTask;
import br.com.todolist.entities.TaskEntity;
import br.com.todolist.repository.TaskRepository;

@Service
public class TaskService {
    
    @Autowired
    private TaskRepository repository;

    public List<TaskEntity> listAll() {
        return repository.findAll();
    }

    public Optional<TaskEntity> findById(Integer id){
        return repository.findById(id);
    }

    public TaskEntity save (TaskEntity task){
        return repository.save(task);
    }

    public void deletar (Integer id){
        repository.deleteById(id);
    }

    public List<TaskEntity> filterByStatus(StatusTask status){
        return repository.findByStatus(status);
    }
}
