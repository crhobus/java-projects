/* 
 * Document   : permissao.js
 * Created on : 02/10/2013, 20:05:38
 * Author     : Caio
 */

$(function(){
    $("#formCadasPermissao\\:cdPermissaoComunic").keypress(function(event){
        return cdPermissaoComunicNumMaiusculo(event);
    });
});

function cdPermissaoComunicNumMaiusculo(event){
    var tecla = (window.event) ? event.keyCode : event.which;
 
    if ((tecla >= 0 && tecla <= 31) || (tecla >= 48 && tecla <= 57) || (tecla >= 65 && tecla <= 90)) {
        return true;
    }
    return false;
}