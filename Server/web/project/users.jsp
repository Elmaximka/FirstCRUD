<%@ page import="main.java.service.UserService" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<br>
<%! UserService userService = UserService.instance();%>
<%! Gson gson = new Gson();%>
<% response.getWriter().println(gson.toJson(userService.getAllUsers()));%>
<p>

</p>
Add new User
<form action="/users/post" method="post">
    Name: <input type="text" name="name"/>
    Password: <input type="password" name="password"/>
    Gender:
    <input type="radio" name="gender" value="female" checked/>Female
    <input type="radio" name="gender" value="male"/>Male
    <input type="submit" value="Add"/>
</form>
Change User
<form action="/users/change" method="post">
    Name: <input type="text" name="name"/>
    NewName: <input type="text" name="newName"/>
    NewPassword: <input type="password" name="password"/>
    NewGender:
    <input type="radio" name="gender" value="female" checked/>Female
    <input type="radio" name="gender" value="male"/>Male
    <input type="submit" value="Change">
</form>
Delete User
<form action="/users/delete" method="post">
    Name: <input type="text" name="name"/>
    <input type="submit" value="Delete">
</form>

</body>
</html>
