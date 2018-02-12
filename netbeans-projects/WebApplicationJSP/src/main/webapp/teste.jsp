<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Teste</title>
    </head>
    <body>
        <h1>Teste</h1>
        <jsp:useBean id="hoje" class="java.util.Date" scope="request" />
        <fmt:formatDate pattern="dd/MM/yyyy HH:mm" value="${hoje}" />
    </body>
</html>
