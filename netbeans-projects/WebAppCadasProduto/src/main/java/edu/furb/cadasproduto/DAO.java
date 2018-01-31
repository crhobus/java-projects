package edu.furb.cadasproduto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAO {

    private Connection conn;

    public void conectar() throws SQLException, ClassNotFoundException {
        Class.forName("oracle.jdbc.OracleDriver");
        conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "sysuser", "keyuser");
    }

    public void desconectar() throws SQLException {
        conn.close();
    }

    public void inserir(Produto produto) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("insert into produto (id, nome, unidade_medida, preco) values (produto_seq.nextval, :nome, :unidade_medida, :preco)");
            ps.setString(1, produto.getNome());
            ps.setString(2, produto.getUnidadeMedida());
            ps.setBigDecimal(3, produto.getPreco());
            ps.executeUpdate();
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    public void alterar(Produto produto) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("update produto set nome = :nome, unidade_medida = :unidade_medida, preco = :preco where id = :id");
            ps.setString(1, produto.getNome());
            ps.setString(2, produto.getUnidadeMedida());
            ps.setBigDecimal(3, produto.getPreco());
            ps.setLong(4, produto.getId());
            ps.executeUpdate();
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    public void excluir(long id) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("delete from produto where id = :id");
            ps.setLong(1, id);
            ps.executeUpdate();
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    public List<Produto> listar() throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("select * from produto");
            ResultSet rs = ps.executeQuery();

            List<Produto> produtos = new ArrayList<>();
            Produto produto;
            while (rs.next()) {
                produto = new Produto();
                produto.setId(rs.getLong("id"));
                produto.setNome(rs.getString("nome"));
                produto.setUnidadeMedida(rs.getString("unidade_medida"));
                produto.setPreco(rs.getBigDecimal("preco"));
                produtos.add(produto);
            }
            return produtos;
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    public Produto getProduto(long id) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("select * from produto where id = :id");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Produto(rs.getLong("id"),
                        rs.getString("nome"),
                        rs.getString("unidade_medida"),
                        rs.getBigDecimal("preco"));
            }
            return null;
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }
}
