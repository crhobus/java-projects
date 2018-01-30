$(document).ready(function() {
    $('#form_orcamento').submit(function(event) {

        event.preventDefault();

        $('#nome').removeClass("campo_obrigatorio");
        $('#email').removeClass("campo_obrigatorio");
        $('#mensagem').removeClass("campo_obrigatorio");

        if ($('#nome').val().length < 1) {
            alert('Preencha o campo nome');
            $('#nome').focus();
            $('#nome').addClass("campo_obrigatorio");
            return false;
        }
        if ($('#email').val().length < 1) {
            alert('Preencha o campo email');
            $('#email').addClass("campo_obrigatorio");
            $('#email').focus();
            return false;
        }
        if ($('#mensagem').val().length < 1) {
            alert('Preencha o campo mensagem');
            $('#mensagem').focus();
            $('#mensagem').addClass("campo_obrigatorio");
            return false;
        }
        $('#box_sucesso').show();
    });

    $('#box_sucesso').click(function() {
        $(this).hide();
    });

    $("#data").datepicker({
        dateFormat: 'dd/mm/yy',
        dayNames: ['Domingo', 'Segunda', 'Terça', 'Quarta', 'Quinta', 'Sexta', 'Sábado'],
        dayNamesMin: ['D', 'S', 'T', 'Q', 'Q', 'S', 'S', 'D'],
        dayNamesShort: ['Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sáb', 'Dom'],
        monthNames: ['Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho', 'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro'],
        monthNamesShort: ['Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez'],
        nextText: 'Próximo',
        prevText: 'Anterior'
    });

    return true;
});