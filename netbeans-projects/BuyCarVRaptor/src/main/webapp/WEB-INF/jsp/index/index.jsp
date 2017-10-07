<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Buy Car VRaptor</title>
        <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.4.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.maskedinput.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/pedido.js"></script>
    </head>
    <body>
        <div class="central">

            <header class="top"></header>

            <div id="menu">
                <ul>
                    <li>
                        <a href="<c:url value="/cliente/listarClientes"/>"><button>Clientes</button></a>
                    </li>
                    <li>
                        <a href="<c:url value="/veiculo/listarVeiculos"/>"><button>Veiculos</button></a>
                    </li>
                    <li>
                        <a href="<c:url value="/opcional/listarOpcionais"/>"><button>Opcionais</button></a>
                    </li>
                    <li>
                        <a href="<c:url value="/listarPedidos"/>"><button>Pedidos</button></a>
                    </li>
                </ul>
            </div>

            <div id="pedido" class="pedido">
                <form action="add" method="POST">

                    <fieldset>

                        <div class="cadastro">

                            <legend>Pedido</legend>

                            <label for="nrSequencia">Nº Pedido:</label>
                            <input type="text" name="pedido.nrSequencia" id="nrSequencia" value="${pedido.nrSequencia}" readonly placeholder="Automático"><br/>

                            <label for="nrSeqCliente">Cliente:</label>
                            <select name="pedido.cliente.nrSequencia" id="nrSeqCliente" ${(pedido.situacao eq 'F' || pedido.situacao eq 'C') ? 'disabled' : ''}>
                                <option value="">Selecione</option>
                                <c:forEach var="cliente" items="${clientes}">
                                    <option value="${cliente.nrSequencia}" ${cliente.nrSequencia == pedido.cliente.nrSequencia ? 'selected' : ''}>
                                        ${cliente.nome} - 
                                        <c:choose>
                                            <c:when test="${cliente.tipoPessoa.equals('F')}">
                                                ${cliente.cpf}
                                            </c:when>
                                            <c:otherwise>
                                                ${cliente.cnpj}
                                            </c:otherwise>
                                        </c:choose>
                                    </option>
                                </c:forEach>
                            </select>
                            <span class="erro">${errors.from("pedido.cliente.nrSequencia")}</span><br/>

                            <label for="nrSeqVeiculo">Veículo:</label>
                            <select name="pedido.veiculo.nrSequencia" id="nrSeqVeiculo" ${(pedido.situacao eq 'F' || pedido.situacao eq 'C') ? 'disabled' : ''}>
                                <option value="">Selecione</option>
                                <c:forEach var="veiculo" items="${veiculos}">
                                    <option value="${veiculo.nrSequencia}" ${veiculo.nrSequencia == pedido.veiculo.nrSequencia ? 'selected' : ''}>
                                        ${veiculo.marca} - ${veiculo.modelo} - ${veiculo.cor} - <fmt:formatNumber minFractionDigits="1" maxFractionDigits="1" minIntegerDigits="1" maxIntegerDigits="1" value="${veiculo.litros}"/> - ${veiculo.potencia}cv - ${veiculo.valvulas}v - ${veiculo.categoria}
                                    </option>
                                </c:forEach>
                            </select>
                            <span class="erro">${errors.from("pedido.veiculo.nrSequencia")}</span><br/>

                            <label for="dtEmissao">Data Emissão:</label>
                            <input type="text" name="pedido.dtEmissao" id="dtEmissao" value="<fmt:formatDate value="${pedido.dtEmissao}" pattern="dd/MM/yyyy"/>" ${(pedido.situacao eq 'F' || pedido.situacao eq 'C') ? 'readonly' : ''}>
                            <span class="erro">${errors.from("pedido.dtEmissao")}</span><br/>

                            <label for="situacao">Situação:</label>
                            <select name="pedido.situacao" id="situacao" ${(pedido.situacao eq 'F' || pedido.situacao eq 'C') ? 'disabled' : ''}>
                                <option value="">Selecione</option>
                                <option value="A" ${pedido.situacao eq 'A'? 'selected' : ''}>Aberto</option>
                                <option value="F" ${pedido.situacao eq 'F'? 'selected' : ''}>Finalizado</option>
                                <option value="C" ${pedido.situacao eq 'C'? 'selected' : ''}>Cancelado</option>
                            </select>
                            <span class="erro">${errors.from("pedido.situacao")}</span><br/>

                            <label for="valorVeiculo">Valor veículo:</label>
                            <input type="text" name="pedido.valorVeiculo" id="valorVeiculo" value="<fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${pedido.valorVeiculo}"/>" ${(pedido.situacao eq 'F' || pedido.situacao eq 'C') ? 'readonly' : ''}>
                            <span class="erro">${errors.from("pedido.valorVeiculo")}</span><br/>

                            <label for="subTotal">Sub Total:</label>
                            <input type="text" name="pedido.subTotal" id="subTotal" value="<fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${pedido.subTotal}"/>" ${(pedido.situacao eq 'F' || pedido.situacao eq 'C') ? 'readonly' : ''}>
                            <span class="erro">${errors.from("pedido.subTotal")}</span><br/>

                            <label for="desconto">Desconto:</label>
                            <input type="text" name="pedido.desconto" id="desconto" value="<fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${pedido.desconto}"/>" ${(pedido.situacao eq 'F' || pedido.situacao eq 'C') ? 'readonly' : ''}>
                            <span class="erro">${errors.from("pedido.desconto")}</span><br/>

                            <label for="valorTotal">Valor Total:</label>
                            <input type="text" name="pedido.valorTotal" id="valorTotal" value="<fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${pedido.valorTotal}"/>" ${(pedido.situacao eq 'F' || pedido.situacao eq 'C') ? 'readonly' : ''}>
                            <span class="erro">${errors.from("pedido.valorTotal")}</span><br/>

                            <label for="condicaoPagto">Condição Pagto:</label>
                            <input type="text" name="pedido.condicaoPagto" id="condicaoPagto" value="${pedido.condicaoPagto}" ${(pedido.situacao eq 'F' || pedido.situacao eq 'C') ? 'readonly' : ''}>
                            <span class="erro">${errors.from("pedido.condicaoPagto")}</span><br/>

                            <br/>

                            <button type="submit" name="myButton" value="foo" class="botao_salvar" ${(pedido.situacao eq 'F' || pedido.situacao eq 'C') ? 'disabled' : ''}>Salvar</button>

                        </div>

                    </fieldset>

                </form>

                <a href="<c:url value="/"/>"><button>Novo</button></a>

                <br/>

                <div class="pedido">

                    <form action="addItem" method="POST">

                        <fieldset>

                            <div class="cadastro">

                                <legend>Itens Pedido</legend>

                                <div class="hidden"></div>

                                <input type="hidden" name="itemPedido.nrSequencia" id="nrSeqItemPedido" value="${itemPedido.nrSequencia}"><br/>

                                <input type="hidden" name="nrSeqPedido" id="nrSeqPedido" value="${pedido.nrSequencia}"><br/>

                                <label for="nrSeqOpcional">Opcional:</label>
                                <select name="itemPedido.opcional.nrSequencia" id="nrSeqOpcional" ${(pedido.situacao eq 'F' || pedido.situacao eq 'C') ? 'disabled' : ''}>
                                    <option value="">Selecione</option>
                                    <c:forEach var="opcional" items="${opcionais}">
                                        <option value="${opcional.nrSequencia}" ${opcional.nrSequencia == itemPedido.opcional.nrSequencia ? 'selected' : ''}>${opcional.nome}</option>
                                    </c:forEach>
                                </select>
                                <span class="erro">${errors.from("itemPedido.opcional.nrSequencia")}</span><br/>

                                <label for="valorOpcional">Valor opcional:</label>
                                <input type="text" name="itemPedido.valorOpcional" id="valorOpcional" value="<fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${itemPedido.valorOpcional}"/>" ${(pedido.situacao eq 'F' || pedido.situacao eq 'C') ? 'readonly' : ''}>
                                <span class="erro">${errors.from("itemPedido.valorOpcional")}</span><br/>

                                <br/>

                                <button type="submit" name="myButton" value="foo" class="botao_salvar"  ${(pedido.nrSequencia == null || pedido.situacao eq 'F' || pedido.situacao eq 'C') ? 'disabled' : ''}>Salvar</button>

                            </div>

                        </fieldset>

                    </form>
                </div>

                <br/>
                <h4>Itens Pedido</h4>
                <br/>

                <div class="table_itens_pedido">
                    <table width="100%">
                        <thead>
                            <tr>
                                <th>Opcional</th>
                                <th>Valor</th>
                                <th></th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${pedido.itensPedido}" var="itemPedido">
                                <tr>
                                    <td class="alinha_esquerda">${itemPedido.opcional.nome}</td>
                                    <td class="alinha_centro"><fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" type="currency" value="${itemPedido.valorOpcional}"/></td>
                                    <td class="largura_col_edit_remove">
                                        <a href="editarItem?nrSequencia=${itemPedido.nrSequencia}&nrSeqPedido=${pedido.nrSequencia}" ${(pedido.situacao eq 'F' || pedido.situacao eq 'C') ? 'hidden' : ''}>
                                            <img src="${pageContext.request.contextPath}/images/edit.png"  class="icones">
                                        </a>
                                    </td>
                                    <td class="largura_col_edit_remove">
                                        <a href="removerItem?nrSequencia=${itemPedido.nrSequencia}&nrSeqPedido=${pedido.nrSequencia}" ${(pedido.situacao eq 'F' || pedido.situacao eq 'C') ? 'hidden' : ''}>
                                            <img src="${pageContext.request.contextPath}/images/delete.png" class="icones">
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>

                <br/>
                <br/>

                <span class="msg_listar_pedido">${msg}</span>

            </div>

        </div>
    </body>
</html>
