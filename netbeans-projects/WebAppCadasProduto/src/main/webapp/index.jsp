<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Produtos</title>
        <link rel="stylesheet" type="text/css" href="${contextPath}/css/style.css"/>
    </head>
    <body>
        <h1>Cadastro de Produtos</h1>
        <form action="produtoservlet" method="POST">

            <input type="hidden" name="id" id="id" readonly value="${produto.id}" class="field"><br/>

            <label for="nome" class="label">Nome:</label>
            <input type="text" name="nome" id="nome" value="${produto.nome}" required class="field"><br/>

            <label for="unidade_medida" class="label">Unidade Medida:</label>
            <input type="text" name="unidade_medida" id="unidade_medida" value="${produto.unidadeMedida}" required class="field"><br/>

            <label for="preco" class="label">Pre&ccedil;o:</label>
            <input type="text" name="preco" id="preco" value="${produto.preco}" required class="field"><br/>

            <br/>

            <input type="submit" value="Salvar" class="button">

        </form>

        <br/>
        <br/>

        <table border="1">
            <tr>
                <th>Id</th>
                <th>Nome</th>
                <th>Unidade Medida</th>
                <th>Pre&ccedil;o</th>
                <th>Excluir</th>
                <th>Atualizar</th>
            </tr>
            <c:forEach var="produto" items="${produtos}">
                <tr>
                    <td>${produto.id}</td>
                    <td>${produto.nome}</td>
                    <td>${produto.unidadeMedida}</td>
                    <td>${produto.preco}</td>
                    <td align="center"><a href="${contextPath}/produtoservlet/excluir?id=${produto.id}"><img src="${contextPath}/img/close.png" width="25" height="25"/></a></td>
                    <td align="center"><a href="${contextPath}/produtoservlet/atualizar?id=${produto.id}"><img src="${contextPath}/img/atualizar.png" width="25" height="25"/></a></td>
                </tr>
            </c:forEach>
        </table>

    </body>
</html>
