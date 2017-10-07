$(document).ready(function () {
    $("#anoFabricacao").mask("9999");
    $("#anoModelo").mask("9999");
    $("#portas").mask("9");
    $("#lotacao").mask("9");

    $("#potencia").keypress(function (event) {
        return somenteNumeros(event);
    });
    $("#valvulas").keypress(function (event) {
        return somenteNumeros(event);
    });
    $("#cilindros").keypress(function (event) {
        return somenteNumeros(event);
    });
    $("#valor").keyup(function () {
        return valor($("#valor"));
    });
    $("#litros").keyup(function () {
        return litros($("#litros"));
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

function valor(value) {
    v = value.val();
    v = v.replace(/\D/g, "");  //permite digitar apenas números
    v = v.replace(/[0-9]{12}/, "invalid");//limita pra máximo 999.999.999,99
    v = v.replace(/(\d{1})(\d{8})$/, "$1.$2");//coloca ponto antes dos últimos 8 digitos
    v = v.replace(/(\d{1})(\d{5})$/, "$1.$2");//coloca ponto antes dos últimos 5 digitos
    v = v.replace(/(\d{1})(\d{1,2})$/, "$1,$2");//coloca virgula antes dos últimos 2 digitos
    if (v == "invalid") {
        value.val("");
    } else {
        value.val(v);
    }
}

function litros(value) {
    v = value.val();
    v = v.replace(/\D/g, "");  //permite digitar apenas números
    v = v.replace(/[0-9]{3}/, "invalid");//limita pra máximo 9,9
    v = v.replace(/(\d{1})(\d{1})$/, "$1,$2");//coloca ponto antes do último digito
    if (v == "invalid") {
        value.val("");
    } else {
        value.val(v);
    }
}
