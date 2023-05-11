// Ds option phục vụ cho react-select
export const getCategoryOptions = (categories) => {
    if (!categories) {
        return [];
    }
    return categories.map((category) => {
        return {
            label: category.name,
            value: category.id,
        };
    });
}

export const getUserOptions = (users) => {
    if (!users) {
        return [];
    }
    return users.map((user) => {
        return {
            label: user.name,
            value: user.id,
        };
    });
}

export const getRolesOptions = (roles) => {
    if (!roles) {
        return [];
    }
    return roles.map((role) => {
        return {
            label: role.name,
            value: role.id,
        };
    });
}
export const getStatusOptions = () => {
    return [
        { label: "Nháp", value: false },
        { label: "Public", value: true },
    ];
}