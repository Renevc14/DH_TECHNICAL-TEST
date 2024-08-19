import { Routes } from '@angular/router';
import { TodoComponent } from './components/todo/todo.component';
import { CategoryComponent } from './components/category/category.component';




export const routes: Routes = [
    {path: 'todo', component: TodoComponent},
    {path: '**', pathMatch: 'full', redirectTo: 'todo'},
];
