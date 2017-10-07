package br.com.model.dao;

import br.com.model.beans.Cliente;
import br.com.model.dao.util.DAO;
import br.com.model.dao.util.DAOAdapter;
import br.com.model.dao.util.Operacao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO extends DAOAdapter<Cliente> {

    private final DAO dao = new DAO();

    @Override
    public void init() throws SQLException, ClassNotFoundException {
        dao.conectar();
    }

    @Override
    public Cliente insert(Cliente cliente) throws SQLException {
        cliente.setNrSequencia(dao.getSequence("cliente_seq.nextval"));
        PreparedStatement ps = null;
        try {
            StringBuilder insert = new StringBuilder();
            insert.append(" insert into cliente ( ");
            insert.append("                     nr_sequencia, ");
            insert.append("                     tipo_pessoa, ");
            insert.append("                     cpf, ");
            insert.append("                     cnpj, ");
            insert.append("                     nome, ");
            insert.append("                     telefone, ");
            insert.append("                     email, ");
            insert.append("                     endereco, ");
            insert.append("                     bairro, ");
            insert.append("                     numero, ");
            insert.append("                     cep, ");
            insert.append("                     cidade, ");
            insert.append("                     estado ");
            insert.append("            ) values ( ");
            insert.append("                     :nr_sequencia, ");
            insert.append("                     :tipo_pessoa, ");
            insert.append("                     :cpf, ");
            insert.append("                     :cnpj, ");
            insert.append("                     :nome, ");
            insert.append("                     :telefone, ");
            insert.append("                     :email, ");
            insert.append("                     :endereco, ");
            insert.append("                     :bairro, ");
            insert.append("                     :numero, ");
            insert.append("                     :cep, ");
            insert.append("                     :cidade, ");
            insert.append("                     :estado) ");

            ps = dao.getPreparedStatement(insert.toString());

            ps.setLong(1, cliente.getNrSequencia());
            ps.setString(2, cliente.getTipoPessoa());
            ps.setString(3, cliente.getCpf());
            ps.setString(4, cliente.getCnpj());
            ps.setString(5, cliente.getNome());
            ps.setString(6, cliente.getTelefone());
            ps.setString(7, cliente.getEmail());
            ps.setString(8, cliente.getEndereco());
            ps.setString(9, cliente.getBairro());
            ps.setInt(10, cliente.getNumero());
            ps.setString(11, cliente.getCep());
            ps.setString(12, cliente.getCidade());
            ps.setString(13, cliente.getEstado());

            ps.executeUpdate();

            return cliente;
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    @Override
    public Cliente update(Cliente cliente) throws SQLException {
        PreparedStatement ps = null;
        try {
            StringBuilder update = new StringBuilder();
            update.append(" update cliente ");
            update.append(" set    tipo_pessoa  = :tipo_pessoa, ");
            update.append("        cpf          = :cpf, ");
            update.append("        cnpj         = :cnpj, ");
            update.append("        nome         = :nome, ");
            update.append("        telefone     = :telefone, ");
            update.append("        email        = :email, ");
            update.append("        endereco     = :endereco, ");
            update.append("        bairro       = :bairro, ");
            update.append("        numero       = :numero, ");
            update.append("        cep          = :cep, ");
            update.append("        cidade       = :cidade, ");
            update.append("        estado       = :estado ");
            update.append(" where  nr_sequencia = :nr_sequencia ");

            ps = dao.getPreparedStatement(update.toString());

            ps.setString(1, cliente.getTipoPessoa());
            ps.setString(2, cliente.getCpf());
            ps.setString(3, cliente.getCnpj());
            ps.setString(4, cliente.getNome());
            ps.setString(5, cliente.getTelefone());
            ps.setString(6, cliente.getEmail());
            ps.setString(7, cliente.getEndereco());
            ps.setString(8, cliente.getBairro());
            ps.setInt(9, cliente.getNumero());
            ps.setString(10, cliente.getCep());
            ps.setString(11, cliente.getCidade());
            ps.setString(12, cliente.getEstado());
            ps.setLong(13, cliente.getNrSequencia());

            ps.executeUpdate();

            return cliente;
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    @Override
    public void delete(long nrSequencia) throws SQLException {
        PreparedStatement ps = null;
        try {
            StringBuilder delete = new StringBuilder();
            delete.append(" delete ");
            delete.append(" from    cliente ");
            delete.append(" where   nr_sequencia = :nr_sequencia ");

            ps = dao.getPreparedStatement(delete.toString());

            ps.setLong(1, nrSequencia);

            ps.executeUpdate();
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    @Override
    public Cliente get(long nrSequencia) throws SQLException {
        PreparedStatement ps = null;
        try {
            StringBuilder select = new StringBuilder();
            select.append(" select  * ");
            select.append(" from    cliente ");
            select.append(" where   nr_sequencia = :nr_sequencia ");

            ps = dao.getPreparedStatement(select.toString());
            ps.setLong(1, nrSequencia);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setNrSequencia(rs.getLong("NR_SEQUENCIA"));
                cliente.setTipoPessoa(rs.getString("TIPO_PESSOA"));
                cliente.setCpf(rs.getString("CPF"));
                cliente.setCnpj(rs.getString("CNPJ"));
                cliente.setNome(rs.getString("NOME"));
                cliente.setTelefone(rs.getString("TELEFONE"));
                cliente.setEmail(rs.getString("EMAIL"));
                cliente.setEndereco(rs.getString("ENDERECO"));
                cliente.setBairro(rs.getString("BAIRRO"));
                cliente.setNumero(rs.getInt("NUMERO"));
                cliente.setCep(rs.getString("CEP"));
                cliente.setCidade(rs.getString("CIDADE"));
                cliente.setEstado(rs.getString("ESTADO"));
                return cliente;
            }
            return null;
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    @Override
    public List<Cliente> list(Operacao operacao) throws SQLException {
        switch (operacao) {
            case LISTAR:
                return listAll();
            default:
                return null;
        }
    }

    private List<Cliente> listAll() throws SQLException {
        PreparedStatement ps = null;
        try {
            StringBuilder select = new StringBuilder();
            select.append(" select   * ");
            select.append(" from     cliente ");
            select.append(" order by nome ");

            ps = dao.getPreparedStatement(select.toString());
            ResultSet rs = ps.executeQuery();

            List<Cliente> clientes = new ArrayList<>();
            Cliente cliente;
            while (rs.next()) {
                cliente = new Cliente();
                cliente.setNrSequencia(rs.getLong("NR_SEQUENCIA"));
                cliente.setTipoPessoa(rs.getString("TIPO_PESSOA"));
                cliente.setCpf(rs.getString("CPF"));
                cliente.setCnpj(rs.getString("CNPJ"));
                cliente.setNome(rs.getString("NOME"));
                cliente.setTelefone(rs.getString("TELEFONE"));
                cliente.setEmail(rs.getString("EMAIL"));
                cliente.setEndereco(rs.getString("ENDERECO"));
                cliente.setBairro(rs.getString("BAIRRO"));
                cliente.setNumero(rs.getInt("NUMERO"));
                cliente.setCep(rs.getString("CEP"));
                cliente.setCidade(rs.getString("CIDADE"));
                cliente.setEstado(rs.getString("ESTADO"));
                clientes.add(cliente);
            }
            return clientes;
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    @Override
    public void destroy() throws SQLException {
        dao.desconectar();
    }
}
