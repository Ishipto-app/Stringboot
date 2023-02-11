// //promise
// //[[promiseState]] : pending
// //[[promiseResult]] : undefinded

// const promise = new Promise((resolve, reject) => {
    
// })

// console.log(promise)

// //[[promiseState]] : fulfilled
// //[[promiseResult]] : "thanh cong"

// const promiseSuccess = new Promise((resolve, reject) => {
//     resolve("thanh cong");
// })

// //[[promiseState]] : rejected
// //[[promiseResult]] : "that bai"

// const promiseFail = new Promise((resolve, reject) => {
//     reject("that bai");
// })


// HỨA : Nếu có trên 30 triệu, sẽ mua iphone 13 pro max
let money = 35

// Hứa
let buyIphone = () => {
    return new Promise(function (resolve, reject) {
        if (money > 30) {
            resolve("Mua iphone thôi") // Lời hứa được thực hiện thành công
        } else {
            reject("Kiếm thêm tiền đi") // Lời hứa được thực hiện thất bại
        }
    })
}
buyIphone()
    .then(res => {
        console.log(res)
        return buyAirpod()
    })
    .then(res => {
        console.log(res)
    })
    .catch(err => {
        console.log(err)
    })
    .finally(() => {
        console.log("ve nha di")
    })

let buyAirpod = () => {
    return new Promise(function (resolve, reject) {
        if (money - 30 - 4 >= 0) {
            resolve("Mua thêm airpod")
        } else {
            reject("Không đủ tiền mua airpod")
        }
    })
}