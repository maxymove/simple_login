<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<title>Edit Profile</title>
<body>
<p>
<form action="/edit" method="post">
    <input type="text" placeholder="Enter new Username" name="username" value="${currentUser.getUsername()}" required><br>
    <input type="password" placeholder="Entera new Password" name="password" value="${currentUser.getPassword()}" required><br>
    <input type="text" placeholder="Enter a new email" name="email" value="${currentUser.getEmail()}" required><br>
    <button type="submit">submit</button>
</form>
</p>
</body>
</html>