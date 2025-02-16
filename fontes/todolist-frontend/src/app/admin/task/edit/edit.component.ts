import { Component } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatIconModule } from '@angular/material/icon';
import { ActivatedRoute, Router, RouterLink, RouterModule } from '@angular/router';
import { TaskInterface } from '../../../interfaces/TaskInterface';
import { TaskService } from '../../../services/task.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'edit',
  imports: [RouterModule, ReactiveFormsModule, MatIconModule, MatIconModule, MatIconModule, MatIconModule, FormsModule, CommonModule],
  templateUrl: './edit.component.html',
  styleUrl: './edit.component.scss'
})
export class EditComponent {

  taskEdit!: FormGroup;
  task!: TaskInterface;
  selectedId: string;
  isModalOpen = true;
  
  constructor(private route: ActivatedRoute, private service: TaskService, private router: Router) {
    this.selectedId = "0";

    this.taskEdit = new FormGroup({
      id: new FormControl('', Validators.required),
      descricao: new FormControl('', [Validators.required]),
      status: new FormControl('', [Validators.required]),
      titulo: new FormControl('', [Validators.required])
    });
  }

  openModal() {
    this.isModalOpen = true;
  }

  closeModal() {
    this.isModalOpen = false;
  }

  ngOnInit() {
    this.selectedId = this.route.snapshot.paramMap.get('id') ?? "0";
    this.service.selectById(this.selectedId).subscribe(
      (res) => {
        console.log(res);
        this.task = res;

        this.taskEdit.patchValue({
          id: this.task.id,
          descricao: this.task.descricao,
          status: this.task.status,
          titulo: this.task.titulo
        });
      }, (err) => {
        console.error('Erro ao buscar a task: ', err)
      }
    );
  }

  onSubmit() {
    if (this.taskEdit.valid) {
      const formData = this.taskEdit.value;
      console.log('Formulário enviado:', formData);

      this.service
        .updateById(
          this.selectedId,
          formData.descricao,
          formData.status,
          formData.titulo
        )
        .subscribe({
          next: () => {
            console.log('Usuário cadastrado com sucesso!');
            this.router.navigate(['/list']);
            this.taskEdit.reset();
          },
          error: (err) => {
            console.error('Erro ao cadastrar usuário:', err);
          },
        });
    } else {
      console.log('Formulário inválido!');
    }
  }
  cancelar(): void {
    this.router.navigate(['/list']);
  }

  delete(): void{
    if(confirm('Tem certeza que deseja excluir esta task?')){
      this.service.deleteById(this.selectedId).subscribe({
        next: ()=>{
          console.log('Task excluída com sucesso!');
          this.router.navigate(['list']);
        },
        error:(err) =>{
          console.error('Erro ao excluir a task: ', err)
        }
      })
    }
  }
}
