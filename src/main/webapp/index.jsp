<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@include file="template/header.jsp"%>

<section class="row align-items-center h-100">
    <form action="userSearch" method="post" class="col-md-6 mx-auto">
        <div class="jumbotron">
            <input type="text" id="idTextbox" name="idTextbox" class="p-1 mr-1" value="" placeholder="ID" />
            <button type="submit" class="btn btn-primary p-1 mr-2" role="button">Search by ID</button>
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

                    <td><c:out value="${user.state.abbreviation}" /></td>
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