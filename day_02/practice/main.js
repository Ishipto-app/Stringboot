let numbers = [1, 2, 3, 4, 5];
console.log(numbers[0]) // 1
console.log(numbers[1]) // 2

console.log(numbers[4]) // 5
console.log(numbers[numbers.length - 1]) // 5


numbers[0] = 10;
numbers[1] = 20;

console.log(numbers) // [10, 20, 3, 4, 5]
let arr = [1,2,3]
let arr2 = arr.sort().reverse()
console.log(arr)
console.log(arr)
console.log(arr == arr2)
// == so sanh gia tri
// === so sanh kieu du lieu & gia tri
// == arr se convert sang string de so sanh

// Bài 1: Viết function tìm số lớn nhất trong mảng
const findMax = arr => {
    if(arr.length == 0) return null;
    return Math.max(...arr);
}
console.log(`số lớn nhất trong mảng [1,3,2] là ${findMax([1,3,2])}`)

// Bài 2: Viết function tìm số nhỏ nhất trong mảng
const findMin = arr => {
    if(arr.length == 0) return null;
    return Math.min(...arr);
}
console.log(`số nhỏ nhất trong mảng [1,3,2] là ${findMin([1,3,2])}`)
// Bài 3: Viết function cho phép truyền vào 1 mảng các số, kết quả trả về là 1 mảng mới với các số là số dư tương ứng khi chia mảng cũ cho 2
const tinhSoDuMang = arr => {
    let newArr = [];
    for (let index = 0; index < arr.length; index++) {
        const element = arr[index];
        newArr.push(element % 2);
    }
    return newArr;
}
console.log(`kết quả trả về là ${tinhSoDuMang([4,2,5,6,2,7])} với các số là số dư tương ứng khi chia [4,2,5,6,2,7] cho 2`)
// Ví dụ : [4,2,5,6,2,7] => [0,0,1,0,0,1]
// Bài 4: Viết function truyền vào 1 chuỗi, hãy sao chép đó chuỗi lên 10 lần (Sử dụng array để làm)
const repeatString = str => {
    let arr = [];
    for (let index = 0; index < 10; index++) {
        arr.push(str);
    }
    return arr.join("");
}
console.log(`sao chép đó chuỗi "ab" lên 10 lần = "${repeatString("ab")}"`);
// Ví dụ: repeatString(‘a’) => Kết quả trả về là ‘aaaaaaaaaa’
// Bài 5: Viết function truyền vào 1 chuỗi, hãy sao chép đó chuỗi lên 10 lần, ngăn cách nhau bởi dấu gạch ngang (Sử dụng array để làm)
const repeatString2 = str => {
    let arr = [];
    for (let index = 0; index < 10; index++) {
        arr.push(str);
    }
    return arr.join("-");
}
console.log(`sao chép đó chuỗi "ab" lên 10 lần = "${repeatString2("ab")}"`);
// Ví dụ: repeatString(‘a’) => Kết quả trả về là ‘a-a-a-a-a-a-a-a-a-a’

// Bài 1: Viết function để kiểm tra 1 giá trị có nằm trong mảng hay không?
const checkElementExist = (arr, num) => {
    for (let index = 0; index < arr.length; index++) {
        if(num == arr[index]) {
            return true;
        }
    }
    return false;
}
console.log(checkElementExist([1,2,3,4,5], 5));
console.log(checkElementExist([1,2,3,4,5], 6));
// checkElementExist([1,2,3,4,5], 5) => true
// checkElementExist([1,2,3,4,5], 6) => false
// Bài 2: Viết function truyền vào 1 mảng các số, và 1 giá trị bất kỳ. Trả về mảng mới với các giá trị lớn hơn giá trị truyền vào
const getElementGreater = (arr, num) => {
    let newArr = [];
    for (let index = 0; index < arr.length; index++) {
        if(num < arr[index]) {
            newArr.push(arr[index]);
        }
    }
    return newArr;
    
}
console.log(getElementGreater([1,2,3,4,5], 3));
console.log(getElementGreater([1,2,3,4,5], 5));
// getElementGreater([1,2,3,4,5], 3) => [4,5]
// getElementGreater([1,2,3,4,5], 5) => []
// Bài 3: Viết function để tạo mã màu HEX ngẫu nhiên.
const randomHexCode = () => {
    let hexChar = [0,1,2,3,4,5,6,7,8,9,"a","b","c","d","e","f"];
    let arr = ["#"];
    for (let i = 0; i < 6; i++) {
        let index = Math.floor(Math.random() * hexChar.length);
        arr.push(hexChar[index].toString());
    }
    return arr.join("");
}
console.log(randomHexCode());
console.log(randomHexCode());
// randomHexCode() => #728a98
// randomHexCode() => #a72938
// Bài 4: Viết function để tạo mã màu RGB ngẫu nhiên.
const randomRgbCode = () => {
    let arr = [];
    for (let i = 0; i < 3; i++) {
        let index = Math.floor(Math.random()*256);
        arr[i] = index.toString();
    }
    return "rgb(" + arr.join(",") + ")";
}
console.log(randomRgbCode());
console.log(randomRgbCode());
// randomRgbCode() => rgb(213,43,133)
// randomRgbCode() => rgb(12,32,122)
const checkArr = arr => {

}

