import { yupResolver } from '@hookform/resolvers/yup';
import { useForm } from "react-hook-form";
import { courseSchema } from "../schemas/schemas";

const useCreate = () => {
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
        console.log(data)
        // //if(newData.categories) newData.categories = changeSelectOptionToCategory(data.categories)
        // //newData.user = userData.find(user => user.id == newData.user);
        // createData(data)
        //     .unwrap()
        //     .then((res) => {
        //         console.log(res)
        //         alert("create success")
        //         //chuyen huong
        //         //navigate("/admin/khoa-hoc/" + data.id)
        //     })
        //     .catch(err => {
        //         console.error(err)
        //         alert('create error')
        //     });
    };

    return { control, register, handleSubmit, errors, setValue }
}

export default useCreate;