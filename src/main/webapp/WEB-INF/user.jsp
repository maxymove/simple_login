<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <style>
        ul {
            list-style-type: none;
            margin: 0;
            padding: 0;
            overflow: hidden;
            background-color: #333;
        }

        li {
            float: left;
        }

        li a {
            display: block;
            color: white;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
        }

        li a:hover {
            background-color: #111;
        }
    </style>


    <style>
        #usersTable {
            display: table;
            font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
            border-collapse: collapse;
            width: 75%;
        }

        #usersTable td, #usersTable th {
            // add
            text-align: center;
            border: 1px solid #ddd;
            padding: 8px;
        }

        #usersTable tr:nth-child(even){background-color: #f2f2f2;}

        #usersTable tr:hover {background-color: #ddd;}

        #usersTable th {
            padding-top: 12px;
            padding-bottom: 12px;
            text-align: justify-all;
            /*text-align: left;*/
            background-color: #4CAF50;
            color: white;
        }
    </style>
</head>
<body>

<ul>
    <li><a class="active" href="/">Home</a></li>
    <li><a href="/add_user">Add User</a></li>
    <li><a href="/logout">Logout</a></li>
</ul>

<table id="usersTable">

    <tr>
        <th>username</th>
        <th>password</th>
        <th>email</th>
        <th>action</th>
    </tr>
    <c:forEach items="${users}" var="u">
        <tr>
            <td>${u.getUsername()}</td>
            <td>${u.getPassword()}</td>
            <td>${u.getEmail()}</td>
            <td>
                <div>
                    <form action="/edit" method="get">
                        <button type="submit" name="username" value="${u.getUsername()}">edit</button>
                    </form>
                    <form action="/delete" method="get">
                        <button type="submit" name="username" value="${u.getUsername()}">delete</button>
                    </form>
                </div>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>