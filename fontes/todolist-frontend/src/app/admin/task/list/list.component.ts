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
  selectedStatus: string = '';

  constructor(private taskService: TaskService, private router: Router) {}



  ngOnInit() {
    this.loadTasks();
  }

  loadTasks(): void {
    if (this.selectedStatus) {
      this.taskService.findByStatus(this.selectedStatus).subscribe((tasks) => {
        this.tasks = Array.isArray(tasks) ? tasks : [tasks];
      });
    } else {
      this.taskService.list().subscribe((tasks) => {
        this.tasks = tasks as TaskInterface[];
      });
    }
  }

  onStatusFilterChange(): void {
    this.loadTasks();
  }

  onStatusChange(task: TaskInterface): void {
    if (task.id) {
      this.taskService.updateById(task.id.toString(), task.descricao, task.status, task.titulo)
        .subscribe({
          next: () => {
            console.log(`Status da tarefa "${task.titulo}" atualizado para "${task.status}"`);
          },
          error: (error) => {
            console.error("Erro ao atualizar status", error);
          }
        });
    }
  }
  

  add(): void{
    this.router.navigate(['/add']);
  }
  
}
