<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Veículos</title>
        <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="central">

            <header class="top"></header>

            <table width="100%">
                <thead>
                    <tr>
                        <th>Modelo</th>
                        <th>Marca</th>
                        <th>Ano de Fabricação</th>
                        <th>Ano Modelo</th>
                        <th>Cor</th>
                        <th>Combustível</th>
                        <th>Litros</th>
                        <th>Potência</th>
                        <th>Categoria</th>
                        <th>Valor</th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${veiculos}" var="veiculo">
                        <tr>
                            <td class="alinha_esquerda">${veiculo.modelo}</td>
                            <td class="alinha_esquerda">${veiculo.marca}</td>
                            <td class="alinha_centro">${veiculo.anoFabricacao}</td>
                            <td class="alinha_centro">${veiculo.anoModelo}</td>
                            <td class="alinha_esquerda">${veiculo.cor}</td>
                            <td class="alinha_esquerda">${veiculo.combustivel}</td>
                            <td class="alinha_centro"><fmt:formatNumber minFractionDigits="1" maxFractionDigits="1" minIntegerDigits="1" maxIntegerDigits="1" value="${veiculo.litros}"/></td>
                            <td class="alinha_centro">${veiculo.potencia}</td>
                            <td class="alinha_esquerda">${veiculo.categoria}</td>
                            <td class="alinha_centro"><fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" type="currency" value="${veiculo.valor}"/></td>
                            <td class="largura_col_edit_remove">
                                <a href="editar?nrSequencia=${veiculo.nrSequencia}">
                                    <img src="${pageContext.request.contextPath}/images/edit.png"  class="icones">
                                </a>
                            </td>
                            <td class="largura_col_edit_remove">
                                <a href="remover?nrSequencia=${veiculo.nrSequencia}">
                                    <img src="${pageContext.request.contextPath}/images/delete.png" class="icones">
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <br/>

            <a href="<c:url value="/veiculo/formVeiculo"/>"><button>Novo</button></a>
            <a href="<c:url value="/"/>"><button>Home</button></a>

            <br/>
            <br/>

            <span class="msg_listar">${msg}</span>

        </div>
    </body>
</html>
