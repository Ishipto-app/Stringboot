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
export const categorySchema = yup
    .object({
        name: yup.string().required("Name không được để trống"),
    })
export const userSchema = yup
    .object({
      name: yup.string().required("Ten khong duoc de trong"),
      email: yup
        .string()
        .matches(
          /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/,
          "Email khong hop le"
        )
        .required("Email khong duoc de trong"),
    })
export const userCreateSchema = yup
    .object({
        name: yup.string().required("Ten khong duoc de trong"),
        email: yup
        .string()
        .matches(
            /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/,
            "Email khong hop le"
        )
        .required("Email khong duoc de trong"),
        password: yup.string().required("password khong duoc de trong"),
    })
export const passwordSchema = yup.object({
    oldPassword: yup.string()
        .required("Nhap lai password"),
    newPassword: yup.string()
        .required("Nhap password moi")
    })