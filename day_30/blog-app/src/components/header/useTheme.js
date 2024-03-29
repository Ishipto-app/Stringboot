import { useEffect, useState } from "react"

const useTheme = () => {
    const [theme, setTheme] = useState(() => {
        const themeLocalValue = localStorage.getItem("theme")
        return themeLocalValue ?? "light"
    })

    useEffect(() => {
        if (theme === "light") {
            document.body.classList.remove("dark")
        } else {
            document.body.classList.add("dark")
        }
        localStorage.setItem("theme", theme)
    }, [theme])

    const toggleTheme = () => {
        setTheme(theme === "light" ? "dark" : "light")
    }

    return {
        theme,
        toggleTheme
    }
}

export default useTheme;