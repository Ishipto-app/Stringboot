/*
action: la Object chua thong tin ve event
    {
        type: loai hanh dong || URL API (bat buoc/duy nhat) 
        payload: thong tin gui len de cap nhat state || request body (ko bat buoc)
    }
action creator: function tra ve 1 Object action
*/

export const increment = () => {
    return {
        type: "counter/increment"
    }
}

export const decrement = () => {
    return {
        type: "counter/decrement"
    }
}
