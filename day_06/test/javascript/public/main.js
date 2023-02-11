//Bài 1. Viết function truyền vào mảng các chuỗi có độ dài bất kỳ. Kết quả trả về là 1 mảng các chuỗi có độ dài lớn nhất
//getStringHasMaxLength(['aba', 'aa', 'ad', 'c', 'vcd']) => ['aba', 'vcd'].
const getStringHasMaxLength = arr => {
    if(arr.length == 0) return arr;
    let arrLength = arr.map(el => el.length);
    let maxLength = Math.max(...arrLength);
    return arr.filter(el => el.length == maxLength);
}
console.log(getStringHasMaxLength(['aba', 'aa', 'ad', 'c', 'vcd']));

//Bài 2. Cho mảng users như sau:
users = [
    {
        name: "Bùi Công Sơn",
        age: 30,
        isStatus: true
    },
    {
        name: "Nguyễn Thu Hằng",
        age: 27,
        isStatus: false
    },
    {
        name: "Phạm Văn Dũng",
        age: 20,
        isStatus: false
    }
]
// Viết function truyền vào 1 mảng các object user. Trả về mảng các user có age > 25 và isStatus = true
// Viết function truyền vào 1 mảng các object user. Trả về mảng các user có age tăng dần

const getFilterUserList = arr => arr.filter(el => el.age > 25 && el.isStatus);
console.log(getFilterUserList(users));

const sortUserAgeAsc = arr => arr.sort((u1, u2) => (u1.age > u2.age) ? 1 : ((u2.age > u1.age) ? -1 : 0));
console.log(sortUserAgeAsc(users));

// Bài 3. Viết function truyền vào 1 mảng các chuỗi. Trả về Object hiển thị xem mỗi phần tử trong mảng xuất hiện bao nhiêu lần

// getCountElement(["one", "two", "three", "one", "one", "three"])

// // Kết quả
// {
//     one: 3,
//     two : 1,
//     three : 2
// }

const getCountElement = arr => {
    let result = {};
    arr.map(el => {
        if(!result[el]) result[el] = 0;
        result[el]++;
    })
    return result;
}
console.log(getCountElement(["one", "two", "three", "one", "one", "three"]));

// 2. Kiểm tra DOM
// Yêu cầu
// Vừa vào trang hiển thị danh sách quiz (bao gồm tiêu đề và danh sách các câu trả lời)
// Khi bấm vào nút “Random Answer” thì random câu trả lời cho từng câu hỏi và tự động được check vào (Mỗi câu hỏi chỉ check 1 câu trả lời)
// Cấu trúc quiz để render xem trong link tham khảo
// Khi render chú ý thuộc tính name trong các ô input, các câu trả lời cho cùng 1 câu hỏi, cần có name giống nhau
const quizes = [
    {
        id: 1,
        question: "1 + 1 = ?",
        answers: [1, 2, 3, 4],
    },
    {
        id: 2,
        question: "2 + 2 = ?",
        answers: [2, 3, 4, 5],
    },
    {
        id: 3,
        question: "3 + 3 = ?",
        answers: [3, 4, 5, 6],
    },
];

const quizList = document.querySelector(".quiz-container");
const btn = document.getElementById("btn");

const renderQuiz = arr => {
    quizList.innerHTML = "";

    if(arr.length === 0){
        quizList.innerHTML = "Chua co cau hoi nao";
        return;
    }

    arr.map((quiz, index) => {
        // create question
        const quizItem = document.createElement("div");
        quizItem.classList.add("quiz-item");

        // add title
        const title = document.createElement('h3');
        title.innerText = `Câu ${index + 1} : ${quiz.question}`
        quizItem.appendChild(title);
        const answer = document.createElement("div");
        answer.classList.add("quiz-answer");

        // create & add answers
        let html = ""
        quiz.answers.map(el => {
            html += `
                <div class="quiz-answer-item">
                    <input type="radio" name="${index + 1}">
                    <label>${el}</label>
                </div>
            `
        })
        answer.innerHTML = html;
        quizItem.appendChild(answer);
        quizList.appendChild(quizItem);
    })
}
renderQuiz(quizes);

const randomIndexAnswer = () => Math.floor(Math.random() * 4);
btn.addEventListener("click", () => {
    quizes.map((quiz, index) => {
        const quizAnswers = quizList.childNodes[index].querySelectorAll(".quiz-item .quiz-answer-item input");
        quizAnswers[randomIndexAnswer()].checked = true;
    })
})

// Danh sách API
// Posts : https://jsonplaceholder.typicode.com/posts
// Albums : https://jsonplaceholder.typicode.com/albums
// Photos : https://jsonplaceholder.typicode.com/photos
// (Học viên kiểm tra API trước khi làm, danh sách API trên lấy ở trang https://jsonplaceholder.typicode.com/)

// Yêu cầu
// Vừa vào trang hiển thị danh sách post (chỉ hiển thị title trong ul li)
// Bấm vào nút nào thì gọi API tương ứng với tên của nút đó và hiển thị (chỉ hiển thị title trong ul li)
// Resource nào đang được active thì highlight button đó lên
const ulEl = document.getElementById("list");
const spanEl = document.getElementById("type-list");
let objBtn = {
    posts: document.getElementById("posts"),
    photos: document.getElementById("photos"),
    albums: document.getElementById("albums")
}

const getList = async (type) => {
    try {
        let res = await axios.get(`https://jsonplaceholder.typicode.com/${type}`);
        renderList(type, res.data)
    } catch (err) {
        console.log(err)
        alert(`Get list ${type} fail!`)
    }
}
const renderList = (type, arr) => {
    // set type value
    spanEl.innerText = type;

    // set active button
    for (const key in objBtn) {
        if (key === type) {
            objBtn[key].classList.add("active");
        } else {
            objBtn[key].classList.remove("active");
        }
    }

    // set li value
    ulEl.innerHTML = "";
    if(arr.length === 0){
        ulEl.innerHTML = "<li>No item</li>"
        return;
    }
    arr.map(el => {
        const item = document.createElement("li");
        item.innerText = el.title;
        ulEl.appendChild(item);
    })
}
getList("posts");