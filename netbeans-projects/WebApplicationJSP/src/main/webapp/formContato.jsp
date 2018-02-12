<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${titulo}</title>
    </head>
    <body>
        <h1>${titulo}</h1>
        <form>
            Nome:
            <input type="text" id="nome" value="${contato.nome}" name="nome"><br>
            Email:
            <input type="text" id="email" value="${contato.email}" name="email"><br>
            Telefone:
            <input type="text" id="telefone" value="${contato.telefone}" name="telefone"><br>
            <button type="submit">Salvar</button>
        </form>
    </body>
</html>
