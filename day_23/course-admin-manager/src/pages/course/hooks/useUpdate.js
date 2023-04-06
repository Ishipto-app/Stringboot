import { yupResolver } from '@hookform/resolvers/yup';
import { useForm } from "react-hook-form";
import { courseSchema } from "../schemas/schemas";
import { useUpdateCourseMutation } from '../../../app/serivce/courseService';

const useUpdate = (id) => {
    const [updateData, { isLoading: isUpdating, isError: isUpdateError, error: updateError }] = useUpdateCourseMutation();
    // Khởi tạo form
    const { control, register, handleSubmit, formState: { errors }, setValue } = useForm({
        defaultValues: {
            price: 0,
        },
        resolver: yupResolver(courseSchema),
        mode: "all",
    });

    // Function tạo khóa học
    const onUpdateCourse = (data) => {
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
    return { control, register, handleSubmit, errors, setValue, onUpdateCourse }
}

export default useUpdate;