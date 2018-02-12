<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Contatos</title>
    </head>
    <body>
        <h1>Lista de Contatos</h1>
        <table border="1">
            <tr>
                <th>Nome</th>
                <th>Email</th>
                <th>Telefone</th>
            </tr>
            <c:forEach var="contato" items="${lista}">
                <tr>
                    <td>${contato.nome}</td>
                    <td>${contato.email}</td>
                    <td>${contato.telefone}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
