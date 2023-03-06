import React, {useState} from 'react'
import TodoItem from './TodoItem'

function TodoList(props) {
    const {appName} = props;
    const [todos, setTodos] = useState(["aaa", "bbbb", "cccc"]);

    // dinh nghia func hung du lieu tu con
    const handleDelete = (index) => {
        console.log(index)
        let newTodo = todos.filter((todo, i) => i !== index);
        setTodos(newTodo);
    }
  return (
    //tra ve 1 phan tu duy nhat
    //dung the ao <React.Fragment> (viet tat <>) de boc cac phan tu
    <>
        <div>{props.appName}</div>
        {todos.map((todo, index) => (
            <TodoItem 
                key={index} 
                title={todo} 
                index={index}
                onDelete = {handleDelete}
            ></TodoItem>
        ))}

    </>
  )
}

export default TodoList