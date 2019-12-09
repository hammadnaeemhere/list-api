<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>Anmeldeformular - Kennenlerntagaufgabe</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <script src="http://code.jquery.com/jquery-2.1.3.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

    <script>
        <c:set var="req" value="${pageContext.request}" />
        <c:set var="baseURL" value="${req.scheme}://${req.serverName}:${req.serverPort}${req.contextPath}" />
        $(document).ready(function () {
            $("#age").focusout(function (e) {
                var age = (e.target.value);
                var baseURL = "${baseURL}";

                var url = baseURL + "/rest/age/" + age;

                $.getJSON(url, function (data) {
                    $("#age-status").html(data.status);
                    $("#age-error").html("");
                }).fail(function (jqxhr, settings, ex) {
                    console.log('Age validation failed, ' + ex);
                });
            });
        });
    </script>
</head>

<body>
<div class="container">
    <h1>${formName}</h1>
    <c:url var="post_url" value="/"/>
    <form:form method="POST" modelAttribute="application" action="${post_url}">
        <spring:hasBindErrors name="application">
            <div class="alert alert-danger">
                <strong>Error!</strong> Please check below form for error.
            </div>
        </spring:hasBindErrors>

        <spring:bind path="lastName">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label for="lastName">Last Name</label>
                <form:input path="lastName" type="text" name="lastName" class="form-control" id="lastName"
                            placeholder="Nachname"/>
                <c:if test="${status.error}">
                    <span class="help-block">Last Name is required</span>
                </c:if>
            </div>
        </spring:bind>

        <spring:bind path="firstName">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label for="firstName">First Name</label>
                <form:input path="firstName" type="text" name="firstName" class="form-control"
                            id="firstName"
                            placeholder="Vorname"/>
                <c:if test="${status.error}">
                    <span class="help-block">First Name is required</span>
                </c:if>
            </div>
        </spring:bind>

        <spring:bind path="age">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label for="age">Age</label>
                <form:input path="age" type="number" name="age" class="form-control"
                            id="age"
                            pattern="[0-9]"
                            placeholder="Age"/>
                <span id="age-status"></span>
                <c:if test="${status.error}">
                    <span class="help-block">Please provide valid value for Age!</span>
                </c:if>
                <c:if test="${ageError}">
                    <span id="age-error" class="help-block">Age is: ${ageStatus}</span>
                </c:if>
            </div>
        </spring:bind>
        <button type="submit" class="btn btn-default">Submit!</button>
    </form:form>
</div>
</body>
</html>
