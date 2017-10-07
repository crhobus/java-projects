package br.com.control.action;

import br.com.model.beans.Opcional;
import br.com.model.dao.OpcionalDAO;
import br.com.model.dao.util.DAOAdapter;
import br.com.model.dao.util.Operacao;
import java.util.List;
import javax.enterprise.context.RequestScoped;

@RequestScoped
public class OpcionalAction {

    private final DAOAdapter<Opcional> opcionalDAO;

    public OpcionalAction() {
        opcionalDAO = new OpcionalDAO();
    }

    public Opcional addOpcional(Opcional opcional) throws Exception {
        try {
            opcionalDAO.init();
            if (opcional.getNrSequencia() == null) {
                return opcionalDAO.insert(opcional);
            } else {
                return opcionalDAO.update(opcional);
            }
        } finally {
            opcionalDAO.destroy();
        }
    }

    public void removeOpcional(long nrSequencia) throws Exception {
        try {
            opcionalDAO.init();
            opcionalDAO.delete(nrSequencia);
        } finally {
            opcionalDAO.destroy();
        }
    }

    public Opcional getOpcional(long nrSequencia) throws Exception {
        try {
            opcionalDAO.init();
            return opcionalDAO.get(nrSequencia);
        } finally {
            opcionalDAO.destroy();
        }
    }

    public List<Opcional> getOpcionais() throws Exception {
        try {
            opcionalDAO.init();
            return opcionalDAO.list(Operacao.LISTAR);
        } finally {
            opcionalDAO.destroy();
        }
    }
}
