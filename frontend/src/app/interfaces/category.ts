import { TodoModel } from './todo';

export interface CategoryModel {
    id: number;
    name: string;
    description: string;
    todoList: TodoModel[];
}
