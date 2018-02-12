<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Student Enrollment Detail Confirmation</title>
        <link href="<c:url value='/static/css/custom.css'/>" rel="stylesheet"></link>
    </head>
    <body>

        <div class="success">
            Confirmation message : ${success}
            <br>
            We have also sent you a confirmation mail to your email address : ${student.email}.
        </div>

    </body>
</html>
