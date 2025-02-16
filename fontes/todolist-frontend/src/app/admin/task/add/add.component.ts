import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { TaskService } from '../../../services/task.service';
import { Router } from '@angular/router';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'add',
  imports: [ReactiveFormsModule, CommonModule, MatIconModule],
  standalone: true,
  providers: [TaskService],
  templateUrl: './add.component.html',
  styleUrl: './add.component.scss'
})
export class AddComponent {

  addTask!: FormGroup;
  isModalOpen = true;

  constructor(private taskService: TaskService,
    private router: Router
  ) {

    this.addTask = new FormGroup({
      titulo: new FormControl('', [Validators.required]),
      descricao: new FormControl('', [Validators.required]),
    });
  }

  openModal() {
    this.isModalOpen = true;
  }

  closeModal() {
    this.isModalOpen = false;
  }


  onSubmit(){
    if(this.addTask.valid){
      const formData = this.addTask.value;

      this.taskService
        .sendData(
          formData.titulo,
          formData.descricao
        )
        .subscribe({
          next: () => {
            console.log('Task Adicionada com sucesso');
            this.router.navigate(['/list']);
            this.addTask.reset();
          },
          error: (err) => {
            console.error('Erro ao cadastrar usuário:', err);
          },
        });
      } else {
        console.log('Formulário inválido!');
        console.log(this.addTask)
      }
    }
    cancelar(): void {
      this.router.navigate(['/list']);
    }
  }

