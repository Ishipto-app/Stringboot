import { yupResolver } from '@hookform/resolvers/yup';
import { useForm } from "react-hook-form";
import { blogSchema, categorySchema, passwordSchema, userSchema } from "../schemas/schemas";
import { useUpdateBlogMutation } from '../../app/apis/blogApi';
import { useUpdateCategoryMutation } from '../../app/apis/categoryApi';
import { useUpdateUserMutation, useUpdateUserPasswordMutation } from '../../app/apis/userApi';

export const useUpdateBlog = (id) => {
    const [updateData, { isLoading: isUpdating, isError: isUpdateError, error: updateError }] = useUpdateBlogMutation();
    // Khởi tạo form
    const { control, register, handleSubmit, formState: { errors }, setValue } = useForm({
        resolver: yupResolver(blogSchema),
        mode: "all",
    });

    // Function tạo khóa học
    const onUpdateBlog = (data) => {
        updateData({id, ...data})
            .unwrap()
            .then((res) => {
                console.log(res)
                alert("update success")
            })
            .catch(err => {
                console.error(err)
                alert('update error')
            });
    };
    if(isUpdateError) {
        console.error(updateError)
        alert("error")

    }
    return { control, register, handleSubmit, errors, setValue, onUpdateBlog }
}

export const useUpdateCategory = (id) => {
    const [updateData, { isLoading: isUpdating, isError: isUpdateError, error: updateError }] = useUpdateCategoryMutation();
    // Khởi tạo form
    const { control, register, handleSubmit, formState: { errors }, setValue } = useForm({
        resolver: yupResolver(categorySchema),
        mode: "all",
    });

    // Function tạo Category
    const onUpdateCategory = (data) => {
        updateData({id, ...data})
            .unwrap()
            .then((res) => {
                console.log(res)
                alert("update success")
            })
            .catch(err => {
                console.error(err)
                alert('update error')
            });
    };
    if(isUpdateError) {
        console.error(updateError)
        alert("error")

    }
    return { control, register, handleSubmit, errors, setValue, onUpdateCategory }
}

export const useUpdateUser = (id) => {
    const [updateData, { isLoading: isUpdating, isError: isUpdateError, error: updateError }] = useUpdateUserMutation();
    // Khởi tạo form
    const { control, register, handleSubmit, formState: { errors }, setValue } = useForm({
        resolver: yupResolver(userSchema),
        mode: "all",
    });

    // Function tạo User
    const onUpdateUser = (data) => {
        console.log(data)
        //data.roles = data.roles.map(a => a.id)
        updateData({id, ...data})
            .unwrap()
            .then((res) => {
                console.log(res)
                alert("update success")
            })
            .catch(err => {
                console.error(err)
                alert('update error')
            });
    };
    if(isUpdateError) {
        console.error(updateError)
        alert("error")

    }
    return { control, register, handleSubmit, errors, setValue, onUpdateUser }
}

export const useUpdateUserPassword = (id) => {
    const [updateData, { isLoading: isUpdating, isError: isUpdateError, error: updateError }] = useUpdateUserPasswordMutation();
    // Khởi tạo form
    const { control, register, handleSubmit, formState: { errors }, setValue } = useForm({
        resolver: yupResolver(passwordSchema),
        mode: "all",
    });

    // Function tạo Password
    const onUpdatePassword = (data) => {
        updateData({id, ...data})
            .unwrap()
            .then((res) => {
                console.log(res)
                alert("update success")
            })
            .catch(err => {
                console.error(err)
                alert('update error')
            });
    };
    if(isUpdateError) {
        console.error(updateError)
        alert("error")

    }
    return { control, register, handleSubmit, isUpdating, errors, setValue, onUpdatePassword }
}
