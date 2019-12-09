<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Applications</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <script src="http://code.jquery.com/jquery-2.1.3.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
    <script src="https://rawgit.com/notifyjs/notifyjs/master/dist/notify.js"></script>
    <script>
        <c:set var="req" value="${pageContext.request}" />
        <c:set var="baseURL" value="${req.scheme}://${req.serverName}:${req.serverPort}${req.contextPath}" />

        function deleteRow(applicationId) {
            var baseURL = "${baseURL}";
            var url = baseURL + "/rest/applications/" + applicationId;
            $.ajax({
                url: url,
                type: 'DELETE',
                success: function (result) {
                    console.log(result);
                    if (result.status === 'success') {
                        $('table#applications tr#' + applicationId).remove();
                        $.notify("Application Deleted Successfully!!", "success");
                    }
                }
            });
        }
    </script>
</head>
<body>
<div class="container">
    <h1>
        Applications
    </h1>
    <table class="table" id="applications">
        <thead>
        <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Age</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${applications}" var="application">
            <tr id="${application.id}">
                <td>${application.firstName}</td>
                <td>${application.lastName}</td>
                <td>${application.age}</td>
                <td>
                    <button type="button" class="btn btn-primary" value="${application.id}"
                            onclick="deleteRow(${application.id})">Delete
                    </button>
                </td>
            </tr>
        </c:forEach>

        </tbody>
    </table>
</div>
</body>
</html>
