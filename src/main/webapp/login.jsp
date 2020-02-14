<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@include file="template/header.jsp"%>

<section class="row align-items-center h-100">
    <form action="login" method="post" class="col-md-6 mx-auto">
        <div class="jumbotron">
            <input type="text" id="usernameTextbox" name="usernameTextbox" class="p-1 mr-1" value="" placeholder="e-Mail Address" />
            <input type="password" id="passwordTextbox" name="passwordTextbox" class="p-1 mr-1" value="" placeholder="Password" />
            <input type="submit" id="searchButton" name="searchButton" class="btn btn-primary p-1 mr-2" value="Search" />

            <button type="submit" class="btn btn-primary p-1 mr-2" role="button">Login</button>
            <a href="searchUser" class="btn btn-secondary p-1" role="button">Register</a>
        </div>
    </form>
</section>

<table class="table">

<c:choose>

    <c:when test="${fn:length(results) gt 0}">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>UserName</th>
            <th>Date of Birth</th>
            <th>Age</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${results}">
            <tr>
                <td>${user.id}</td>
                <td>${user.firstName} ${user.lastName}</td>
                <td>${user.username}</td>

                <td><c:out value="${user.state.toArray()[0].abbreviation}" /></td>
            </tr>
        </c:forEach>
        </tbody>
    </c:when>
    <c:otherwise>
        <tbody>
        <tr>
            <td>No One Found</td>
        </tr>
        </tbody>
    </c:otherwise>

</c:choose>

</table>

<%@include file="template/footer.jsp"%>