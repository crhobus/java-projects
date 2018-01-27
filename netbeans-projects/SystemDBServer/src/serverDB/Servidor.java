package serverDB;

import java.sql.Connection;

public class Servidor {

    public Servidor(Connection con) {
        RequisicoesEncriptadas requisicoesEncriptadas = new RequisicoesEncriptadas(con);
        requisicoesEncriptadas.start();
        RequisicoesEncriptadaskeyStore requisicoesEncriptadaskeyStore = new RequisicoesEncriptadaskeyStore(con);
        requisicoesEncriptadaskeyStore.start();
    }
}
