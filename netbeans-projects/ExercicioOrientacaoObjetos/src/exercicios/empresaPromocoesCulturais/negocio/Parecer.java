package exercicios.empresaPromocoesCulturais.negocio;

import java.util.Date;

/**
 *
 * @author CaioRenan
 */
public class Parecer {

    private String nomeParecerista;
    private Date data;
    private String conteudo;

    public Parecer(String nomeParecerista, Date data, String conteudo) {
        setNomeParecerista(nomeParecerista);
        setData(data);
        setConteudo(conteudo);
    }

    public String getNomeParecerista() {
        return nomeParecerista;
    }

    public void setNomeParecerista(String nomeParecerista) {
        if (nomeParecerista != null
                && !nomeParecerista.isEmpty()) {
            this.nomeParecerista = nomeParecerista;
        }
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        if (data != null) {
            this.data = data;
        }
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        if (conteudo != null
                && !conteudo.isEmpty()) {
            this.conteudo = conteudo;
        }
    }
}
