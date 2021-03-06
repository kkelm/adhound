<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>

    <link rel="stylesheet" href="./css/bootstrap/4.4.1/css/bootstrap.min.css" />
    <link rel="stylesheet" href="./css/font-awesome/all.min.css" />
    <link rel="stylesheet" href="./css/main.css" />

    <style>

        html,
        body {
            height: 100%;
        }

        body {
            display: -ms-flexbox;
            display: flex;
            -ms-flex-align: center;
            align-items: center;
            padding-top: 40px;
            padding-bottom: 40px;
            background-color: #fff;
        }

        .login-form {
            width: 100%;
            max-width: 330px;
            padding: 15px;
            margin: auto;
        }
        .login-form .checkbox {
            font-weight: 400;
        }
        .login-form .form-control {
            position: relative;
            box-sizing: border-box;
            height: auto;
            padding: 10px;
            font-size: 16px;
        }
        .login-form .form-control:focus {
            z-index: 2;
        }
        .login-form input[type="text"] {
            margin-bottom: 1em;
            border-bottom-right-radius: 0;
            border-bottom-left-radius: 0;
        }
        .form-signin input[type="password"] {
            margin-bottom: 10px;
            border-top-left-radius: 0;
            border-top-right-radius: 0;
        }

        .brand-logo {

        }

        @media (max-width: 767px) {
            .login-form {
                width: 90%;
                padding: 15px;
                margin: auto;
            }
        }

    </style>

</head>
<body class="text-center">

<form class="login-form" action="dashboard" method="get">
    <img class="brand-logo mb-2 w-100" src="./images/AdHound-Logo-Square.jpg" alt="AdHound">

    User '<%=request.getRemoteUser()%>' has been logged out.

    <% session.invalidate(); %>

    <br/><br/>

    <button type="submit" class="btn btn-primary btn-block mb-3" role="button">Login</button>

    <p class="mt-4 text-muted">&copy; 2020</p>

</form>

<script src="./js/jquery-3.4.1.min.js"></script>
<script src="./js/popper/popper.js"></script>
<script src="./css/bootstrap/4.4.1/js/bootstrap.min.js"></script>

</body>
</html>