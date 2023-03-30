// Reducer : pure function (cung 1 tham so dau vao thi co 1 ket qua)
// khong co side effect

// const sum = (a, b) => {
//     return a + b;
// } 

const initialState = 10;

const counterReducer = (state = initialState, action) => {
    switch (action.type) {
        case "counter/increment":
            return state + 1;
        case "counter/decrement":
            return state - 1;
    
        default:
            return state;
    }
}

export default counterReducer