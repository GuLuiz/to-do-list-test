import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { TaskService } from '../../../services/task.service';
import { MatIconModule } from '@angular/material/icon';
import { TaskInterface } from '../../../interfaces/TaskInterface';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'list',
  imports: [CommonModule, RouterModule, MatIconModule, FormsModule],
  templateUrl: './list.component.html',
  styleUrl: './list.component.scss'
})
export class ListComponent {

  colunas: string[] = ['Titulo', 'Descricao', 'Status', 'Creation Date'];
  statusList = ['PENDENTE', 'EM_ANDAMENTO', 'CONCLUIDO'];
  tasks: TaskInterface[] = [];

  constructor(private taskService: TaskService, private router: Router) {}



  ngOnInit() {
    this.loadTasks();
  }

  loadTasks() {
    this.taskService.list().subscribe((tasks: any) => {
      this.tasks = tasks;
    });
  }

  onStatusChange(task: TaskInterface): void {
    if (task.id) {
      this.taskService.updateById(task.id.toString(), task.descricao, task.status, task.titulo)
        .subscribe(() => {
          console.log(`Status da tarefa "${task.titulo}" atualizado para "${task.status}"`);
        }, error => {
          console.error("Erro ao atualizar status", error);
        });
    }
  }

  add(): void{
    this.router.navigate(['/add']);
  }
  
}
