package br.com.todolist.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import br.com.todolist.service.interfaces.ITaskService;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/task")
@CrossOrigin(origins = "*")
public class TaskController {
    
    @Autowired
    private ITaskService service;

    @GetMapping("/")
    public List<TaskEntity> listAll(){
        return service.listAll();
    }

    @GetMapping("/{id}")
    public Optional<TaskEntity> findById(@PathVariable Integer id){
        return service.findById(id);
    }

    @Transactional
    @PostMapping("/")
    public ResponseEntity<TaskEntity> save(@RequestBody TaskEntity task) {
        System.out.println("Recebido: " + task);
        task = service.save(task);
        return ResponseEntity.ok().body(task);
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<TaskEntity> update(@PathVariable Integer id, @RequestBody TaskEntity task){
        
        TaskEntity taskUpdated = service.updateTask(task);
        return ResponseEntity.ok().body(taskUpdated);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/status/{status}")
    public List<TaskEntity> filterByStatus(@PathVariable StatusTask status){
        return service.filterByStatus(status);
    }
}