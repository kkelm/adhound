<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>${title}</title>

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <!--
    <link rel="stylesheet" type="text/css" href="./css/bootstrap/4.4.1/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="./css/font-awesome/all.min.css" />
    <link rel="stylesheet" type="text/css" href="./css/main.css" />
    -->
    <link rel="stylesheet" type="text/css" href="./css/main.css" />



</head>
<body class="bg-light">



<nav class="navbar navbar-expand-xl navbar-light fixed-top">
    <a class="navbar-brand" href="#"><img src="images/AdHound-Logo.gif" class="img-responsive" style="max-height: 30px" /></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon mb-1"></span>
    </button>

    <div class="row collapse navbar-collapse my-2" id="navbarsExampleDefault">
        <div id="dashboardDiv" class="col-md-2 d-flex justify-content-start align-items-center">
            <a class="nav-link stretched-link" href="#"><span class="fas fa-tachometer-alt"></span> Dashboard</a> <span class="sr-only">(current)</span>
        </div>
        <div id="searchDiv" class="col-md-8">
            <form class="row my-2 mx-2">
                <input class="col-9 form-control" type="text" placeholder="Search" aria-label="Search">
                <button class="col-3 form-control btn btn-secondary" type="submit">Search</button>
            </form>
        </div>
        <div id="logoutDiv" class="col-md-2 d-flex justify-content-end align-items-center">
            <a class="nav-link stretched-link" href="#">Logout <span class="fas fa-sign-out-alt"></span></a>
        </div>
    </div>
</nav>


<main class="container p-2">