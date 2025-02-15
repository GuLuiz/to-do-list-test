package br.com.todolist.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.todolist.entities.StatusTask;
import br.com.todolist.entities.TaskEntity;
import br.com.todolist.service.TaskService;

@RestController
@RequestMapping("/task")
@CrossOrigin(origins = "*")
public class TaskController {
    
    @Autowired
    private TaskService service;

    @GetMapping("/")
    public List<TaskEntity> listAll(){
        return service.listAll();
    }

    @GetMapping("/{id}")
    public Optional<TaskEntity> findById(@PathVariable Integer id){
        return service.findById(id);
    }

    @PostMapping("/newTask")
    public TaskEntity save(TaskEntity task){
        return service.save(task);
    }

    @PutMapping("/{id}")
    public TaskEntity update(@PathVariable Integer id, @RequestBody TaskEntity task){
        task.setId(id);
        return service.save(task);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id){
        service.deletar(id);
    }

    @GetMapping("/status/{status}")
    public List<TaskEntity> filterByStatus(@PathVariable StatusTask status){
        return service.filterByStatus(status);
    }
}