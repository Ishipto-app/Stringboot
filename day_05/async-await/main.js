let money = 25

let buyIphone = () => {
    return new Promise(function (resolve, reject) {
        if (money > 30) {
            resolve("Mua iphone thôi") // Lời hứa được thực hiện thành công
        } else {
            reject("Kiếm thêm tiền đi") // Lời hứa được thực hiện thất bại
        }
    })
}
let buyAirpod = () => {
    return new Promise(function (resolve, reject) {
        if (money - 30 - 4 >= 0) {
            resolve("Mua thêm airpod")
        } else {
            reject("Không đủ tiền mua airpod")
        }
    })
}

//async function buy() {}
//const buy = async function() {}

const buy = async () => {
    try {
        let res = await buyIphone();
        console.log(res)

        let res1 = await buyAirpod();
        console.log(res1)
    } catch (err) {
        console.log(err)
    }

    //su dung return luon tra ve promise fulfilled

    return "abc";
}

buy().then(data => console.log(data))