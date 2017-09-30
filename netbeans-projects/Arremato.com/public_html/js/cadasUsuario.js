/*
 Documento   : cadasUsuario.js
 Disciplina  : Fundamentos do Desenvolvimento Web
 Equipe      : Caio Renan Hobus
               Daniel Zimmermann
 */

var validouCadastro;

$(document).ready(function() {

    $('#data_nasc_usuario').mask('99/99/9999');
    $('#celular_usuario').mask('(99) 9999-9999');
    $('#cpf_usuario').mask('999.999.999-99');
    $('#rg_usuario').mask('9.999.999');

    $('#form_cadastro_usuario').submit(function(evt) {

        evt.preventDefault();

        validaCadastro();

    });

    $("#senha_usuario").keypress(function(evt) {
        return caracteresDigitosSenha(evt);
    });

    $("#confirmacao_senha_usuario").keypress(function(evt) {
        return caracteresDigitosSenha(event);
    });
});

function validaCadastro() {
    validouCadastro = true;
    $(".msg_erro").remove();

    $('#login_usuario').removeClass("campo_obrigatorio");
    $('#senha_usuario').removeClass("campo_obrigatorio");
    $('#confirmacao_senha_usuario').removeClass("campo_obrigatorio");
    $('#nome_usuario').removeClass("campo_obrigatorio");
    $('#cpf_usuario').removeClass("campo_obrigatorio");
    $('#rg_usuario').removeClass("campo_obrigatorio");
    $('#sexo_usuario').removeClass("campo_obrigatorio");
    $('#estado_civil_usuario').removeClass("campo_obrigatorio");
    $('#data_nasc_usuario').removeClass("campo_obrigatorio");
    $('#celular_usuario').removeClass("campo_obrigatorio");
    $('#email_usuario').removeClass("campo_obrigatorio");

    valida('#login_usuario');
    valida('#senha_usuario');
    valida('#confirmacao_senha_usuario');
    valida('#nome_usuario');
    valida('#cpf_usuario');
    valida('#rg_usuario');
    valida('#sexo_usuario');
    valida('#estado_civil_usuario');
    valida('#data_nasc_usuario');
    valida('#celular_usuario');
    valida('#email_usuario');

    if (!validouCadastro) {
        $(".msg_erro_cadastro").append("<span class=\"msg_erro\">Cadastro contém campos não preenchidos. Favor informá-los!</span>");
        return false;
    }

    if ($("#senha_usuario").val().length < 8) {
        $("#senha_usuario").addClass("campo_obrigatorio");
        $(".msg_erro_cadastro").append("<span class=\"msg_erro\">A senha deve ter no mínimo 8 dígitos!</span>");
        return false;
    }

    if ($("#senha_usuario").val() !== $("#confirmacao_senha_usuario").val()) {
        $("#confirmacao_senha_usuario").addClass("campo_obrigatorio");
        $(".msg_erro_cadastro").append("<span class=\"msg_erro\">Confirme sua senha!</span>");
        return false;
    }

    if ($("#cpf_usuario").val().length < 14) {
        $("#cpf_usuario").addClass("campo_obrigatorio");
        $(".msg_erro_cadastro").append("<span class=\"msg_erro\">Informe um CPF válido!</span>");
        return false;
    }

    if ($("#rg_usuario").val().length < 9) {
        $("#rg_usuario").addClass("campo_obrigatorio");
        $(".msg_erro_cadastro").append("<span class=\"msg_erro\">Informe um RG válido!</span>");
        return false;
    }

    if ($("#data_nasc_usuario").val().length < 10) {
        $("#data_nasc_usuario").addClass("campo_obrigatorio");
        $(".msg_erro_cadastro").append("<span class=\"msg_erro\">Informe uma data válida!</span>");
        return false;
    }

    if ($("#celular_usuario").val().length < 14) {
        $("#celular_usuario").addClass("campo_obrigatorio");
        $(".msg_erro_cadastro").append("<span class=\"msg_erro\">Informe um número de celular válido!</span>");
        return false;
    }
}

function valida(idCampo) {
    if ($(idCampo).val().length < 1) {
        $(idCampo).addClass("campo_obrigatorio");
        validouCadastro = false;
    }
}

function caracteresDigitosSenha(event) {
    var tecla = (window.event) ? event.keyCode : event.which;

    if ((tecla >= 0 && tecla <= 31)
            || (tecla >= 48 && tecla <= 57)
            || (tecla >= 64 && tecla <= 90)
            || (tecla >= 97 && tecla <= 122)) {
        return true;
    }
    else {
        return false;
    }
}
