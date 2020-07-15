<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="io.muzoo.ooc.webapp.basic.security.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="io.muzoo.ooc.webapp.basic.security.SecurityService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<title>Users Page</title>

<style>
    .btn-group button {
        background-color: #4CAF50; /* Green background */
        border: 1px solid green; /* Green border */
        color: white; /* White text */
        padding: 10px 24px; /* Some padding */
        cursor: pointer; /* Pointer/hand icon */
        float: left; /* Float the buttons side by side */
    }

    .btn-group button:not(:last-child) {
        border-right: none; /* Prevent double borders */
    }

    /* Clear floats (clearfix hack) */
    .btn-group:after {
        content: "";
        clear: both;
        display: table;
    }

    /* Add a background color on hover */
    .btn-group button:hover {
        background-color: #3e8e41;
    }
</style>
<body>
<h3>
    <p>${error}</p>
</h3>
<h1>
    <div class="btn-group">
        <form action="/" method="get">
            <button type="submit">Home</button>
        </form>
        <form action="/add_user" method="get">
            <button type="submit">Add user</button>
        </form>
        <form action="/logout" method="get">
            <button type="submit">Logout</button>
        </form>
    </div>
</h1>
<h2>
    <p>Welcome back, ${username}</p>
</h2>
<h4>
    <p>Username: ${currentUser.getUsername()}</p>
    <p>Email: ${currentUser.getEmail()}</p>
</h4>
<h3>
    <c:forEach items="${users}" var="user">
        <c:out value="${user.getUsername()}"/>
        <form action="/edit" method="get">
            <button type="submit" name="username" value="${user.getUsername()}">edit</button>
        </form>
        <form action="/delete" method="get">
            <button type="submit" name="username" value="${user.getUsername()}">delete</button>
        </form>
    </c:forEach>
</h3>
<h1>

</h1>
</body>
</html>