/* 
 * Document   : administrador.js
 * Created on : 24/06/2013, 21:28:45
 * Author     : Caio
 */

$(function(){
    $("#formCadasUsuario\\:nmUsuario").focus(function(){
        nomeUsuario();
    });
});

$(function(){
    $("#formCadasUsuario\\:nmUsuario").keypress(function(event){
        return nomeUsuarioMinusculo(event);
    });
});

$(function(){
    $("#formCadasUsuario\\:dsSenha").keypress(function(event){
        return digitoSenha(event);
    });
});

$(function(){
    $("#formCadasUsuario\\:dsConfirmaSenha").keypress(function(event){
        return digitoSenha(event);
    });
});

$(function(){
    $("#formAlterarSenha\\:dsSenhaAtual").keypress(function(event){
        return digitoSenha(event);
    });
});

$(function(){
    $("#formAlterarSenha\\:dsNovaSenha").keypress(function(event){
        return digitoSenha(event);
    });
});

$(function(){
    $("#formAlterarSenha\\:dsConfirmaNovaSenha").keypress(function(event){
        return digitoSenha(event);
    });
});

function nomeUsuario(){
    if (document.getElementById("formCadasUsuario:nrSequencia").value == 0) {

        var nmCliente = document.getElementById("formCadasUsuario:nmPessoa").value;
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

        $('#formCadasUsuario\\:nmUsuario').attr('value', s.toString().toLowerCase());
    }
}

function digitoSenha(event){
    var tecla = (window.event) ? event.keyCode : event.which;
 
    if ((tecla >= 0 && tecla <= 31) || (tecla >= 48 && tecla <= 57) || (tecla >= 65 && tecla <= 90) || (tecla >= 97 && tecla <= 122)) {
        return true;
    }
    return false;
}

function nomeUsuarioMinusculo(event){
    var tecla = (window.event) ? event.keyCode : event.which;
 
    if ((tecla >= 0 && tecla <= 31) || (tecla >= 97 && tecla <= 122)) {
        return true;
    }
    return false;
}