// Swap phần tử
let a = 1;
let b = 3;
[a, b] = [b, a];
// console.log(a); // 3
// console.log(b); // 1

// Danh sách các sản phẩm có trong giỏ hàng
let products = [
    {
        name: "Iphone 13 Pro Max", // Tên sản phẩm
        price: 3000000, // Giá mỗi sản phẩm
        brand: "Apple", // Thương hiệu
        count: 2, // Số lượng sản phẩm trong giỏ hàng
    },
    {
        name: "Samsung Galaxy Z Fold3",
        price: 41000000,
        brand: "Samsung",
        count: 1,
    },
    {
        name: "IPhone 11",
        price: 15500000,
        brand: "Apple",
        count: 1,
    },
    {
        name: "OPPO Find X3 Pro",
        price: 19500000,
        brand: "OPPO",
        count: 3,
    },
]

// 1. In ra thông tin các sản phẩm trong giỏ hàng theo cấu trúc sau
// Tên - Giá - Thương Hiệu - Số lượng
// Ví dụ : OPPO Find X3 Pro - 19500000 - OPPO - 3
const writeDetails = arr => {
    return arr.map(ele => console.log([ele.name, ele.price, ele.brand, ele.count].join(" - ")))
}
console.log(writeDetails(products));
// 2. Tính tổng tiền tất cả sản phẩm trong giỏ hàng
// Tổng tiền mỗi sản phẩm = price * count
const getTotalPrice = arr => {
    return arr.reduce((sum, ele) => {
        return sum + (ele.price * ele.count);
    }, 0)
}
console.log(getTotalPrice(products));
// 3. Tìm các sản phẩm của thuơng hiệu "Apple"
const getProductByBrand = (arr, brand) => {
    return arr.filter(ele => ele.brand == brand);
}
console.log(getProductByBrand(products, 'Apple'))
// 4. Tìm các sản phẩm có giá > 20000000
const getProductByPrice = (arr, price) => {
    return arr.filter(ele => ele.price > price);
}
console.log(getProductByPrice(products, 20000000))

// 5. Tìm các sản phẩm có chữ "pro" trong tên (Không phân biệt hoa thường)
const getProductByName = (arr, name) => {
    return arr.filter(ele => ele.name.toLowerCase().includes(name));
}
console.log(getProductByName(products, "pro"))

// 6. Thêm 1 sản phẩm bất kỳ vào trong mảng product
const addRandomProduct = arr => {
    let nameArr = arr.map(ele => ele.name)
    let index = Math.floor(Math.random() * nameArr.length);
    let product = arr.find(ele => ele.name == nameArr[index])
    product.count++;
    console.log(`Add product ${nameArr[index]} the count number = ${product.count}`);
}
console.log(addRandomProduct(products));
// 7. Xóa tất cả sản phẩm của thương hiệu "Samsung" trong giỏ hàng

// 8. Sắp xếp giỏ hàng theo price tăng dần

// 9. Sắp xếp giỏ hàng theo count giảm dần

// 10. Lấy ra 2 sản phẩm bất kỳ trong giỏ hàng