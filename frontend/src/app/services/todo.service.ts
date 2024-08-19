import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { API_URL } from '../configs/app.constants';
import { TodoModel } from '../interfaces/todo';

@Injectable({
  providedIn: 'root'
})
export class TodoService {
  constructor(private httpClient: HttpClient) { }

  getAllTodos(): Observable<TodoModel[]> {
    return this.httpClient.get<TodoModel[]>(`${API_URL}/api/todos/all`);
  }
}
