<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@include file="template/header.jsp"%>

<section class="row align-items-center h-100">

    <div class="col-md-8 mx-auto">
        <h2 class="mb-3">Registration</h2>

        <c:choose>
            <c:when test="${fn:length(results) gt 0}">
                <c:forEach var="user" items="${results}">
                    ${user.lastName}
                </c:forEach>
            </c:when>
            <c:otherwise></c:otherwise>
        </c:choose>

        <form action="register" method="post" class="needs-validation" novalidate>

            <div class="row">
                <div class="mb-3">
                    <label for="emailTextbox">e-Mail Address</label>
                    <div class="input-group">
                        <input value="test@email.com" required type="email" id="emailTextbox" name="emailTextbox" class="form-control" placeholder="you@example.com" />
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="mb-3">
                    <label for="passwordTextbox">Password</label>
                    <div class="input-group">
                        <input value="123abc" required type="password" id="passwordTextbox" name="passwordTextbox" class="form-control" />
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-md-6 mb-3">
                    <label for="firstNameTextbox">First Name</label>
                    <input value="Kevin" required type="text" id="firstNameTextbox" name="firstNameTextbox" class="form-control" />
                </div>
                <div class="col-md-6 mb-3">
                    <label for="lastNameTextbox">Last Name</label>
                    <input value="Kelm" required type="text" id="lastNameTextbox" name="lastNameTextbox" class="form-control" />
                </div>
            </div>

            <div class="row">
                <div class="col-md-6 mb-3">
                    <label for="phoneTextbox">Phone</label>
                    <input value="(123) 456-7890" required type="text" id="phoneTextbox" name="phoneTextbox" class="form-control" />
                </div>
                <div class="col-md-6 mb-3">
                    <label for="faxTextbox">Last Name</label>
                    <input value="(987) 654-3210" required type="text" id="faxTextbox" name="faxTextbox" class="form-control" />
                </div>
            </div>

            <div class="row">
                <div class="mb-3">
                    <label for="addresssTextbox">Street Address</label>
                    <div class="input-group">
                        <input value="123 Test Road" required type="text" id="addresssTextbox" name="addresssTextbox" class="form-control" />
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-md-5 mb-3">
                    <label for="cityTextbox">City</label>
                    <input value="Madison" required type="text" id="cityTextbox" name="cityTextbox" class="form-control" />
                </div>
                <div class="col-md-3 mb-3">
                    <label for="stateIdDropdown">Phone</label>
                    <select required type="text" id="stateIdDropdown" name="stateIdDropdown" class="form-control">
                        <c:forEach var="state" items="${states}">
                            <option value="${state.id}">${state.abbreviation}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-md-4 mb-3">
                    <label for="zipcodeTextbox">Last Name</label>
                    <input value="12345-6789" required type="text" id="zipcodeTextbox" name="zipcodeTextbox" class="form-control" />
                </div>
            </div>

            <div class="row">
                <div class="mb-3">
                    <button type="submit" class="btn btn-primary p-1 mr-2" role="button">Register</button>
                </div>
            </div>
        </form>
    </div>
</section>

<%@include file="template/footer.jsp"%>