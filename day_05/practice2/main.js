const btn = document.getElementById("btn");
const selectEl = document.getElementById("breed-list");
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
const renderList = (arr) => {
    arr.map(data => {
        const item = document.createElement("option");
        item.value = data;
        item.innerText = data;
        selectEl.appendChild(item);
    })
}
getBreedList();

const getImage = async (name) => {
    try {
        let res = await axios.get(`https://dog.ceo/api/breed/${name}/images/random`);
        image.src = res.data.message;
    } catch (error) {
        console.log(error)
    }
}
btn.addEventListener("click", () => {
    let name = selectEl.value;
    getImage(name);
})