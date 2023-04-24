import { useGetAllCategoriesQuery } from "../../../app/apis/categoryApi";

const useFetchQuery = () => {
    // Lấy danh sách category
    const { data: categories, isLoading: isLoadingCategory } = useGetAllCategoriesQuery();

    // Lấy danh sách user
    //const { data: users, isLoading: isLoadingUser } = useGetAllUsersQuery();

    return {
        categories,
        isLoading: isLoadingCategory 
    }
}

export default useFetchQuery;