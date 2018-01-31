<%@page import="server.Index"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="Stylesheet" href="css/principal.css" type="text/css"/>
        <link rel="Stylesheet" href="css/promocoes.css" type="text/css"/>
        <title>Pizza Nostra - Promoções</title>
    </head>
    <body>
        <form id="formPromocoes" action="servletindex">
            <table id="principal">
                <thead>
                    <tr>

                        <th id="cabecalho_p1">
                        </th>

                        <th>
                            <a href="index.jsp">
                                <img src="imagens/logo.png" alt="Pizza Nostra"/>
                            </a>
                        </th>

                        <th id="cabecalho_p2">
                        </th>

                        <%if (Index.getCdCliente() > 0) {%>

                        <th id="bemVindo">
                            Seja bem vindo, <%=Index.getDadosCliente().getInfo("NM_CLIENTE")%>
                        </th>

                        <th id="cabecalho_p8">
                            <input type="submit" value="Sair" name="bt_sair_promocoes"/>
                        </th>

                        <th id="cabecalho_p9">                            
                        </th>

                        <%} else {%>

                        <th id="cabecalho_p3">
                            <input type="text" name="usuario" id="usuario" placeholder="usuario">
                            </br>
                        </th>

                        <th id="cabecalho_p4">
                            <input type="password" name="senha" id="senha" placeholder="senha"/>
                            </br>
                        </th>

                        <th id="cabecalho_p5">
                            <input type="submit" value="OK" name="bt_ok_promocoes"/>
                        </th>

                        <th id="cabecalho_p6">
                            <a href="cliente.jsp">Cadastre-se</a>
                        </th>

                        <%}%>

                        <th id="cabecalho_p7">
                        </th>

                    </tr>

                </thead>
                <tbody>
                    <tr>

                        <td id="conteudo_p1">
                        </td>

                        <td valign=top id="conteudo_p2">
                            <ul class="barraLateral">
                                <li>
                                    <a href="aCasa.jsp" id="casa">A Casa</a>
                                </li>
                                <li>
                                    <a href="horarios.jsp" id="horarios">Horários</a>
                                </li>
                                <li>
                                    <a href="promocoes.jsp" id="promocoes">Promoções</a>
                                </li>
                                <li>
                                    <a href="contato.jsp" id="contato">Contato</a>
                                </li>
                                <li>
                                    <a href="servletcardapio" id="cardapio">Cardápio</a>
                                </li>
                                <li>
                                    <a href="cliente.jsp" id="cadastro">Cadastro</a>
                                </li>
                                <li>
                                    <a href="servletpedido" id="pedido">Pedido</a>
                                </li>
                            </ul>
                        </td>

                        <td rowspan="2" colspan="7" id="conteudo_p3">
                            <!--                            Inicio -->
                            <div>
                                <div id="mensagem">
                                    <h5>                                      
                                        <%
                                            String mensagem = (String) request.getAttribute("mensagem");
                                            mensagem = mensagem == null ? "" : " " + mensagem;
                                        %>
                                        <%=mensagem%>
                                    </h5>
                                </div>

                                </br>
                                </br>
                                </br>
                                <%if ("".equalsIgnoreCase(mensagem)) {%>
                                </br>
                                </br>
                                <%}%>

                                <div id="titulo">
                                    <h2>Promoções</h2>
                                </div>

                                <div>
                                    <div>
                                        <div id="imgs">
                                            <ul id="lista_imgs">
                                                <li>
                                                    <div>Pizza baiana pequena com refrigerante de 600ml apenas R$ 15,00*</div>
                                                    <br>
                                                    <img src="imagens/pizzas/Promo/giganteCoca2l.JPG"  height="221" />
                                                </li>
                                                <br>
                                                <br>
                                                <li>
                                                    <div>Pizza de vegetariana e marguerita grande com refrigerante de dois litros sai apenas R$ 30,00*</div>
                                                    <br>
                                                    <img src="imagens/pizzas/Promo/vegetarianaCoca.jpg"  height="221" />
                                                </li>
                                                <br>
                                                <br>
                                                <li>
                                                    <div>Pizza de calabreza grande com refrigerante de dois litros apenas R$ 35,00*</div>
                                                    <br>
                                                    <img src="imagens/pizzas/Promo/CalabrezaMCoca.jpg"  height="221" />
                                                </li>
                                                <br>
                                                <br>
                                                <li>
                                                    <div>Pizza de calabreza média com uma garrafa de vinho tinto e uma pizza doce pequena por R$ 75,00*</div>
                                                    <br>
                                                    <img src="imagens/pizzas/Promo/VinhoMediaDoce.jpg"  height="221" />
                                                </li>
                                                <div>*Sem incluir taxa de entrega</div>
                                            </ul>
                                        </div>
                                        <p id="texto_p">
                                            A Pizza Nostra oferece, além das pizzas deliciosas um menu executivo especial e um cardápio variado de massas, carnes e peixes. Escolha a opção a seu gosto e delicie-se!
                                        </p>
                                    </div>
                                </div>

                            </div>
                            <!--                            Fim    -->
                        </td>

                    </tr>

                </tbody>
            </table>
        </form>
    </body>
</html>