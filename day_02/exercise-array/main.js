// Bài 1: Viết function để kiểm tra 1 giá trị có nằm trong mảng hay không?
const checkElementExist = (arr, value) => arr.includes(value);
console.log(`checkElementExist([1,2,3,4,5], 5) => ${checkElementExist([1,2,3,4,5], 5)}`)
console.log(`checkElementExist([1,2,3,4,5], 6) => ${checkElementExist([1,2,3,4,5], 6)}`)
// checkElementExist([1,2,3,4,5], 5) => true
// checkElementExist([1,2,3,4,5], 6) => false
// Bài 2: Viết function truyền vào 1 mảng các số, và 1 giá trị bất kỳ. Trả về mảng mới với các giá trị lớn hơn giá trị truyền vào
const getElementGreater = (arr, value) => arr.filter(ele => ele > value);
console.log(`getElementGreater([1,2,3,4,5], 3) => ${getElementGreater([1,2,3,4,5], 3)}`)
console.log(`getElementGreater([1,2,3,4,5], 5) => ${getElementGreater([1,2,3,4,5], 5)}`)
// getElementGreater([1,2,3,4,5], 3) => [4,5]
// getElementGreater([1,2,3,4,5], 5) => []
// Bài 3: Viết function để tạo mã màu HEX ngẫu nhiên.
const randomIndex = num => Math.floor(Math.random() * num);
const randomHexCode = () => {
    let hexChar = [0,1,2,3,4,5,6,7,8,9,"a","b","c","d","e","f"];
    let arrStr = [...Array(6).keys()];
    arrStr.map((ele, index) => {
        arrStr[index] = index == 0 ? '#' : hexChar[randomIndex(16)];
    });
    return arrStr.join("");
}
console.log(randomHexCode());
console.log(randomHexCode());
// randomHexCode() => #728a98
// randomHexCode() => #a72938
