$(document).ready(function () {
    $("#dtEmissao").mask("99/99/9999");

    $("#valorVeiculo").keyup(function () {
        return valor($("#valorVeiculo"));
    });
    $("#subTotal").keyup(function () {
        return valor($("#subTotal"));
    });
    $("#desconto").keyup(function () {
        return valor($("#desconto"));
    });
    $("#valorTotal").keyup(function () {
        return valor($("#valorTotal"));
    });
    $("#valorOpcional").keyup(function () {
        return valor($("#valorOpcional"));
    });
});

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