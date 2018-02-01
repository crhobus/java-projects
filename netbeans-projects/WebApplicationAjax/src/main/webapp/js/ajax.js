function getAjax() {
    return new XMLHttpRequest();
}

function log(texto) {
    document.getElementById('log').innerHTML = texto;
}

document.addEventListener('DOMContentLoaded', function () {
    document.getElementById('btnLoadText').onclick = btnLoadTextClick;
    document.getElementById('btnLoadJson').onclick = btnLoadJsonClick;
    document.getElementById('btnLoadXml').onclick = btnLoadXmlClick;
});

function getFile(fileName, callback, mimiType) {
    var obj = getAjax();
    if (!obj) {
        alert('Erro na criação do objetoAjax');
        return;
    }
    obj.onreadystatechange = function () {
        if (obj.readyState == 4
                && obj.status == 200) {
            callback(obj);
        }
    };
    if (mimiType != '') {
        obj.overrideMimeType(mimiType);
    }
    obj.open('GET', fileName);
    obj.send(null);
}

function btnLoadTextClick() {
    getFile('arquivos/msg.txt', function (ajax) {
        log(ajax.responseText);
    }, 'text/html; charset=UTF-8');
}

function btnLoadJsonClick() {
    var func = function (ajax) {
        var livros = null;
        try {
            livros = JSON.parse(ajax.responseText).livros;
        } catch (e) {
            log('Erro: ' + e.message);
            return;
        }
        var i;
        var t, r, c;

        t = document.createElement('table');
        t.setAttribute('style', 'border: 1px solid blue; margin 15px');
        r = t.insertRow(0);
        r.setAttribute('style', 'background-color: yellow');

        c = r.insertCell(0);
        c.innerHTML = 'ID';

        c = r.insertCell(1);
        c.innerHTML = 'Título';

        c = r.insertCell(2);
        c.innerHTML = 'Autor';

        c = r.insertCell(3);
        c.innerHTML = 'Preço';

        c = r.insertCell(4);
        c.innerHTML = 'Site';

        for (i = 0; i < livros.length; i++) {
            var livro = livros[i];
            r = t.insertRow(i + 1);
            r.setAttribute('style', 'background-color: #0acb4a');

            c = r.insertCell(0);
            c.innerHTML = livro.id;

            c = r.insertCell(1);
            c.innerHTML = livro.titulo;

            c = r.insertCell(2);
            c.innerHTML = livro.autor;

            c = r.insertCell(3);
            c.innerHTML = livro.preco;

            c = r.insertCell(4);
            c.innerHTML = livro.site;
        }

        clearConsole();
        document.getElementById('log').appendChild(t);
    };

    getFile('arquivos/livros.json', func, 'application/json');
}

function clearNodes(node) {
    while (node.hasChildNodes()) {
        node.removeChild(node.lastChild);
    }
}

function clearConsole() {
    clearNodes(document.getElementById('log'));
}

function btnLoadXmlClick() {
    getFile('arquivos/livros.xml', function (ajax) {
        var xml = ajax.responseXML;
        var livros = xml.getElementsByTagName('livros')[0];
        var i;

        clearConsole();

        var div = document.createElement('div');
        for (i = 0; i < livros.childElementCount; i++) {
            var livro = livros.children[i];

            var item = document.createElement('div');
            item.setAttribute('style', 'background-color: #fff');

            //id
            var id = document.createElement('p');
            var text = document.createTextNode('id: ' + livro.children[0].innerHTML);
            id.appendChild(text);
            item.appendChild(id);

            //Título
            var title = document.createElement('h2');
            text = document.createTextNode('Título: ' + livro.children[1].innerHTML);
            title.appendChild(text);
            item.appendChild(title);

            //Autor
            var author = document.createElement('p');
            text = document.createTextNode('Autor: ' + livro.children[2].innerHTML);
            author.appendChild(text);
            item.appendChild(author);

            //Preço
            var price = document.createElement('p');
            text = document.createTextNode('Preço: ' + livro.children[3].innerHTML);
            price.appendChild(text);
            item.appendChild(price);

            //Site
            var site = document.createElement('a');
            site.setAttribute('href', livro.children[4].innerHTML);
            site.setAttribute('target', '_blank');
            text = document.createTextNode(livro.children[4].innerHTML);
            site.appendChild(text);
            item.appendChild(site);

            div.appendChild(item);
            div.appendChild(document.createElement('hr'));

        }//for
        log(div.innerHTML);
    },
            'text/xml; charset=iso-8859-1');
}
