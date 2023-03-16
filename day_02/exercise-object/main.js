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
const randomIndex = num => Math.floor(Math.random() * num);
// 6. Thêm 1 sản phẩm bất kỳ vào trong mảng product
const addRandomProduct = arr => {
    let randomName = arr[randomIndex(arr.length)].name;
    let product = arr.find(ele => ele.name == randomName);
    product.count++;
    console.log(`Add product ${randomName} the count number = ${product.count}`);
}
addRandomProduct(products);
addRandomProduct(products);

addProduct = () => {
    let randomProduct = { ...products[randomIndex(products.length)], count: 1};
    products.push(randomProduct)
}
addProduct();
console.log(products)
// 7. Xóa tất cả sản phẩm của thương hiệu "Samsung" trong giỏ hàng
const delBrandProduct = (arr, brand) => arr.filter(ele => ele.brand != brand);
products = delBrandProduct(products, "Samsung");
console.log([...products]);
// 8. Sắp xếp giỏ hàng theo price tăng dần
const sortProductAsc = (arr, type) => {
    return arr.sort((p1, p2) => (p1[type] > p2[type]) ? 1 : ((p2[type] > p1[type]) ? -1 : 0));
}
sortProductAsc(products, "price");
console.log([...products]);
// 9. Sắp xếp giỏ hàng theo count giảm dần
const sortProductDesc = (arr, type) => {
    return arr.sort((p1, p2) => (p1[type] < p2[type]) ? 1 : ((p2[type] < p1[type]) ? -1 : 0));
}
sortProductDesc(products, "count");
console.log([...products]);
// 10. Lấy ra 2 sản phẩm bất kỳ trong giỏ hàng
const getTwoRandomProduct = arr => {
    let arrIndex = [...Array(arr.length).keys()]
    let index1 = arrIndex.splice(randomIndex(arrIndex.length), 1)[0];
    let index2 = arrIndex.splice(randomIndex(arrIndex.length), 1)[0];
    return [arr[index1], arr[index2]];
}
console.log(getTwoRandomProduct(products));
console.log(getTwoRandomProduct(products));