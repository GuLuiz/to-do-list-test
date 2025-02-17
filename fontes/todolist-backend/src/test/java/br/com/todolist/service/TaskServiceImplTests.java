package br.com.todolist.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.todolist.entities.StatusTask;
import br.com.todolist.entities.TaskEntity;
import br.com.todolist.repository.TaskRepository;

public class TaskServiceImplTests {
    
    @InjectMocks
    private TaskServiceImpl taskService;

    @Mock
    private TaskRepository taskRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListAll(){
        TaskEntity teste1 = new TaskEntity(1,"testando","Teste1", StatusTask.PENDENTE);
        TaskEntity teste2 = new TaskEntity(1, "testando novamente","Teste2", StatusTask.CONCLUIDO);

        when(taskRepository.findAll()).thenReturn(Arrays.asList(teste1, teste2));

        List<TaskEntity> result = taskService.listAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Teste1", result.get(0).getTitulo());
        assertEquals(StatusTask.CONCLUIDO, result.get(1).getStatus());
    }

    @Test
    void testDelete(){
        TaskEntity teste1 = new TaskEntity(1,"testando","Teste1", StatusTask.PENDENTE);

        when(taskRepository.findById(1)).thenReturn(Optional.of(teste1));

        assertDoesNotThrow(() -> taskService.delete(1));
    }

    @Test
    void testSave(){
        TaskEntity teste1 = new TaskEntity(1, "testando","Teste1", StatusTask.PENDENTE);

        when(taskRepository.save(any(TaskEntity.class))).thenReturn(teste1);

        TaskEntity result = taskService.save(teste1);

        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testUpdateTask() {
        TaskEntity taskExistente = new TaskEntity(1, "Descrição Antiga", "Tarefa Antiga", StatusTask.PENDENTE);
        TaskEntity novaTask = new TaskEntity(1, "Descrição Nova", "Tarefa Nova", StatusTask.CONCLUIDO);

        when(taskRepository.findById(1)).thenReturn(Optional.of(taskExistente));
        when(taskRepository.save(any(TaskEntity.class))).thenReturn(novaTask);

        TaskEntity result = taskService.updateTask(novaTask);

        assertNotNull(result);
        assertEquals("Tarefa Nova", result.getTitulo());
        assertEquals("Descrição Nova", result.getDescricao());
        assertEquals(StatusTask.CONCLUIDO, result.getStatus());
    }

}
