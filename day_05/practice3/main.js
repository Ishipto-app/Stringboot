const btn = document.getElementById("btn");
const selectEl = document.getElementById("breed-list");
const ulEl = document.getElementById("sub-breed-list");
const image = document.getElementById("image");

const getBreedList = async () => {
    try {
        let res = await axios.get("https://dog.ceo/api/breeds/list/all");
        console.log(res.data)
        renderList(Object.keys(res.data.message))
    } catch (err) {
        console.log(err)
    }
}
const renderList = arr => {
    arr.map(data => {
        const item = document.createElement("option");
        item.value = data;
        item.innerText = data;
        selectEl.appendChild(item);
    })
    getSubBreedList(arr[0]);
}
getBreedList();

btn.addEventListener("click", () => {
    let name = selectEl.value;
    getSubBreedList(name)
})

const getSubBreedList = async name => {
    try {
        let res = await axios.get(`https://dog.ceo/api/breed/${name}/list`);
        console.log(res.data.message)
        if(res.data.message){
            renderSubList(name, res.data.message[name])
        }
    } catch (error) {
        console.log(error)
    }
}

const renderSubList = (name, arrSubName) => {
    ulEl.innerHTML = "";
    if(arrSubName.length === 0){
        ulEl.innerHTML = "<li>Khong co sub breed</li>"
        return;
    }
    arrSubName.map(subName => {
        const item = document.createElement("li");
        item.innerText = subName;
        item.classList.add("cursor-pointer");
        item.addEventListener("click", () => {
            getImage(name, subName);
        })
        ulEl.appendChild(item);
    })
}
const getImage = async (name, subName) => {
    try {
        let res = await axios.get(`https://dog.ceo/api/breed/${name}/${subName}/images/random`);
        image.src = res.data.message;
    } catch (error) {
        console.log(error)
    }
}