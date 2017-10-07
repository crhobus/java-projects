<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Opcionais</title>
        <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="central">

            <header class="top"></header>

            <table width="100%">
                <thead>
                    <tr>
                        <th>Nome</th>
                        <th>Valor</th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${opcionais}" var="opcional">
                        <tr>
                            <td class="alinha_esquerda">${opcional.nome}</td>
                            <td class="alinha_centro"><fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" type="currency" value="${opcional.valor}"/></td>
                            <td class="largura_col_edit_remove">
                                <a href="editar?nrSequencia=${opcional.nrSequencia}">
                                    <img src="${pageContext.request.contextPath}/images/edit.png"  class="icones">
                                </a>
                            </td>
                            <td class="largura_col_edit_remove">
                                <a href="remover?nrSequencia=${opcional.nrSequencia}">
                                    <img src="${pageContext.request.contextPath}/images/delete.png" class="icones">
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <br/>

            <a href="<c:url value="/opcional/formOpcional"/>"><button>Novo</button></a>
            <a href="<c:url value="/"/>"><button>Home</button></a>

            <br/>
            <br/>

            <span class="msg_listar">${msg}</span>

        </div>
    </body>
</html>
