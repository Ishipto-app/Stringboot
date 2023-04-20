import React from 'react'
import { Helmet } from "react-helmet";

function NotFound() {
  return (
    <>
        <Helmet>
            <title>Page not found</title>
        </Helmet>
        <main className="main">
            <header className="page-header">
                <h1>Page not found</h1>
            </header>
        </main>
    </>
  )
}

export default NotFound