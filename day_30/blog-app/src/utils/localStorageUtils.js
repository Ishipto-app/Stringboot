// luu du lieu vao localStorage

export const setData = (key, value) => {
    localStorage.setItem(key, JSON.stringify(value))
}

// lay du lieu tu localStorage
export const getData = (key) => {
    const localStorageValue = localStorage.getItem(key)
    if(localStorageValue){
        return JSON.parse(localStorageValue)
    }
    return null;
}