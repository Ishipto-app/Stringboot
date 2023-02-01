// Bài 1. Viết function truyền vào 1 chuỗi bất kỳ, hãy sao chép chuỗi đó lên 10 lần.

// Ví dụ : repeatString(“a”) => Kết quả trả về là “aaaaaaaaaa”
const repeatString = str => {
    let newStr = '';
    for (let i = 0; i < 10; i++) {
        newStr += `${str}`;
    }
    return newStr;
}
console.log(repeatString('a'))
// Bài 2. Viết function truyền vào 1 chuỗi bất kỳ, hãy viết hàm có tác dụng sao chép đó chuỗi lên 10 lần, ngăn cách nhau bởi dấu gạch ngang.

// Ví dụ: repeatString(“a”) => Kết quả trả về là “a-a-a-a-a-a-a-a-a-a”
const repeatString2 = str => {
    let newStr = '';
    for (let i = 0; i < 10; i++) {
        newStr += `${str}${i < 9 ? '-' : ''}`;
    }
    return newStr;
}
console.log(repeatString2('a'))
// Bài 3. Viết function truyền vào 1 chuỗi bất kỳ và 1 số nguyên dương n > 1, hãy viết hàm có tác dụng sao chép đó chuỗi lên n lần, ngăn cách nhau bởi dấu gạch ngang.
const copyString = (str = '', n) => {
    let newStr = '';
    if(n > 1){
        for (let i = 0; i < n; i++) {
            newStr += `${str}${i < n - 1 ? '-' : ''}`;
        }
    }
    return newStr;
}
console.log(copyString('a', 5));
// Ví dụ: repeatString(‘a’, 5) => Kết quả trả về là ‘a-a-a-a-a’

// Bài 4. Tính tổng các số chia hết cho 5 từ 0 -> 100
const sumTo100 = () => {
    let sum = 0;
    for (let i = 0; i <= 100; i++) {
        if(i%5 == 0){
            sum += i;
        }
    }
    return sum;
}
console.log(`tong cac so chia het cho 5 tu 0 -> 100 la ${sumTo100()}`);

// Bài 5: Viết hàm tính thể tích hình cầu, với tham số truyền vào là bán kính của hình cầu.
const theTichHinhCau = r => {
    let v = 4*Math.PI*r/3;
    return v;
}
console.log(`Viết hàm tính thể tích hình cầu, với ban kinh = 5 la ${theTichHinhCau(5)}`);

// Bài 6: Viết hàm truyền vào 2 số nguyên, tính tổng tất cả các số nguyên nằm giữa chúng. Ví dụ với tham số 3 và 8 ta có kết quả là 22 (bằng 4 + 5 + 6 + 7).
const sumSoNguyen = (a, b) =>{
    let sum = 0;
    let min, max;
    if(a > b){
        min = b;
        max = a;
    } else {
        min = a;
        max = b;
    }
    for (let i = min; i <= max; i++) {
        sum += i;
    }
    return sum;
}
console.log(sumSoNguyen(10, 7));
// Note : Kết quả xuôi và ngược là như nhau

// Ví dụ :

// sum(3,8) = 22
// sum(8,3) = 22
// Bài 7: Cho 1 số, kiểm tra xem số đó có phải là số nguyên tố hay không, kết quả trả về true hoặc false.
const checkSoNguyenTo = num => {
    let check = true;
    if(num < 1) {
        check = false;
    } else {
        for (let i = 2; i < num; i++) {
            if(num % i == 0) check = false;
        }
    }
    return check
}
console.log(`10${checkSoNguyenTo(10) ? '' : ' ko'} là số nguyên tố`)
// Bài 8: Cho 1 số nguyên dương bất kỳ. Tính tổng tất cả các số nguyên tố mà nhỏ hơn hoặc bằng tham số truyền vào.
const tongCacSoNguyenDuong = num => {
    let sum = 0
    if(num > 1){
        for (let i = 2; i <= num; i++) {
            let check = true;
            for (let j = 2; j < i; j++) {
                if(i % j == 0) check = false;
            }
            if(check) sum += i
        }
    }
    return sum
}
console.log(`Tính tổng tất cả các số nguyên tố <= 50 la ${tongCacSoNguyenDuong(50)}`)
// Bài 9: Cho 1 số nguyên dương, viết hàm tính tổng tất cả các ước số của số đó.
const tongUocSo = num => {
    let sum = 0
    if(num >= 1) {
        for (let i = 1; i <= num; i++) {
            if(num % i == 0) sum += i;
        }
    }
    return sum
}
console.log(`tổng tất cả các ước số của số 50 la ${tongUocSo(10)}`)