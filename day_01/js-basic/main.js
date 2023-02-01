console.log("Hello world");
//alert("xin chao");
//document.write("hello");
let age;
const email = 'abc@gmail.com';
age = 15;
console.log(typeof age);
let name = "Bùi Hiên"
let year = 1997
//bieu thuc chen bien phai nam trong cap nhay ``
console.log(`Xin chào các bạn, mình tên là ${name}. Năm nay ${2022 - year} tuổi`);

// c1 regular function
// c2 function expression
// c3 arrow function - ES6
// default parameter - ES6
let sum = (a, b) => {
    return a + b;
}
let sum1 = (a = 10, b = 20) => {
    return a + b
}

console.log(sum(3,4))
console.log(sum1(5))

//cong chuoi
repeatString = (str) => {
    
}