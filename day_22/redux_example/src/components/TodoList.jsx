import React, {useState} from 'react'
import { useSelector, useDispatch } from 'react-redux';
import { deleteTodo } from '../app/actions/todoActions';
import TodoItem from './TodoItem'

function TodoList() {
    const todos = useSelector(state => state.todos);
    const dispatch = useDispatch(); // ban chat la gui action  => store de xu ly

    // dinh nghia func hung du lieu tu con
    const handleDelete = (id) => {
        console.log(id)
        dispatch(deleteTodo(id));
    }
  return (
    //tra ve 1 phan tu duy nhat
    //dung the ao <React.Fragment> (viet tat <>) de boc cac phan tu
    <>
        <div>TodoList app</div>

        {todos.length > 0 && todos.map((todo) => (
            <TodoItem 
                key={todo.id} 
                todo={todo} 
                onDelete = {handleDelete}
            ></TodoItem>
        ))}

        {todos.length == 0 && (
            <p>Khong co cong viec trong danh sach</p>
        )}

    </>
  )
}

export default TodoList