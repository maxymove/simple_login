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
    body {
        background-color: #c94c4c;
    }
</style>

<style>
    .nav {
        background-color: #eea29a;
        list-style-type: none;
        text-align: center;
        margin: 0;
        padding: 0;
    }

    .nav li {
        display: inline-block;
        font-size: 20px;
        padding: 20px;
    }
</style>

<style>
    .button {
        background-color: #f7786b;
        border: none;
        color: white;
        padding: 15px 32px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 16px;
        margin: 4px 2px;
        cursor: pointer;
    }
</style>
</head>
<body>

<h1>Welcome back, ${username}</h1>
<p>Username: ${currentUser.getUsername()}</p>
<p>Email: ${currentUser.getEmail()}</p>

<ul class="nav">
    <li><a href="/" class="button">Home</a></li>
    <li><a href="/add_user" class="button">Add User</a></li>
    <li><a href="/logout" class="button">Logout</a></li>

<%--    <li><a href="/">Home</a></li>--%>
<%--    <li><a href="/add_user">Add User</a></li>--%>
<%--    <li><a href="/logout">Logout</a></li>--%>
</ul>
<body>
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
</body>
</html>