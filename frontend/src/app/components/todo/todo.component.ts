import { CommonModule } from '@angular/common';
import { Component, computed, signal } from '@angular/core';
import { FilterType, TodoModel } from '../../models/todo';
import { FormControl, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-todo',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './todo.component.html',
  styleUrl: './todo.component.css'
})
export class TodoComponent {
  todoList = signal <TodoModel[]>([
    {
      id: 1,
      title: 'Tarea 1',
      description: 'Descripcion de la tarea 1',
      startDate: '2021-06-01',
      status: 'DONE',
      favorite: false,
      editing: false
    },
    {
      id: 2,
      title: 'Tarea 2',
      description: 'Descripcion de la tarea 2',
      startDate: '2021-06-01',
      status: 'TODO',
      favorite: false,
      editing: false

    },
    {
      id: 3,
      title: 'Tarea 3',
      description: 'Descripcion de la tarea 3',
      startDate: '2021-06-01',
      status: 'TODO',
      favorite: false,
      editing: false

    },
    // aqui puedo acomodar los datos de la API creo
  ]);

  filter = signal<FilterType>('TODO');

  todoListFiltered = computed(()=> {
    const filter = this.filter();
    const todos = this.todoList();

    switch(filter){
      case 'TODO':
        return todos.filter((todo)=>todo.status === 'TODO');
      case 'IN_PROGRESS':
        return todos.filter((todo)=>todo.status === 'IN_PROGRESS');
      case 'DONE':
        return todos.filter((todo)=>todo.status === 'DONE');
      default:
        return todos;
    }
  });

  newTodo= new FormControl('', {
    nonNullable: true,
    validators: [Validators.required, Validators.minLength(3)],
  });

  changeFilter(filterString: FilterType) {
    this.filter.set(filterString);
  }

  addTodo() {
    const newTodoTitle = this.newTodo.value.trim();
    if (this.newTodo.valid && newTodoTitle !=='') {
      this.todoList.update((prev_todos)=>{
        return [
          ...prev_todos,
          {
            id: Date.now(),title: newTodoTitle, description: '',
            startDate: new Date().toISOString(), status: 'TODO', favorite: false, editing: false}
        ];
      });
      this.newTodo.reset();
    } else {
      this.newTodo.reset();
    }
  }

  toggLeTodo(todoId: number){
    return this.todoList.update((prev_todos)=>
      prev_todos.map((todo)=>{
        return todo.id === todoId ?
        {...todo, status: todo.status === 'TODO' ? 'DONE' : 'TODO'} : todo;
    }));
  }

  removeTodo(todoId: number){
    this.todoList.update((prev_todos)=>
    prev_todos.filter((todo)=>todo.id !== todoId));
  }

  updateTodoEditingMode(todoId: number){
    return this.todoList.update((prev_todos)=>
      prev_todos.map((todo)=>{
        return todo.id === todoId
        //cambiar editing por un componente que se muestra sólo cuando le dan click a editar

        ? {...todo, editing: true}
        : {...todo, editing: false};
      }))
  }

  saveTitleTodo(todoId:number, event: Event){
    const title = (event.target as HTMLInputElement).value;
    return this.todoList.update((prev_todos)=>
      prev_todos.map((todo)=>{
        return todo.id === todoId
        ? {...todo, title, editing: false}
        : todo;
      })
    )
  }
}
