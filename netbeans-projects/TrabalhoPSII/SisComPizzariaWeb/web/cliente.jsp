<%@page import="control.funcoes.Dados"%>
<%@page import="java.util.HashMap"%>
<%@page import="server.Index"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="Stylesheet" href="css/principal.css" type="text/css"/>
        <link rel="Stylesheet" href="css/cliente.css" type="text/css"/>
        <script type="text/javascript" src="jquery/jquery-1.7.2.js"></script>
        <script type="text/javascript" src="jquery/jquery.maskedinput-1.3.js"></script>
        <script type="text/javascript" src="js/cliente.js"></script>
        <title>Pizza Nostra - Clientes</title>
    </head>
    <body>
        <form id="formCliente" action="servletcliente">

            <%
                HashMap<String, String> dados;
                Dados d = Index.getDadosCliente();
                if (d != null) {
                    dados = d.getDados();
                } else {
                    dados = (HashMap<String, String>) request.getAttribute("dados");
                }
            %>

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
                            <input type="submit" value="Sair" name="bt_sair_cliente"/>
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
                            <input type="submit" value="OK" name="bt_ok_cliente"/>
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

                                <div id="centralizado">

                                    <div id="titulo">
                                        <h2>Cadastro de Cliente</h2>
                                    </div>

                                    <table>
                                        <tr>
                                            <td class="nm_cliente">
                                                <label for="nome">Nome</label>
                                                <input type="text" name="nm_cliente" value="<%= dados == null ? "" : dados.get("NM_CLIENTE")%>" id="nm_cliente"/></br>
                                            </td>

                                            <td class="nm_usuario">
                                                <label for="usuario">Usuário</label>
                                                <input type="text" name="nm_usuario" value="<%= dados == null ? "" : dados.get("NM_USUARIO")%>" id="nm_usuario"/></br>
                                            </td>

                                            <td class="ds_senha">
                                                <label for="senha">Senha</label>
                                                <input type="password" name="ds_senha" id="ds_senha"/></br>
                                            </td>
                                        </tr>
                                    </table>

                                    <table>
                                        <tr>
                                            <td class="ds_confirma_senha">
                                                <label for="confirma_senha">Confirma Senha</label>
                                                <input type="password" name="ds_confirma_senha" id="ds_confirma_senha"/></br>
                                            </td>

                                            <td class="ds_endereco">
                                                <label for="endereco">Endereço</label>
                                                <input type="text" name="ds_endereco" value="<%= dados == null ? "" : dados.get("DS_ENDERECO")%>" id="ds_endereco"/></br>
                                            </td>
                                        </tr>
                                    </table>

                                    <table>
                                        <tr>
                                            <td class="ds_bairro">
                                                <label for="bairro">Bairro</label>
                                                <input type="text" name="ds_bairro" value="<%= dados == null ? "" : dados.get("DS_BAIRRO")%>" id="ds_bairro"/></br>
                                            </td>

                                            <td class="nr_cep">
                                                <label for="cep">Cep</label>
                                                <input type="text" name="nr_cep" value="<%= dados == null ? "" : dados.get("NR_CEP")%>" id="nr_cep"/></br>
                                            </td>

                                            <td class="nr_residencia">
                                                <label for="numero">Número</label>
                                                <input type="text" name="nr_residencia" value="<%= dados == null ? "" : dados.get("NR_RESIDENCIA")%>" id="nr_residencia"/></br>
                                            </td>
                                        </tr>
                                    </table>

                                    <table>
                                        <tr>
                                            <td class="nm_cidade">
                                                <label for="cidade">Cidade</label>
                                                <input type="text" name="nm_cidade" value="<%= dados == null ? "" : dados.get("NM_CIDADE")%>" id="nm_cidade"/></br>
                                            </td>

                                            <td class="ds_referencia">
                                                <label for="referencia">Referência</label>
                                                <input type="text" name="ds_referencia" value="<%= dados == null ? "" : dados.get("DS_REFERENCIA")%>" id="ds_referencia"/></br>
                                            </td>
                                        </tr>
                                    </table>

                                    <table>
                                        <tr>
                                            <td class="nr_telefone">
                                                <label for="telefone">Telefone</label>
                                                <input type="text" name="nr_telefone" value="<%= dados == null ? "" : dados.get("NR_TELEFONE")%>" id="nr_telefone"/></br>
                                            </td>

                                            <td class="nr_celular">
                                                <label for="celular">Celular</label>
                                                <input type="text" name="nr_celular" value="<%= dados == null ? "" : dados.get("NR_CELULAR")%>" id="nr_celular"/></br>
                                            </td>

                                            <td class="ds_email">
                                                <label for="email">Email</label>
                                                <input type="text" name="ds_email" value="<%= dados == null ? "" : dados.get("DS_EMAIL")%>" id="ds_email"/></br>
                                            </td>
                                        </tr>
                                    </table>

                                    <table>
                                        <tr>
                                            <td class="ds_observacao">
                                                <label for="observacao">Observação</label>
                                                <input type="text" name="ds_observacao" value="<%= dados == null ? "" : dados.get("DS_OBSERVACAO")%>" id="ds_observacao"/></br>
                                            </td>
                                        </tr>
                                    </table>

                                    <button type="button" id="bt_salvar">Salvar</button>

                                    <input type="reset" id="bt_cancelar" value="Cancelar"/>

                                    <%
                                        String msg = (String) request.getAttribute("msg");
                                    %>

                                    <h5 id="msg">
                                        <%= msg == null ? "" : msg%>
                                    </h5>
                                    </br>
                                    </br>

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
