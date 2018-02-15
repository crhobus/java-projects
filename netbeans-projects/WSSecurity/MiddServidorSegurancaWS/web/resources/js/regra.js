/* 
 * Document   : permissao.js
 * Created on : 02/10/2013, 20:45:01
 * Author     : Caio
 */

$(function(){
    $("#formCadasRegra\\:cdRegraComunic").keypress(function(event){
        return cdRegraComunicNumMaiusculo(event);
    });
});

function cdRegraComunicNumMaiusculo(event){
    var tecla = (window.event) ? event.keyCode : event.which;
 
    if ((tecla >= 0 && tecla <= 31) || (tecla >= 48 && tecla <= 57) || (tecla >= 65 && tecla <= 90)) {
        return true;
    }
    return false;
}