import { yupResolver } from '@hookform/resolvers/yup';
import { useForm } from "react-hook-form";
import { blogSchema } from "../schemas/schemas";
import { useCreateBlogMutation } from '../../../app/apis/blogApi';
import { useNavigate } from 'react-router-dom'

const useCreate = () => {
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

export default useCreate;