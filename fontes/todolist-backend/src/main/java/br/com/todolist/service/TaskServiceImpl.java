package br.com.todolist.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.todolist.entities.StatusTask;
import br.com.todolist.entities.TaskEntity;
import br.com.todolist.repository.TaskRepository;
import br.com.todolist.service.interfaces.ITaskService;

@Service
public class TaskServiceImpl implements ITaskService {
    
    @Autowired
    private TaskRepository repository;

    @Override
    public List<TaskEntity> listAll() {
        return repository.findAll();
    }

    @Override
    public Optional<TaskEntity> findById(Integer id){
        Optional<TaskEntity> task = repository.findById(id);
        if(task.isPresent()){
            return repository.findById(id); 
        }
        else{
            throw new RuntimeException("Task com o id "+ id + " não encontrada!");
        }
    }

    @Override
    public TaskEntity save (TaskEntity task){

        task.setStatus(StatusTask.PENDENTE);
        task = repository.save(task);

        return task;
    }

    @Override
    public void delete (Integer id){
        Optional<TaskEntity> task = repository.findById(id);
        if(task.isPresent()){
            repository.deleteById(id);
        }
        else{
            throw new RuntimeException("Tarefa não encontrada para o ID " + id);
        }
    }

    @Override
    public List<TaskEntity> filterByStatus(StatusTask status){
        return repository.findByStatus(status);
    }

    @Override
    public TaskEntity updateTask(@RequestBody TaskEntity newTaskData) {
        
        TaskEntity existingTask = repository.findById(newTaskData.getId()).orElse(null);
    
        existingTask.setTitulo(newTaskData.getTitulo());
        existingTask.setDescricao(newTaskData.getDescricao());
        existingTask.setStatus(newTaskData.getStatus());
        
        newTaskData = repository.save(existingTask);
        System.out.println(newTaskData);
        return newTaskData;
    }
}
