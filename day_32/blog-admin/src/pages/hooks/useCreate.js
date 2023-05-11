import { yupResolver } from '@hookform/resolvers/yup';
import { useForm } from "react-hook-form";
import { blogSchema, categorySchema, userCreateSchema } from "../schemas/schemas";
import { useCreateBlogMutation } from '../../app/apis/blogApi';
import { useNavigate } from 'react-router-dom'
import { useCreateCategoryMutation } from '../../app/apis/categoryApi';
import { useCreateUserMutation } from '../../app/apis/userApi';

export const useCreateBlog = () => {
    const [createData, { isLoading: isCreating, isError: isCreateError, error: createError }] = useCreateBlogMutation();
    const navigate = useNavigate()
    // Khởi tạo form
    const { control, register, handleSubmit, formState: { errors }, setValue } = useForm({
        defaultValues: {
            status: false,
            categoryIds: []
        },
        resolver: yupResolver(blogSchema),
        mode: "all",
    });

    // Function tạo blog
    const onCreateBlog = (data) => {
        console.log(data)
        createData(data)
            .unwrap()
            .then((res) => {
                console.log(res)
                alert("create success")
                navigate("/admin/blogs/" + res.id)
            })
            .catch(err => {
                console.error(err)
                alert('create error')
            });
    };
    if(isCreateError) {
        console.error(createError)
        alert("error")

    }
    return { control, register, handleSubmit, errors, onCreateBlog }
}

export const useCreateCategory = () => {
    const [createData, { isLoading: isCreating, isError: isCreateError, error: createError }] = useCreateCategoryMutation();
    const navigate = useNavigate()
    // Khởi tạo form
    const { control, register, handleSubmit, formState: { errors }, setValue } = useForm({
        defaultValues: {},
        resolver: yupResolver(categorySchema),
        mode: "all",
    });

    // Function tạo category
    const onCreateCategory = (data) => {
        console.log(data)
        createData(data)
            .unwrap()
            .then((res) => {
                console.log(res)
                alert("create success")
                navigate("/admin/category/" + res.id)
            })
            .catch(err => {
                console.error(err)
                alert('create error')
            });
    };
    if(isCreateError) {
        console.error(createError)
        alert("error")

    }
    return { control, register, handleSubmit, errors, onCreateCategory }

}

export const useCreateUser = () => {
    const [createData, { isLoading: isCreating, isError: isCreateError, error: createError }] = useCreateUserMutation();
    const navigate = useNavigate()
    // Khởi tạo form
    const { control, register, handleSubmit, formState: { errors }, setValue } = useForm({
        defaultValues: {roles: []},
        resolver: yupResolver(userCreateSchema),
        mode: "all",
    });

    // Function tạo user
    const onCreateUser = (data) => {
        console.log(data)
        createData(data)
            .unwrap()
            .then((res) => {
                console.log(res)
                alert("create success")
                navigate("/admin/user/" + res.id)
            })
            .catch(err => {
                console.error(err)
                alert('create error')
            });
    };
    if(isCreateError) {
        console.error(createError)
        alert("error")

    }
    return { control, register, handleSubmit, errors, onCreateUser }

}