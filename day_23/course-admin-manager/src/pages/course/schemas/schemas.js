import * as yup from "yup";

// Schema để validate course
export const courseSchema = yup
    .object({
        name: yup.string().required("Tên không được để trống"),
        description: yup.string().required("Mô tả không được để trống"),
        price: yup
            .number()
            .typeError('Không đúng định dạng')
            .min(0, "Giá phải >= 0")
    })