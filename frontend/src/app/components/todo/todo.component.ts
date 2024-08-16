import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';

@Component({
  selector: 'app-todo',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './todo.component.html',
  styleUrl: './todo.component.css'
})
export class TodoComponent {
  todolist = signal <todoModel[]>([
    {
      id: 1,
      title: 'Tarea 1',
      description: 'Descripcion de la tarea 1',
      startDate: '2021-06-01',
      status: 'TODO',
      favorite: false
    },
    // aqui puedo acomodar los datos de la API creo
  ]);

  filter = signal<FilterType>('TODO');
}
