import { yupResolver } from '@hookform/resolvers/yup';
import { useForm } from "react-hook-form";
import { blogSchema } from "../schemas/schemas";
import { useUpdateBlogMutation } from '../../../app/apis/blogApi';

const useUpdate = (id) => {
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

export default useUpdate;