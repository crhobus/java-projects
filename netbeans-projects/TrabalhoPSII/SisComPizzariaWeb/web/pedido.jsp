<%@page import="model.entidades.Sabor"%>
<%@page import="java.util.List"%>
<%@page import="server.Index"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="Stylesheet" href="css/principal.css" type="text/css"/>
        <link rel="Stylesheet" href="css/pedido.css" type="text/css"/>
        <script type="text/javascript" src="js/pedido.js"></script>
        <title>Pizza Nostra - Pedido</title>
    </head>
    <body>
        <form id="formPedido" action="servletpedido">
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
                            <input type="submit" value="Sair" name="bt_sair_pedido"/>
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
                            <input type="submit" value="OK" name="bt_ok_pedido"/>
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
                                    <h2>Pedido</h2>
                                </div>

                                <%
                                    List<Sabor> sabores = (List) request.getAttribute("sabores");
                                %>

                                <!--coloquem aqui a parte de vocês-->
                                <!--Inicio Cadastro de Pedido -->
                                <div id="centralizado">
                                    <table>
                                        <tr>
                                            <td>
                                                <fieldset>
                                                    <legend> Preencha os dados abaixo: </legend>                                                
                                                    <select name="numPizzas" id="numPizzas" onchange="qtdePizzas(this.value)" >
                                                        <option value="">Selecione...</option>
                                                        <option value="1">1</option>
                                                        <option value="2">2</option>
                                                        <option value="3">3</option>
                                                        <option value="4">4</option>
                                                        <option value="5">5</option>
                                                    </select>
                                                    <span class="style1">* </span>
                                                    <br>
                                                    </div>
                                                    <br>

                                                    <div id="itemPedido1">
                                                        <fieldset>
                                                            <Legend> Item 1: </legend>
                                                            <div id="tamPizza1">
                                                                <label> Tamanho: </label>
                                                                <input type="radio" name="tamanho1" value="1" onclick="qtdeSabores('pequena1')"/> Pequena
                                                                <input type="radio" name="tamanho1" value="2" onclick="qtdeSabores('media1')" /> Media
                                                                <input type="radio" name="tamanho1" value="3" onclick="qtdeSabores('grande1')" /> Grande
                                                                <input type="radio" name="tamanho1" value="4" onclick="qtdeSabores('gigante1')" /> Gigante
                                                            </div>

                                                            <table>
                                                                <label>Sabores: </label>
                                                                <tr>
                                                                    <td>
                                                                        <div id="sabor1_1">
                                                                            <select name="1-1" id="1-1">
                                                                                <option value="" > Selecione... </option>
                                                                                <%
                                                                                    if (sabores != null) {
                                                                                        for (Sabor sab : sabores) {
                                                                                            Integer codigo = sab.getCdSabor();
                                                                                            String nome = sab.getNmSabor();
                                                                                            String desc = sab.getDsSabor();

                                                                                %>
                                                                                <option value="" id="<%=codigo%>"> <%=nome%> </option>           
                                                                                <%
                                                                                        }
                                                                                    }
                                                                                %>
                                                                            </select>
                                                                        </div>
                                                                    </td>
                                                                    <td>
                                                                        <div id="sabor2_1">
                                                                            <select name="1-2" id="1-2">
                                                                                <option value=""> Selecione... </option>
                                                                                <%
                                                                                    if (sabores != null) {
                                                                                        for (Sabor sab : sabores) {
                                                                                            Integer codigo = sab.getCdSabor();
                                                                                            String nome = sab.getNmSabor();
                                                                                            String desc = sab.getDsSabor();

                                                                                %>
                                                                                <option value="" id="<%=codigo%>"> <%=nome%> </option>           
                                                                                <%
                                                                                        }
                                                                                    }
                                                                                %>
                                                                            </select>
                                                                        </div>
                                                                    </td>
                                                                    <td>
                                                                        <div id="sabor3_1">
                                                                            <select name="1-3" id="1-3">
                                                                                <option value=""> Selecione... </option>
                                                                                <%
                                                                                    if (sabores != null) {
                                                                                        for (Sabor sab : sabores) {
                                                                                            Integer codigo = sab.getCdSabor();
                                                                                            String nome = sab.getNmSabor();
                                                                                            String desc = sab.getDsSabor();

                                                                                %>
                                                                                <option value="" name="3-1" id="<%=codigo%>"> <%=nome%> </option>           
                                                                                <%
                                                                                        }
                                                                                    }
                                                                                %>
                                                                            </select>
                                                                        </div>
                                                                    </td>
                                                                    <td>
                                                                        <div id="sabor4_1">
                                                                            <select name="1-4" id="1-4">
                                                                                <option value=""> Selecione... </option>
                                                                                <%
                                                                                    if (sabores != null) {
                                                                                        for (Sabor sab : sabores) {
                                                                                            Integer codigo = sab.getCdSabor();
                                                                                            String nome = sab.getNmSabor();
                                                                                            String desc = sab.getDsSabor();

                                                                                %>
                                                                                <option value="" name="4-1" id="<%=codigo%>"> <%=nome%> </option>           
                                                                                <%
                                                                                        }
                                                                                    }
                                                                                %>
                                                                            </select>
                                                                        </div>
                                                                    </td>
                                                                </tr>
                                                            </table>                                                                    
                                                        </fieldset>
                                                    </div>

                                                    <div id="itemPedido2">
                                                        <fieldset>
                                                            <Legend> Item 1: </legend>
                                                            <div id="tamPizza2">
                                                                <label> Tamanho: </label>
                                                                <input type="radio" name="tamanho2" value="pequena" onclick="qtdeSabores('pequena2')"/> Pequena
                                                                <input type="radio" name="tamanho2" value="media" onclick="qtdeSabores('media2')" /> Media
                                                                <input type="radio" name="tamanho2" value="grande" onclick="qtdeSabores('grande2')" /> Grande
                                                                <input type="radio" name="tamanho2" value="gigante" onclick="qtdeSabores('gigante2')" /> Gigante
                                                            </div>

                                                            <table>
                                                                <label>Sabores: </label>
                                                                <tr>
                                                                    <td>
                                                                        <div id="sabor1_2">
                                                                            <select>
                                                                                <option value=""> Selecione... </option>
                                                                                <%
                                                                                    if (sabores != null) {
                                                                                        for (Sabor sab : sabores) {
                                                                                            Integer codigo = sab.getCdSabor();
                                                                                            String nome = sab.getNmSabor();
                                                                                            String desc = sab.getDsSabor();

                                                                                %>
                                                                                <option value="" id="<%=codigo%>"> <%=nome%> </option>           
                                                                                <%
                                                                                        }
                                                                                    }
                                                                                %>
                                                                            </select>
                                                                        </div>
                                                                    </td>
                                                                    <td>
                                                                        <div id="sabor2_2">
                                                                            <select>
                                                                                <option value=""> Selecione... </option>
                                                                                <%
                                                                                    if (sabores != null) {
                                                                                        for (Sabor sab : sabores) {
                                                                                            Integer codigo = sab.getCdSabor();
                                                                                            String nome = sab.getNmSabor();
                                                                                            String desc = sab.getDsSabor();

                                                                                %>
                                                                                <option value="" id="<%=codigo%>"> <%=nome%> </option>           
                                                                                <%
                                                                                        }
                                                                                    }
                                                                                %>
                                                                            </select>
                                                                        </div>
                                                                    </td>
                                                                    <td>
                                                                        <div id="sabor3_2">
                                                                            <select>
                                                                                <option value=""> Selecione... </option>
                                                                                <%
                                                                                    if (sabores != null) {
                                                                                        for (Sabor sab : sabores) {
                                                                                            Integer codigo = sab.getCdSabor();
                                                                                            String nome = sab.getNmSabor();
                                                                                            String desc = sab.getDsSabor();

                                                                                %>
                                                                                <option value="" id="<%=codigo%>"> <%=nome%> </option>           
                                                                                <%
                                                                                        }
                                                                                    }
                                                                                %>
                                                                            </select>
                                                                        </div>
                                                                    </td>
                                                                    <td>
                                                                        <div id="sabor4_2">
                                                                            <select>
                                                                                <option value=""> Selecione... </option>
                                                                                <%
                                                                                    if (sabores != null) {
                                                                                        for (Sabor sab : sabores) {
                                                                                            Integer codigo = sab.getCdSabor();
                                                                                            String nome = sab.getNmSabor();
                                                                                            String desc = sab.getDsSabor();

                                                                                %>
                                                                                <option value="" id="<%=codigo%>"> <%=nome%> </option>           
                                                                                <%
                                                                                        }
                                                                                    }
                                                                                %>
                                                                            </select>
                                                                        </div>
                                                                    </td>
                                                                </tr>
                                                            </table>                                                                    
                                                        </fieldset>
                                                    </div>

                                                    <div id="itemPedido3">
                                                        <fieldset>
                                                            <Legend> Item 3: </legend>
                                                            <div id="tamPizza3">
                                                                <label> Tamanho: </label>
                                                                <input type="radio" name="tamanho3" value="pequena" onclick="qtdeSabores('pequena3')"/> Pequena
                                                                <input type="radio" name="tamanho3" value="media" onclick="qtdeSabores('media3')" /> Media
                                                                <input type="radio" name="tamanho3" value="grande" onclick="qtdeSabores('grande3')" /> Grande
                                                                <input type="radio" name="tamanho3" value="gigante" onclick="qtdeSabores('gigante3')" /> Gigante
                                                            </div>

                                                            <table>
                                                                <label>Sabores: </label>
                                                                <tr>
                                                                    <td>
                                                                        <div id="sabor1_3">
                                                                            <select>
                                                                                <option value=""> Selecione... </option>
                                                                                <%
                                                                                    if (sabores != null) {
                                                                                        for (Sabor sab : sabores) {
                                                                                            Integer codigo = sab.getCdSabor();
                                                                                            String nome = sab.getNmSabor();
                                                                                            String desc = sab.getDsSabor();

                                                                                %>
                                                                                <option value="" id="<%=codigo%>"> <%=nome%> </option>           
                                                                                <%
                                                                                        }
                                                                                    }
                                                                                %>
                                                                            </select>
                                                                        </div>
                                                                    </td>
                                                                    <td>
                                                                        <div id="sabor2_3">
                                                                            <select>
                                                                                <option value=""> Selecione... </option>
                                                                                <%
                                                                                    if (sabores != null) {
                                                                                        for (Sabor sab : sabores) {
                                                                                            Integer codigo = sab.getCdSabor();
                                                                                            String nome = sab.getNmSabor();
                                                                                            String desc = sab.getDsSabor();

                                                                                %>
                                                                                <option value="" id="<%=codigo%>"> <%=nome%> </option>           
                                                                                <%
                                                                                        }
                                                                                    }
                                                                                %>
                                                                            </select>
                                                                        </div>
                                                                    </td>
                                                                    <td>
                                                                        <div id="sabor3_3">
                                                                            <select>
                                                                                <option value=""> Selecione... </option>
                                                                                <%
                                                                                    if (sabores != null) {
                                                                                        for (Sabor sab : sabores) {
                                                                                            Integer codigo = sab.getCdSabor();
                                                                                            String nome = sab.getNmSabor();
                                                                                            String desc = sab.getDsSabor();

                                                                                %>
                                                                                <option value="" id="<%=codigo%>"> <%=nome%> </option>           
                                                                                <%
                                                                                        }
                                                                                    }
                                                                                %>
                                                                            </select>
                                                                        </div>
                                                                    </td>
                                                                    <td>
                                                                        <div id="sabor4_3">
                                                                            <select>
                                                                                <option value=""> Selecione... </option>
                                                                                <%
                                                                                    if (sabores != null) {
                                                                                        for (Sabor sab : sabores) {
                                                                                            Integer codigo = sab.getCdSabor();
                                                                                            String nome = sab.getNmSabor();
                                                                                            String desc = sab.getDsSabor();

                                                                                %>
                                                                                <option value="" id="<%=codigo%>"> <%=nome%> </option>           
                                                                                <%
                                                                                        }
                                                                                    }
                                                                                %>
                                                                            </select>
                                                                        </div>
                                                                    </td>
                                                                </tr>
                                                            </table>                                                                    
                                                        </fieldset>
                                                    </div>

                                                    <div id="itemPedido4">
                                                        <fieldset>
                                                            <Legend> Item 4: </legend>
                                                            <div id="tamPizza4">
                                                                <label> Tamanho: </label>
                                                                <input type="radio" name="tamanho4" value="pequena" onclick="qtdeSabores('pequena4')"/> Pequena
                                                                <input type="radio" name="tamanho4" value="media" onclick="qtdeSabores('media4')" /> Media
                                                                <input type="radio" name="tamanho4" value="grande" onclick="qtdeSabores('grande4')" /> Grande
                                                                <input type="radio" name="tamanho4" value="gigante" onclick="qtdeSabores('gigante4')" /> Gigante
                                                            </div>

                                                            <table>
                                                                <label>Sabores: </label>
                                                                <tr>
                                                                    <td>
                                                                        <div id="sabor1_4">
                                                                            <select>
                                                                                <option value=""> Selecione... </option>
                                                                                <%
                                                                                    if (sabores != null) {
                                                                                        for (Sabor sab : sabores) {
                                                                                            Integer codigo = sab.getCdSabor();
                                                                                            String nome = sab.getNmSabor();
                                                                                            String desc = sab.getDsSabor();

                                                                                %>
                                                                                <option value="" id="<%=codigo%>"> <%=nome%> </option>           
                                                                                <%
                                                                                        }
                                                                                    }
                                                                                %>
                                                                            </select>
                                                                        </div>
                                                                    </td>
                                                                    <td>
                                                                        <div id="sabor2_4">
                                                                            <select>
                                                                                <option value=""> Selecione... </option>
                                                                                <%
                                                                                    if (sabores != null) {
                                                                                        for (Sabor sab : sabores) {
                                                                                            Integer codigo = sab.getCdSabor();
                                                                                            String nome = sab.getNmSabor();
                                                                                            String desc = sab.getDsSabor();

                                                                                %>
                                                                                <option value="" id="<%=codigo%>"> <%=nome%> </option>           
                                                                                <%
                                                                                        }
                                                                                    }
                                                                                %>
                                                                            </select>
                                                                        </div>
                                                                    </td>
                                                                    <td>
                                                                        <div id="sabor3_4">
                                                                            <select>
                                                                                <option value=""> Selecione... </option>
                                                                                <%
                                                                                    if (sabores != null) {
                                                                                        for (Sabor sab : sabores) {
                                                                                            Integer codigo = sab.getCdSabor();
                                                                                            String nome = sab.getNmSabor();
                                                                                            String desc = sab.getDsSabor();

                                                                                %>
                                                                                <option value="" id="<%=codigo%>"> <%=nome%> </option>           
                                                                                <%
                                                                                        }
                                                                                    }
                                                                                %>
                                                                            </select>
                                                                        </div>
                                                                    </td>
                                                                    <td>
                                                                        <div id="sabor4_4">
                                                                            <select>
                                                                                <option value=""> Selecione... </option>
                                                                                <%
                                                                                    if (sabores != null) {
                                                                                        for (Sabor sab : sabores) {
                                                                                            Integer codigo = sab.getCdSabor();
                                                                                            String nome = sab.getNmSabor();
                                                                                            String desc = sab.getDsSabor();

                                                                                %>
                                                                                <option value="" id="<%=codigo%>"> <%=nome%> </option>           
                                                                                <%
                                                                                        }
                                                                                    }
                                                                                %>
                                                                            </select>
                                                                        </div>
                                                                    </td>
                                                                </tr>
                                                            </table>                                                                    
                                                        </fieldset>
                                                    </div>

                                                    <div id="itemPedido5">
                                                        <fieldset>
                                                            <Legend> Item 5: </legend>
                                                            <div id="tamPizza5">
                                                                <label> Tamanho: </label>
                                                                <input type="radio" name="tamanho5" value="pequena" onclick="qtdeSabores('pequena5')"/> Pequena
                                                                <input type="radio" name="tamanho5" value="media" onclick="qtdeSabores('media5')" /> Media
                                                                <input type="radio" name="tamanho5" value="grande" onclick="qtdeSabores('grande5')" /> Grande
                                                                <input type="radio" name="tamanho5" value="gigante" onclick="qtdeSabores('gigante5')" /> Gigante
                                                            </div>

                                                            <table>
                                                                <label>Sabores: </label>
                                                                <tr>
                                                                    <td>
                                                                        <div id="sabor1_5">
                                                                            <select>
                                                                                <option value=""> Selecione... </option>
                                                                                <%
                                                                                    if (sabores != null) {
                                                                                        for (Sabor sab : sabores) {
                                                                                            Integer codigo = sab.getCdSabor();
                                                                                            String nome = sab.getNmSabor();
                                                                                            String desc = sab.getDsSabor();

                                                                                %>
                                                                                <option value="" id="<%=codigo%>"> <%=nome%> </option>           
                                                                                <%
                                                                                        }
                                                                                    }
                                                                                %>
                                                                            </select>
                                                                        </div>
                                                                    </td>
                                                                    <td>
                                                                        <div id="sabor2_5">
                                                                            <select>
                                                                                <option value=""> Selecione... </option>
                                                                                <%
                                                                                    if (sabores != null) {
                                                                                        for (Sabor sab : sabores) {
                                                                                            Integer codigo = sab.getCdSabor();
                                                                                            String nome = sab.getNmSabor();
                                                                                            String desc = sab.getDsSabor();

                                                                                %>
                                                                                <option value="" id="<%=codigo%>"> <%=nome%> </option>           
                                                                                <%
                                                                                        }
                                                                                    }
                                                                                %>
                                                                            </select>
                                                                        </div>
                                                                    </td>
                                                                    <td>
                                                                        <div id="sabor3_5">
                                                                            <select>
                                                                                <option value=""> Selecione... </option>
                                                                                <%
                                                                                    if (sabores != null) {
                                                                                        for (Sabor sab : sabores) {
                                                                                            Integer codigo = sab.getCdSabor();
                                                                                            String nome = sab.getNmSabor();
                                                                                            String desc = sab.getDsSabor();

                                                                                %>
                                                                                <option value="" id="<%=codigo%>"> <%=nome%> </option>           
                                                                                <%
                                                                                        }
                                                                                    }
                                                                                %>
                                                                            </select>
                                                                        </div>
                                                                    </td>
                                                                    <td>
                                                                        <div id="sabor4_5">
                                                                            <select>
                                                                                <option value=""> Selecione... </option>
                                                                                <%
                                                                                    if (sabores != null) {
                                                                                        for (Sabor sab : sabores) {
                                                                                            Integer codigo = sab.getCdSabor();
                                                                                            String nome = sab.getNmSabor();
                                                                                            String desc = sab.getDsSabor();

                                                                                %>
                                                                                <option value="" id="<%=codigo%>"> <%=nome%> </option>           
                                                                                <%
                                                                                        }
                                                                                    }
                                                                                %>
                                                                            </select>
                                                                        </div>
                                                                    </td>
                                                                </tr>
                                                            </table>                                                                    
                                                        </fieldset>
                                                    </div>



                                                    <br>
                                                    <input type="submit" id="bt_continuar" name="bt_continuar" value="Continuar"/>
                                                    <br><span class="style1">* Campos com * são obrigatórios! </span></p>
                                                </fieldset>										
                                            </td>
                                        </tr>
                                    </table>                           

                                </div>
                                <!-- Fim Cadastro de pedido de Pizza -->


                            </div>
                            <!--                            Fim    -->
                        </td>

                    </tr>

                </tbody>
            </table>
        </form>
    </body>
</html>
