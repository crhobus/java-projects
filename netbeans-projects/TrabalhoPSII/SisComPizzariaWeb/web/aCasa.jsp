<%@page import="server.Index"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="Stylesheet" href="css/principal.css" type="text/css"/>
        <link rel="Stylesheet" href="css/aCasa.css" type="text/css"/>
        <title>Pizza Nostra - A Casa</title>
    </head>
    <body>
        <form id="formACasa" action="servletindex">
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
                            <input type="submit" value="Sair" name="bt_sair_aCasa"/>
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
                            <input type="submit" value="OK" name="bt_ok_aCasa"/>
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
                                    <h2>A Casa</h2>
                                </div>
                                <div>
                                    <div>
                                        <div id="texto_p1">
                                            <br>
                                            <div>Há mais de 20 anos, a Pizza Nostra conquista paladares e amigos com o melhor da cozinha italiana, unindo deliciosos pratos com ingredientes especiais,</div>
                                            <div>profissionais qualificados e atendimento único.</div>
                                            <br>
                                            <div>Em novembro de 2009, o restaurante também conquistou o prêmio de "Melhor Pizzaria do mundo", consagrando o conceito de que é sinônimo de bom gosto e</div>
                                            <div>referência naquilo que faz.</div>
                                            <br>
                                            <div>Além das deliciosas pizzas, a Pizza Nostra oferece um cardápio variado de massas, carnes e peixes. Também dispõe de saboroso e exclusivo Menu Executivo, </div>
                                            <div>servido de segunda a sexta, no almoço e jantar, acompanhado de exclusiva carta de vinhos e deliciosas cervejas.</div>
                                            <br>
                                            <br>
                                            <div><b>AMBIENTES</b></div>
                                            <div>
                                                <br>
                                            </div>
                                            <div>A Pizza Nostra dispõe de dois ambientes internos planejados e um deck privado para surpreender você desde o primeiro olhar.</div>
                                            <div>Tudo isso acompanhado por uma lareira que proporciona agradáveis momentos nos dias e nas noites frias do inverno blumenauense.</div>
                                        </div>
                                        <div>
                                            <div id="imgs">
                                                <ul id="lista_imgs">
                                                    <li>
                                                        <img src="imagens/acasa_01.jpg"  height="221" />
                                                    </li>
                                                    <li>
                                                        <img src="imagens/acasa_02.jpg"  height="221" />
                                                    </li>
                                                    <li>
                                                        <img src="imagens/acasa_03.jpg"  height="221" />
                                                    </li>
                                                </ul>
                                            </div>
                                            <p id="texto_p2">
                                                A Pizza Nostra oferece, além das pizzas deliciosas um menu executivo especial e um cardápio variado de massas, carnes e peixes. Escolha a opção a seu gosto e delicie-se!
                                            </p>
                                        </div>
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