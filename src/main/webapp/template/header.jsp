<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>${title}</title>

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" />
    <link rel="stylesheet" href="css/main.css" />

</head>
<body>
<header class="text-white bg-dark p-3 row">
    <div class="col-md px-3 d-flex justify-content-end align-items-center">
        <h1 class="mr-auto">User Display Exercise - Week 2</h1>

        <form action="login" method="post" class="">
            <input type="text" id="searchTermTextbox" name="searchTermTextbox" class="p-1 mr-1" value="" placeholder="Enter a Name" />
            <input type="submit" id="searchButton" name="searchButton" class="btn btn-primary p-1 mr-2" value="Search" />
            <a href="searchUser" class="btn btn-secondary p-1" role="button">View All Users</a>
        </form>
    </div>
</header>
<main class="container p-2">