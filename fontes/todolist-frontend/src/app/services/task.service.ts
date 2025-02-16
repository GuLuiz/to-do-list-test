import { Injectable } from '@angular/core';
import * as defaults from '../../enviroments/enviroments';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TaskInterface } from '../interfaces/TaskInterface';

export interface Task {
  id?: number;
  titulo: string;
  descricao: string;
  status: 'PENDENTE' | 'EM_ANDAMENTO' | 'CONCLUIDA'
  creationDate?: string;
}


@Injectable({
  providedIn: 'root'
})

export class TaskService {

  URL_TASK: string = defaults.enviroments.appUrl + "task";

  headers = new HttpHeaders()
    .set('content-type', 'application/json')

  constructor(private httpClient: HttpClient) {}

  list(): Observable<Array<Object>> {
    return this.httpClient.get<Array<Object>>(`${this.URL_TASK}/`, { "headers": this.headers });
  }

  sendData(titulo: String, descricao: String): Observable<TaskInterface> {
    const data = { titulo, descricao}
    return this.httpClient.post<TaskInterface>(`${this.URL_TASK}/`, data);
  }

  selectById(id : string): Observable<TaskInterface>{
    return this.httpClient.get<TaskInterface>(`${this.URL_TASK}/${id}`,{"headers": this.headers});
  }

  updateById(
    id: string,
    descricao: string,
    status: string,
    titulo: string
  ): Observable<TaskInterface> {
    const data = { id, status, descricao, titulo };
    return this.httpClient.put<TaskInterface>(`${this.URL_TASK}/${id}`, data);
  }

  deleteById(id : string): Observable<any>{
    return this.httpClient.delete(`${this.URL_TASK}/${id}`, {headers: this.headers});
  }

  findByStatus(status: string): Observable<TaskInterface>{
    return this.httpClient.get<TaskInterface>(`${this.URL_TASK}/status/${status}`,{"headers": this.headers});
  }
}
