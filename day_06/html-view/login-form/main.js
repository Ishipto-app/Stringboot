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
            let message;
            if(error.response && error.response.data) {
                message = error.response.data.message;
            } else {
                message = error.message;
            }
            setError(btnLogin, message);
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
const setError = (ele, message) => {
    let parentEle = ele.parentNode;
    parentEle.classList.add('error');
    parentEle.querySelector('small').innerText = message;
}
const setSuccess = (ele) => {
    ele.parentNode.classList.add('success');
}