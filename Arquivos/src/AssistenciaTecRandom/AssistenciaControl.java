package AssistenciaTecRandom;

import java.util.*;

public class AssistenciaControl {

    private ArquivoBinario arquivoBinario;
    private ClienteControl controlClie;
    private List<Assistencia> lista = new ArrayList<Assistencia>();

    public AssistenciaControl(ClienteControl controlClie) {
        this.controlClie = controlClie;
        arquivoBinario = new ArquivoBinario();
        lista = arquivoBinario.listarTodos();
    }

    public void adicionar(int codClie, String atendente, String tipoServico, String equipamento,
            String marca, String modelo, String setor, String descricao, String anexo) {
        Assistencia assistencia = new Assistencia();
        assistencia.setCodigo(arquivoBinario.qtdade() + 1);
        assistencia.setCodClie(codClie);
        assistencia.setAtendente(atendente);
        assistencia.setTipoServico(tipoServico);
        assistencia.setEquipamento(equipamento);
        assistencia.setMarca(marca);
        assistencia.setModelo(modelo);
        assistencia.setSetor(setor);
        assistencia.setDescricao(descricao);
        assistencia.setAnexo(anexo);
        assistencia.setServicoRealizado("");
        assistencia.setIncluidoPor("");
        assistencia.setFormaPagto("Dinheiro");
        assistencia.setCondPagto("1X");
        assistencia.setSituacao("Em Aberto");
        assistencia.setSalvar("");
        assistencia.setOrcamento(false);
        assistencia.setSubTotal(0.0);
        assistencia.setDescontos(0.0);
        assistencia.setTotal(0.0);
        lista.add(assistencia);
        arquivoBinario.gravar(assistencia);
    }

    public int tamanho() {
        return arquivoBinario.qtdade();
    }

    public Object conteudoLinha(int linha, int coluna) {
        Assistencia cadas = lista.get(linha);
        switch (coluna) {
            case 0:
                return cadas.getCodigo();
            case 1:
                return cadas.getCodClie();
            case 2:
                if (controlClie.clienteCadasNome(cadas.getCodClie()) != null) {
                    return controlClie.getNome();
                } else {
                    return "Cliente não encontrado";
                }
            case 3:
                return cadas.getAtendente();
            case 4:
                return cadas.getTipoServico();
            case 5:
                return cadas.getEquipamento();
            case 6:
                return cadas.getMarca();
            case 7:
                return cadas.getModelo();
            case 8:
                return cadas.getSetor();
            case 9:
                return cadas.getDescricao();
            case 10:
                return cadas.getAnexo();
            case 11:
                return cadas.isOrcamento();
            case 12:
                return cadas.getServicoRealizado();
            case 13:
                return cadas.getIncluidoPor();
            case 14:
                return cadas.getSubTotal();
            case 15:
                return cadas.getDescontos();
            case 16:
                return cadas.getTotal();
            case 17:
                return cadas.getCondPagto();
            case 18:
                return cadas.getFormaPagto();
            case 19:
                return cadas.getSituacao();
            default:
                return cadas.getSalvar();
        }
    }

    public void alterarCelula(Object valor, int linha, int coluna) {
        Assistencia cadas = lista.get(linha);
        Calculo calculo = new Calculo();
        try {
            switch (coluna) {
                case 11:
                    cadas.setOrcamento((Boolean) valor);
                    break;
                case 12:
                    cadas.setServicoRealizado((String) valor);
                    break;
                case 13:
                    cadas.setIncluidoPor((String) valor);
                    break;
                case 14:
                    cadas.setSubTotal((Double) valor);
                    cadas.setTotal(calculo.Calculo(cadas.getSubTotal(), cadas.getDescontos()));
                    break;
                case 15:
                    cadas.setDescontos((Double) valor);
                    cadas.setTotal(calculo.Calculo(cadas.getSubTotal(), cadas.getDescontos()));
                    break;
                case 16:
                    cadas.setTotal((Double) valor);
                    break;
                case 17:
                    cadas.setCondPagto((String) valor);
                    break;
                case 18:
                    cadas.setFormaPagto((String) valor);
                    break;
                case 19:
                    cadas.setSituacao((String) valor);
                    break;
                case 20:
                    arquivoBinario.regravar(cadas, linha + 1);
                    break;
            }
        } catch (Exception ex) {
        }
    }

    public void fecharArquivo() {
        arquivoBinario.fecharArquivo();
    }
}
