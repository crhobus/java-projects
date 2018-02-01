function getAjax() {
    return new XMLHttpRequest();
}

function log(texto) {
    document.getElementById('log').innerHTML = texto;
}

function testeAjax() {
    var obj = getAjax();

    obj.onreadystatechange = function () {
        log('readyState: ' + obj.readyState
                + ', status: ' + obj.status
                + ', responseText: ' + obj.responseText);
    };

    obj.open('GET', 'arquivos/msg.txt');
    obj.send(null);
}