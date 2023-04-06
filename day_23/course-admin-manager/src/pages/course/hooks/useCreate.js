import { yupResolver } from '@hookform/resolvers/yup';
import { useForm } from "react-hook-form";
import { courseSchema } from "../schemas/schemas";
import { useCreateCourseMutation } from '../../../app/serivce/courseService';
import { useNavigate } from 'react-router-dom'

const useCreate = () => {
    const [createData, { isLoading: isCreating, isError: isCreateError, error: createError }] = useCreateCourseMutation();
    const navigate = useNavigate()
    // Khởi tạo form
    const { control, register, handleSubmit, formState: { errors }, setValue } = useForm({
        defaultValues: {
            price: 0,
        },
        resolver: yupResolver(courseSchema),
        mode: "all",
    });

    // Function tạo khóa học
    const onCreateCourse = (data) => {
        createData(data)
            .unwrap()
            .then((res) => {
                console.log(res)
                alert("create success")
                navigate("/admin/khoa-hoc/" + res.id)
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
    return { control, register, handleSubmit, errors, onCreateCourse }
}

export default useCreate;