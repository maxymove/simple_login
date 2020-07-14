<%@ page import="java.util.List" %>
<%@ page import="io.muzoo.ooc.webapp.basic.security.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Iterator" %>
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
    <p>This is a User Page</p>
    <p>Welcome back, ${username}</p>
</h2>
<h3>
    <% ArrayList<User> users = (ArrayList<User>) request.getAttribute("users"); %>

    <table cellspacing="2" cellpadding="2">

        <tr><th>user</th></tr>
        <%
            // Iterating through subjectList
            if(request.getAttribute("users") != null)  // Null check for the object
            {
                Iterator<User> iterator = users.iterator();  // Iterator interface
                while(iterator.hasNext())  // iterate through all the data until the last record
                {
                    User user = iterator.next(); //assign individual employee record to the employee class object
        %>
        <tr>
            <td><%=user.getUsername()%></td>
        </tr>
        <%
                }
            }
        %>
    </table>
</h3>
</body>
</html>