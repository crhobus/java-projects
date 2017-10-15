<%-- 
    Document   : index
    Created on : May 16, 2012, 8:34:20 PM
    Author     : Caio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
        <title>Exercício JavaScript - Cálculo Índice de Massa Corpórea</title>
        <link rel="Stylesheet" href="estilo.css" type="text/css"/>
    </head>
    <body>

        <form id="imc" action="calculoimc">

            <div id="centralizado">

                <h1>Cálculo Índice de Massa Corpórea</h1>

                <fieldset>

                    <label for="nome">Nome:</label>
                    <input type="text" name="nome" id="nome"/></br>

                    <label for="peso">Peso:</label>
                    <input type="text" name="peso" id="peso"/></br>

                    <label for="altura">Altura:</label>
                    <input type="text" name="altura" id="altura"/></br>

                    <input type="submit" id="btCalcularIMC" value="Calcular IMC"/>

                </fieldset>

            </div>

        </form>

    </body>
</html>
