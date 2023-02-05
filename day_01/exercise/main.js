// Bài 1: Viết function truyền vào 1 số nguyên dường. Tính giai thừa của số đó

// Ví dụ: calculateFactorial(5) = 5 * 4 * 3 * 2 * 1 = 120
const calculateFactorial = num => {
    let result = 1;
    if(num >= 0) {
        for (let i = 1; i <= num; i++) {
            result = result * i;
        }
    }
    return result;
}
console.log(`5! = ${calculateFactorial(5)}`)
// Bài 2: Viết function truyền vào 1 chuỗi. In ra chuỗi đảo ngược của chuỗi đó
const reverseString = str => {
    str = str.toString();
    let newStr = ''
    for (let i = 1; i <= str.length; i++) {
        newStr = newStr + str.charAt(str.length - i)
    }
    return newStr
}
console.log(`hello => ${reverseString("hello")}`)
// Ví dụ: reverseString(‘hello’) => olleh
// Bài 3: Viết function truyền vào mã quốc gia. Trả về message có ý nghĩa là “Xin chào”, tương ứng với mã quốc gia được truyền vào

// Ví dụ: translate(‘VN’) => “Xin chào”
// translate(‘EN’) => “Hello”
// Gợi ý : Sử dụng switch - case . Học viên tự nghĩ ra 1 vài mã quốc gia và câu chào tương ứng với quốc gia đó
const translate = str => {
    if(str.length > 1) {
        switch (str) {
            case "VN":
                return "Xin chào";
                break;
            case "EN":
                return "Hello";
                break;
            case "FR":
                return "Bonjour";
                break;
            default:
                return "Ma quoc gia dang duoc cap nhat";
                break;
        }
    } else {
        return "Ma quoc gia khong dung";
    }
}
console.log(`Xin chao tieng phap => ${translate("FR")}`)
// Bài 4: Cho function truyền vào 1 chuỗi dài hơn 15 ký tự. Viết 1 function cắt chuỗi, lấy ra 10 ký tự đầu tiên và thêm vào dấu “…” ở cuối chuỗi.

// Ví dụ : subString(“xinchaocacbandenvoiTechmaster”) => “xinchaocac…”
const subString = str => {
    if(str.length > 15){
        return str.slice(0, 10) + "..."
    } else {
        return "do dai ko du 15 ky tu"
    }
}
console.log(`xinchaocacbandenvoiTechmaster => ${subString("xinchaocacbandenvoiTechmaster")}`)