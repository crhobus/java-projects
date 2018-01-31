package Principal;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

import DAOFactory.DAOFactory;

public class Sistema {

    public static void main(String[] args) {
        DAOFactory daoFactory = DAOFactory.getFactory(1);
        Connection con = null;
        try {
            Class.forName("org.firebirdsql.jdbc.FBDriver");
            String local = "jdbc:firebirdsql:localhost/3050:C:/Users/Caio/Dropbox/NetbeansProject/Transportes e Mecânica Guthner LTDA/Banco de dados/SYSTEM.FDB";
            String usuario = "SYSDBA";
            String senha = "masterkey";
            con = DriverManager.getConnection(local, usuario, senha);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Não foi possivel criar uma conexão com o banco de dados", "Erro", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        new Principal(daoFactory, con);
    }
}
