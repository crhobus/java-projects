$(function(){
    $("#nm_usuario").focus(function(){
        nomeUsuario();
    });
});

$(function(){
    $("#nr_residencia").keypress(function(event){
        return somenteNumeros(event);
    });
});

$(function(){
    $("#nr_cep").mask("99999-999");
    $("#nr_telefone").mask("(99)9999-9999");
    $("#nr_celular").mask("(99)9999-9999");
});

$(function(){
    $("#bt_salvar").click(function(){
        validaCamposObrigatorios();
    });
});

function nomeUsuario(){
    var nmCliente = document.getElementById("nm_cliente").value;
    var nmUsuario = nmCliente.split(" ");
        
    var str = "";
    var s = "";
    for (var i = 0; i < nmUsuario.length; i++) {
        str = nmUsuario[i];
        if (i == nmUsuario.length - 1) {
            s = s.concat(str);
        } else {
            s = s.concat(str.substring(0, 1));
        }
    }
    
    $('#nm_usuario').attr('value', s.toString().toLowerCase());
}

function somenteNumeros(event){
    var tecla = (window.event) ? event.keyCode : event.which;
 
    if ((tecla >= 48 && tecla <= 57) || (tecla >= 0 && tecla <= 31)) {
        return true;
    }
    else {
        return false;
    }
}

function validaCamposObrigatorios(){
    var campoPreenchido = true;
    if (document.getElementById("nm_cliente").value == "") {
        campoPreenchido = false;
    } else if (document.getElementById("nm_usuario").value == "") {
        campoPreenchido = false;
    } else if (document.getElementById("ds_senha").value == "") {
        campoPreenchido = false;
    } else if (document.getElementById("ds_endereco").value == "") {
        campoPreenchido = false;
    } else if (document.getElementById("ds_bairro").value == "") {
        campoPreenchido = false;
    } else if (document.getElementById("nr_cep").value == "") {
        campoPreenchido = false;
    } else if (document.getElementById("nr_residencia").value == "") {
        campoPreenchido = false;
    } else if (document.getElementById("nm_cidade").value == "") {
        campoPreenchido = false;
    } 
    if (!campoPreenchido) {
        alert("Campos obrigatórios não preenchido");
    } else {
        document.forms["formCliente"].submit();
    }
}