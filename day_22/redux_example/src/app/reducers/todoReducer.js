const initialState = [
    {id: 1, title: "lam bai tap 1"},
    {id: 2, title: "lam bai tap 2"},
    {id: 3, title: "lam bai tap 3"}
];

const todoReducer = (state = initialState, action) => {
    switch (action.type) {
        case "todo/addTodo":
            return [...state, action.payload];
        case "todo/deleteTodo":
            return state.filter(todo => todo.id != action.payload);
    
        default:
            return state;
    }
}

export default todoReducer