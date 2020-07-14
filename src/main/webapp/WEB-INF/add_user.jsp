<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<title>Add User</title>
<body>
<h1>
    This is Add User page.
</h1>
<h2>
    <form action="/add_user" method="post">
        <input type="text" placeholder="Enter Username" name="username" required><br>
        <input type="password" placeholder="Enter Password" name="password" required><br>
        <button type="submit">add user</button>
    </form>
</h2>
</body>
</html>