<%@page import="model.entidades.Sabor"%>
<%@page import="java.util.List"%>
<%@page import="control.funcoes.Dados"%>
<%@page import="java.util.HashMap"%>
<%@page import="server.Index"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script type="text/javascript">
    function load()
    {
        //  alert("Page is loaded");
         
        //  document.getElementById('submit').onclick = function() {
        //      document.getElementById('MyForm').submit();
        // }
         
        // form.action="submit"
        // form.submit();
    }
</script>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="Stylesheet" href="css/principal.css" type="text/css"/>
        <link rel="Stylesheet" href="css/cardapio.css" type="text/css"/>
        <title>Pizza Nostra - Cardápio</title>
    </head>
    <body onload="load()">
        <form id="formCardapio" action="servletcardapio" >

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
                            <input type="submit" value="Sair" name="bt_sair_cardapio"/>
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
                            <input type="submit" value="OK" name="bt_ok_cardapio"/>
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
                                    <h2>Cardápio</h2>
                                </div>

                                <!--coloquem aqui a parte de vocês-->

                                <h3>Sabores de pizzas</h3>
                                <h2>                                      
                                    <%
                                        String teste = (String) request.getAttribute("dados");
                                        teste = teste == null ? "" : " " + teste;
                                    %>
                                    <%=teste%>
                                </h2>

                                <%
                                    boolean cor1 = true;
                                    List<Sabor> sabores = (List) request.getAttribute("sabores");
                                    if (sabores != null) {

                                        for (Sabor sab : sabores) {
                                            String nome = sab.getNmSabor();
                                            String desc = sab.getDsSabor();

                                            if (cor1) {
                                %>
                                <div id="cor1">
                                    <b><%=nome%></b>
                                    <br>
                                    <span id="corTexto">
                                        &nbsp;&nbsp;<%=desc%>
                                    </span>
                                </div>
                                <br>
                                <%
                                cor1 = false;
                                } else {
                                %>
                                <div id="cor2">
                                    <b><%=nome%></b>
                                    <br>
                                    <span id="corTexto">
                                        &nbsp;&nbsp;<%=desc%>
                                    </span>
                                </div>
                                <br>
                                <%  
                                cor1 = true;        
                                }
                                        }
                                    }
                                %>


                            </div>
                            <!--                            Fim    -->
                        </td>

                    </tr>

                </tbody>
            </table>
        </form>
    </body>
</html>
