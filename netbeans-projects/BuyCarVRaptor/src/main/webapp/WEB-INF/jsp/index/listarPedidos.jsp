<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pedidos</title>
        <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="central">

            <header class="top"></header>

            <table width="100%">
                <thead>
                    <tr>
                        <th>Nº Pedido</th>
                        <th>Cliente</th>
                        <th>Veículo</th>
                        <th>Data Emissão</th>
                        <th>Situação</th>
                        <th>Valor Total</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${pedidos}" var="pedido">
                        <tr>
                            <td class="alinha_centro">${pedido.nrSequencia}</td>
                            <td class="alinha_esquerda">
                                ${pedido.cliente.nome} - 
                                <c:choose>
                                    <c:when test="${pedido.cliente.tipoPessoa.equals('F')}">
                                        ${pedido.cliente.cpf}
                                    </c:when>
                                    <c:otherwise>
                                        ${pedido.cliente.cnpj}
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td class="alinha_esquerda">
                                ${pedido.veiculo.marca} - ${pedido.veiculo.modelo} - ${pedido.veiculo.cor} - <fmt:formatNumber minFractionDigits="1" maxFractionDigits="1" minIntegerDigits="1" maxIntegerDigits="1" value="${pedido.veiculo.litros}"/> - ${pedido.veiculo.potencia}cv - ${pedido.veiculo.valvulas}v - ${pedido.veiculo.categoria}
                            </td>
                            <td class="alinha_centro"><fmt:formatDate value="${pedido.dtEmissao}" pattern="dd/MM/yyyy"/></td>
                            <td class="alinha_esquerda">${pedido.situacao}</td>
                            <td class="alinha_centro"><fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" type="currency" value="${pedido.valorTotal}"/></td>
                            <td class="largura_col_edit_remove">
                                <a href="editar?nrSequencia=${pedido.nrSequencia}">
                                    <img src="${pageContext.request.contextPath}/images/edit.png"  class="icones">
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <br/>

            <a href="<c:url value="/"/>"><button>Home</button></a>

            <br/>
            <br/>

            <span class="msg_listar">${msg}</span>

        </div>
    </body>
</html>
