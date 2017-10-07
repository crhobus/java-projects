<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de Cliente</title>
        <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.4.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.maskedinput.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/cliente.js"></script>
    </head>
    <body>
        <div class="central">

            <header class="top"></header>

            <form action="add" method="POST">

                <fieldset>

                    <div class="cadastro">

                        <legend>Adicionar Cliente</legend>

                        <input type="hidden" name="cliente.nrSequencia" id="nrSequencia" value="${cliente.nrSequencia}"><br/>

                        <label for="nome">Nome:</label>
                        <input type="text" name="cliente.nome" id="nome" value="${cliente.nome}">
                        <span class="erro">${errors.from("cliente.nome")}</span><br/>

                        <label for="tipoPessoa">Tipo de Pessoa:</label>
                        <select name="cliente.tipoPessoa" id="tipoPessoa" style="width: 164px">
                            <option value="">Selecione</option>
                            <option value="F" ${cliente.tipoPessoa eq 'F'? 'selected' : ''}>Pessoa Física</option>
                            <option value="J" ${cliente.tipoPessoa eq 'J'? 'selected' : ''}>Pessoa Jurídica</option>
                        </select>
                        <span class="erro">${errors.from("cliente.tipoPessoa")}</span><br/>

                        <label for="cpf">CPF:</label>
                        <input type="text" name="cliente.cpf" id="cpf" value="${cliente.cpf}">
                        <span class="erro">${errors.from("cliente.cpf")}</span><br/>

                        <label for="cnpj">CNPJ:</label>
                        <input type="text" name="cliente.cnpj" id="cnpj" value="${cliente.cnpj}">
                        <span class="erro">${errors.from("cliente.cnpj")}</span><br/>

                        <label for="telefone">Telefone:</label>
                        <input type="text" name="cliente.telefone" id="telefone" value="${cliente.telefone}">
                        <span class="erro">${errors.from("cliente.telefone")}</span><br/>

                        <label for="email">Email:</label>
                        <input type="text" name="cliente.email" id="email" value="${cliente.email}">
                        <span class="erro">${errors.from("cliente.email")}</span><br/>

                        <label for="endereco">Endereço:</label>
                        <input type="text" name="cliente.endereco" id="endereco" value="${cliente.endereco}">
                        <span class="erro">${errors.from("cliente.endereco")}</span><br/>

                        <label for="bairro">Bairro:</label>
                        <input type="text" name="cliente.bairro" id="bairro" value="${cliente.bairro}">
                        <span class="erro">${errors.from("cliente.bairro")}</span><br/>

                        <label for="numero">Número:</label>
                        <input type="text" name="cliente.numero" id="numero" value="${cliente.numero}">
                        <span class="erro">${errors.from("cliente.numero")}</span><br/>

                        <label for="cep">CEP:</label>
                        <input type="text" name="cliente.cep" id="cep" value="${cliente.cep}">
                        <span class="erro">${errors.from("cliente.cep")}</span><br/>

                        <label for="cidade">Cidade:</label>
                        <input type="text" name="cliente.cidade" id="cidade" value="${cliente.cidade}">
                        <span class="erro">${errors.from("cliente.cidade")}</span><br/>

                        <label for="estado">Estado:</label>
                        <input type="text" name="cliente.estado" id="estado" value="${cliente.estado}">
                        <span class="erro">${errors.from("cliente.estado")}</span><br/>

                        <br/>

                        <button type="submit" name="myButton" value="foo" class="botao_salvar">Salvar</button>

                    </div>

                </fieldset>

            </form>

            <a href="<c:url value="/cliente/formCliente"/>"><button>Novo</button></a>
            <a href="<c:url value="/cliente/listarClientes"/>"><button>Listar</button></a>
            <a href="<c:url value="/"/>"><button>Home</button></a>

            <br/>
            <br/>

            <span class="msg">${msg}</span>

        </div>
    </body>
</html>
