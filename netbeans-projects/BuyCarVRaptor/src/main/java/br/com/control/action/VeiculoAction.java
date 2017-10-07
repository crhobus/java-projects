package br.com.control.action;

import br.com.model.beans.Veiculo;
import br.com.model.dao.VeiculoDAO;
import br.com.model.dao.util.DAOAdapter;
import br.com.model.dao.util.Operacao;
import java.util.List;
import javax.enterprise.context.RequestScoped;

@RequestScoped
public class VeiculoAction {

    private final DAOAdapter<Veiculo> veiculoDAO;

    public VeiculoAction() {
        veiculoDAO = new VeiculoDAO();
    }

    public Veiculo addVeiculo(Veiculo veiculo) throws Exception {
        try {
            veiculoDAO.init();
            if (veiculo.getNrSequencia() == null) {
                return veiculoDAO.insert(veiculo);
            } else {
                return veiculoDAO.update(veiculo);
            }
        } finally {
            veiculoDAO.destroy();
        }
    }

    public void removeVeiculo(long nrSequencia) throws Exception {
        try {
            veiculoDAO.init();
            veiculoDAO.delete(nrSequencia);
        } finally {
            veiculoDAO.destroy();
        }
    }

    public Veiculo getVeiculo(long nrSequencia) throws Exception {
        try {
            veiculoDAO.init();
            return veiculoDAO.get(nrSequencia);
        } finally {
            veiculoDAO.destroy();
        }
    }

    public List<Veiculo> getVeiculos() throws Exception {
        try {
            veiculoDAO.init();
            return veiculoDAO.list(Operacao.LISTAR);
        } finally {
            veiculoDAO.destroy();
        }
    }
}
