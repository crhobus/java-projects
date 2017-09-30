/*
 Documento   : script.js
 Disciplina  : Fundamentos do Desenvolvimento Web
 Equipe      : Caio Renan Hobus
               Daniel Zimmermann
 */

$(document).ready(function () {
    $('#slider').cycle();


    $(function () {

        //Ano
        var countdown_year = 2015;

        //MÊs
        var countdown_month = 3;

        //Dia
        var countdown_day = 26;

        var timeTo = new Date(parseInt(countdown_year), parseInt(countdown_month - 1), parseInt(countdown_day));

        $('#relogio_contador').countdown({until: timeTo, format: 'HMS'});
       
    });


});
