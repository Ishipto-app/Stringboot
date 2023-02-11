// https://dog.ceo/api/breeds/image/random
// fetch API
// axios

const btn = document.getElementById("btn")
const image = document.getElementById("image")

// btn.addEventListener("click", () => {
//     fetch("https://dog.ceo/api/breeds/image/random")
//         .then((res) => {
//             console.log(res)
//             return res.json()
//         })
//         .then(data => {
//             image.src = data.message;

//         })
//         .catch(err => {

//         })
// })

// btn.addEventListener("click", async () => {
//     try {
//         let rs = await fetch("https://dog.ceo/api/breeds/image/random")
//         console.log(rs)
//         let data = await rs.json()
//         image.src = data.message
//     } catch (error) {
        
//     }
// })

// btn.addEventListener("click", async () => {
//     try {
//         let rs = await axios.get("https://dog.ceo/api/breeds/image/random")
//         console.log(rs)
//         image.src = rs.data.message
//     } catch (error) {
        
//     }
// })

btn.addEventListener("click", () => {
    axios.get("https://dog.ceo/api/breeds/image/random")
        .then((res) => {
            image.src = res.data.message;

        })
        .catch(err => {
            console.log(err)
        })
})