<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de Opcional</title>
        <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.4.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/opcional.js"></script>
    </head>
    <body>
        <div class="central">

            <header class="top"></header>

            <form action="add" method="POST">

                <fieldset>

                    <div class="cadastro">

                        <legend>Adicionar Opcional</legend>

                        <input type="hidden" name="opcional.nrSequencia" id="nrSequencia" value="${opcional.nrSequencia}"><br/>

                        <label for="nome">Nome:</label>
                        <input type="text" name="opcional.nome" id="nome" value="${opcional.nome}">
                        <span class="erro">${errors.from("opcional.nome")}</span><br/>

                        <label for="descricao">Descrição:</label>
                        <input type="text" name="opcional.descricao" id="descricao" value="${opcional.descricao}">
                        <span class="erro">${errors.from("opcional.descricao")}</span><br/>

                        <label for="valor">Valor:</label>
                        <input type="text" name="opcional.valor" id="valor" value="<fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${opcional.valor}"/>">
                        <span class="erro">${errors.from("opcional.valor")}</span><br/>

                        <br/>

                        <button type="submit" name="myButton" value="foo" class="botao_salvar">Salvar</button>

                    </div>

                </fieldset>

            </form>

            <a href="<c:url value="/opcional/formOpcional"/>"><button>Novo</button></a>
            <a href="<c:url value="/opcional/listarOpcionais"/>"><button>Listar</button></a>
            <a href="<c:url value="/"/>"><button>Home</button></a>

            <br/>
            <br/>

            <span class="msg">${msg}</span>

        </div>
    </body>
</html>
