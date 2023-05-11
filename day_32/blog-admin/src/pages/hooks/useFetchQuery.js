import { useGetAllCategoriesQuery } from "../../app/apis/categoryApi";
import { useGetAllUserRolesQuery } from "../../app/apis/userApi";

const useFetchQuery = () => {
    // Lấy danh sách category
    const { data: categories, isLoading: isLoadingCategory } = useGetAllCategoriesQuery();
    const { data: roles, isLoading: isLoadingRole } = useGetAllUserRolesQuery();

    // Lấy danh sách user
    //const { data: users, isLoading: isLoadingUser } = useGetAllUsersQuery();

    return {
        categories, roles,
        isLoading: isLoadingCategory || isLoadingRole
    }
}

export default useFetchQuery;