import React from 'react'
import { useSelector } from 'react-redux'
import { Outlet } from 'react-router-dom';
import Forbiden from './Forbiden';

//prop => role bat buoc de co the vao dc component tuong ung
function AuthorizeRoutes({requireRoles}) {
    const {auth} = useSelector(state => state.auth);
    const roles = auth.roles.map(role => role.name)
    const canAccess = requireRoles.some(role => roles.includes(role))
    if(!canAccess){
        return <Forbiden />
    }

  return (
    <Outlet />
  )
}

export default AuthorizeRoutes