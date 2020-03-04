<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@include file="template/header.jsp"%>

<section class="row align-items-center h-100">
    <form action="j_security_check" method="post" class="col-md-6 mx-auto">
        <div class="jumbotron">
            <input type="text" id="usernameTextbox" name="j_username" class="p-1 mr-1" value="kkelm" placeholder="e-Mail Address" />
            <input type="password" id="passwordTextbox" name="j_username" class="p-1 mr-1" value="testPassword" placeholder="Password" />
            <button type="submit" class="btn btn-primary p-1 mr-2" role="button">Login</button>
            <a href="searchUser" class="btn btn-secondary p-1" role="button">Register</a>
        </div>
    </form>
</section>

<%@include file="template/footer.jsp"%>