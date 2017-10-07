<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Clientes</title>
        <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="central">

            <header class="top"></header>

            <table width="100%">
                <thead>
                    <tr>
                        <th>Nome</th>
                        <th>Tipo de pessoa</th>
                        <th>CPF</th>
                        <th>CNPJ</th>
                        <th>Telefone</th>
                        <th>Email</th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${clientes}" var="cliente">
                        <tr>
                            <td class="alinha_esquerda">${cliente.nome}</td>
                            <td class="alinha_esquerda">${cliente.tipoPessoa}</td>
                            <td class="alinha_esquerda">${cliente.cpf}</td>
                            <td class="alinha_esquerda">${cliente.cnpj}</td>
                            <td class="alinha_esquerda">${cliente.telefone}</td>
                            <td class="alinha_esquerda">${cliente.email}</td>
                            <td class="largura_col_edit_remove">
                                <a href="editar?nrSequencia=${cliente.nrSequencia}">
                                    <img src="${pageContext.request.contextPath}/images/edit.png"  class="icones">
                                </a>
                            </td>
                            <td class="largura_col_edit_remove">
                                <a href="remover?nrSequencia=${cliente.nrSequencia}">
                                    <img src="${pageContext.request.contextPath}/images/delete.png" class="icones">
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <br/>

            <a href="<c:url value="/cliente/formCliente"/>"><button>Novo</button></a>
            <a href="<c:url value="/"/>"><button>Home</button></a>

            <br/>
            <br/>

            <span class="msg_listar">${msg}</span>

        </div>
    </body>
</html>
