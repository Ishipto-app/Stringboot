import * as yup from "yup";

// Schema để validate blog
export const blogSchema = yup
    .object({
        title: yup.string().required("Title không được để trống"),
        description: yup
            .string()
            .min(10, "Description co it nhat 10 ky tu")
            .required("Mô tả không được để trống"),
        content: yup
            .string()
            .min(10, "Content co it nhat 10 ky tu")
            .required("Mô tả không được để trống"),
    })