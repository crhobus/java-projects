$(document).ready(function () {
    $("#cpf").mask("999.999.999-99");
    $("#cnpj").mask("99.999.999/9999-99");
    $("#telefone").mask("(99) 9999-9999");
    $("#cep").mask("99999-999");

    $("#numero").keypress(function (event) {
        return somenteNumeros(event);
    });
});

function somenteNumeros(event) {
    var tecla = (window.event) ? event.keyCode : event.which;

    if ((tecla >= 48 && tecla <= 57) || (tecla >= 0 && tecla <= 31)) {
        return true;
    } else {
        return false;
    }
}
