<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de Veículo</title>
        <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.4.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.maskedinput.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/veiculo.js"></script>
    </head>
    <body>
        <div class="central">

            <header class="top"></header>

            <form action="add" method="POST">

                <fieldset>

                    <div class="cadastro">

                        <legend>Adicionar Veículo</legend>

                        <input type="hidden" name="veiculo.nrSequencia" id="nrSequencia" value="${veiculo.nrSequencia}"><br/>

                        <label for="modelo">Modelo:</label>
                        <input type="text" name="veiculo.modelo" id="modelo" value="${veiculo.modelo}">
                        <span class="erro">${errors.from("veiculo.modelo")}</span><br/>

                        <label for="marca">Marca:</label>
                        <input type="text" name="veiculo.marca" id="marca" value="${veiculo.marca}">
                        <span class="erro">${errors.from("veiculo.marca")}</span><br/>

                        <label for="anoFabricacao">Ano de fabricação:</label>
                        <input type="text" name="veiculo.anoFabricacao" id="anoFabricacao" value="${veiculo.anoFabricacao}">
                        <span class="erro">${errors.from("veiculo.anoFabricacao")}</span><br/>

                        <label for="anoModelo">Ano modelo:</label>
                        <input type="text" name="veiculo.anoModelo" id="anoModelo" value="${veiculo.anoModelo}">
                        <span class="erro">${errors.from("veiculo.anoModelo")}</span><br/>

                        <label for="cor">Cor:</label>
                        <input type="text" name="veiculo.cor" id="cor" value="${veiculo.cor}">
                        <span class="erro">${errors.from("veiculo.cor")}</span><br/>

                        <label for="combustivel">Combustível:</label>
                        <select name="veiculo.combustivel" id="combustivel">
                            <option value="">Selecione</option>
                            <option value="G" ${veiculo.combustivel eq 'G'? 'selected' : ''}>Gasolina</option>
                            <option value="A" ${veiculo.combustivel eq 'A'? 'selected' : ''}>Alcool</option>
                            <option value="F" ${veiculo.combustivel eq 'F'? 'selected' : ''}>Flex</option>
                            <option value="D" ${veiculo.combustivel eq 'D'? 'selected' : ''}>Diesel</option>
                        </select>
                        <span class="erro">${errors.from("veiculo.combustivel")}</span><br/>

                        <label for="portas">Portas:</label>
                        <input type="text" name="veiculo.portas" id="portas" value="${veiculo.portas}">
                        <span class="erro">${errors.from("veiculo.portas")}</span><br/>

                        <label for="litros">Litros:</label>
                        <input type="text" name="veiculo.litros" id="litros" value="<fmt:formatNumber minFractionDigits="1" maxFractionDigits="1" minIntegerDigits="1" maxIntegerDigits="1" value="${veiculo.litros}"/>">
                        <span class="erro">${errors.from("veiculo.litros")}</span><br/>

                        <label for="potencia">Potência:</label>
                        <input type="text" name="veiculo.potencia" id="potencia" value="${veiculo.potencia}">
                        <span class="erro">${errors.from("veiculo.potencia")}</span><br/>

                        <label for="cilindros">Cilindros:</label>
                        <input type="text" name="veiculo.cilindros" id="cilindros" value="${veiculo.cilindros}">
                        <span class="erro">${errors.from("veiculo.cilindros")}</span><br/>

                        <label for="valvulas">Valvulas:</label>
                        <input type="text" name="veiculo.valvulas" id="valvulas" value="${veiculo.valvulas}">
                        <span class="erro">${errors.from("veiculo.valvulas")}</span><br/>

                        <label for="categoria">Categoria:</label>
                        <input type="text" name="veiculo.categoria" id="categoria" value="${veiculo.categoria}">
                        <span class="erro">${errors.from("veiculo.categoria")}</span><br/>

                        <label for="lotacao">Lotacao:</label>
                        <input type="text" name="veiculo.lotacao" id="lotacao" value="${veiculo.lotacao}">
                        <span class="erro">${errors.from("veiculo.lotacao")}</span><br/>

                        <label for="valor">Valor:</label>
                        <input type="text" name="veiculo.valor" id="valor" value="<fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${veiculo.valor}"/>">
                        <span class="erro">${errors.from("veiculo.valor")}</span><br/>

                        <br/>

                        <button type="submit" name="myButton" value="foo" class="botao_salvar">Salvar</button>

                    </div>

                </fieldset>

            </form>

            <a href="<c:url value="/veiculo/formVeiculo"/>"><button>Novo</button></a>
            <a href="<c:url value="/veiculo/listarVeiculos"/>"><button>Listar</button></a>
            <a href="<c:url value="/"/>"><button>Home</button></a>

            <br/>
            <br/>

            <span class="msg">${msg}</span>

        </div>
    </body>
</html>
