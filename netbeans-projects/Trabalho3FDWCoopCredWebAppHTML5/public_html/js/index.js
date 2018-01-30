$(document).ready(function() {
    $('#form_login').submit(function(evt) {

        if ($('#agencia').val().length < 1) {
            alert('Preencha o campo agência');
            $('#agencia').focus();
            return false;
        }
        if ($('#conta').val().length < 1) {
            alert('Preencha o campo conta');
            $('#conta').focus();
            return false;
        }
        if ($('#senha').val().length < 1) {
            alert('Preencha o campo senha');
            $('#senha').focus();
            return false;
        }
    });
    return true;
});