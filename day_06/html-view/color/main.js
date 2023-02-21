const btn = document.querySelector("button");
const input = document.querySelector("input");
const p = document.querySelector("p");

const heightInput = document.getElementById("height-number");
const weightInput = document.getElementById("weight-number");
const bmiResult = document.getElementById("bmi-result");
const inputEles = document.querySelectorAll('#bmi-container .input-row');
const getColor = async (type) => {
    try {
        let rs = await axios.get(`http://localhost:8081/random-color?type=${type}`);
        colorObj = rs.data;
        document.body.style.backgroundColor = colorObj.color;
        p.innerText = "Background color: " + colorObj.color;
        input.value = "";
    } catch (error) {
        p.innerText = `status error: ${error.response.data.status} / message: ${error.response.data.message}`;
    }
}
btn.addEventListener("click", e => {
    let value = input.value;
    if(value.trim() === "") return alert("Type khong duoc de trong");
    getColor(value);
})

const getBmi = async (type) => {
    Array.from(inputEles).map((ele) =>
        ele.classList.remove('success', 'error')
    );
    if(checkValidate()){
        let data = {
            height: heightInput.value,
            weight: weightInput.value
        }
        try {
            let rs;
            if(type == "get"){
                rs = await axios.get(`http://localhost:8081/bmi?height=${data.height}&weight=${data.weight}`);
            } else {
                rs = await axios.post(`http://localhost:8081/bmi`, data);
            }
            let bmiObj = rs.data;
            bmiResult.innerText = `Height: ${bmiObj.height}, Weight: ${bmiObj.weight}kg, Bmi: ${bmiObj.bmi}`;
            heightInput.value = "";
            weightInput.value = "";
        } catch (error) {
            bmiResult.innerText = `status error: ${error.response.data.status} / message: ${error.response.data.message}`;
        }
    }
}
const checkValidate = () => {
    let height = heightInput.value;
    let weight = weightInput.value;
    let isCheck = true;
    if (!height) {
        setError(heightInput, 'Height không được để trống');
        isCheck = false;
    } else if(!isHeight(height)) {
        setError(heightInput, 'Height không đúng định dạng');
    } else {
        setSuccess(heightInput);
    }
    if (!weight) {
        setError(weightInput, 'Weight không được để trống');
        isCheck = false;
    } else if(!isWeight(weight)) {
        setError(weightInput, '0kg < Weight < 999kg');
    } else {
        setSuccess(weightInput);
    }
    return isCheck;
}
const setError = (ele, message) => {
    let parentEle = ele.parentNode;
    parentEle.classList.add('error');
    parentEle.querySelector('small').innerText = message;
}
const setSuccess = (ele) => {
    ele.parentNode.classList.add('success');
}
const isHeight = (str) => {
    return /([0-9]+m+[0-9]+[0-9])/.test(str);
}
const isWeight = (number) => {
    let check = true;
    if(number >= 1000 || number <= 0) check = false;
    return check;
}

//login form
const loginEle = document.getElementById('login-container');
const logoutEle = document.getElementById('logout-container');
const usernameEle = document.getElementById('username');
const passwordEle = document.getElementById('password');
const btnLogin = document.getElementById('btn-login');
const btnLogout = document.getElementById('btn-logout');
const inputLogin = document.querySelectorAll('#login-container .input-row');
let userData;

btnLogin.addEventListener('click', async () => {
    Array.from(inputLogin).map((ele) =>
        ele.classList.remove('success', 'error')
    );

    if(checkValidateLogin()){
        let data = {
            username: usernameEle.value,
            password: passwordEle.value
        }
        try {
            rs = await axios.post(`http://localhost:8081/login`, data);
            userData = rs.data
            loginEle.classList.add("hide");
            logoutEle.classList.remove("hide");
            logoutEle.querySelector("h2").innerText = `Xin chao ${userData.username}. Email: ${userData.email}. Avatar: ${userData.avatar}`;
            usernameEle.value = "";
            passwordEle.value = "";
        } catch (error) {
            console.log(error);
            setError(btnLogin, error.response.data.message);
        }
    }
});
btnLogout.addEventListener('click', async () => {
    userData = null;
    logoutEle.querySelector("h2").innerText = "";
    loginEle.classList.remove("hide");
    logoutEle.classList.add("hide");
});
const checkValidateLogin = () => {
    let usernameValue = usernameEle.value;
    let passwordValue = passwordEle.value;

    let isCheck = true;

    if (usernameValue == '') {
        setError(usernameEle, 'Tên không được để trống');
        isCheck = false;
    } else {
        setSuccess(usernameEle);
    }

    if (passwordValue == '') {
        setError(passwordEle, 'Password không được để trống');
        isCheck = false;
    } else if (!ispassword(passwordValue)) {
        setError(passwordEle, 'Password độ dài ngắn nhất sáu ký tự');
        isCheck = false;
    } else {
        setSuccess(passwordEle);
    }

    return isCheck;
}
const ispassword = (str) => {
    let check = true;
    if(str.length < 6) check = false;
    return check;
}