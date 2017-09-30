package AssistenciaTecRandom;

import java.io.*;
import java.util.*;
import javax.swing.*;

public class ArquivoBinario {

    private RandomAccessFile arquivo;

    public ArquivoBinario() {
        try {
            arquivo = new RandomAccessFile("Assistencia.dat", "rw");
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao abrir o arquivo", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void gravar(Assistencia assistencia) {
        try {
            arquivo.seek(arquivo.length());
            arquivo.writeInt(assistencia.getCodigo());
            arquivo.writeInt(assistencia.getCodClie());
            arquivo.write(Arrays.copyOf(assistencia.getAtendente().getBytes(), 40));
            arquivo.write(Arrays.copyOf(assistencia.getTipoServico().getBytes(), 27));
            arquivo.write(Arrays.copyOf(assistencia.getEquipamento().getBytes(), 25));
            arquivo.write(Arrays.copyOf(assistencia.getMarca().getBytes(), 25));
            arquivo.write(Arrays.copyOf(assistencia.getModelo().getBytes(), 25));
            arquivo.write(Arrays.copyOf(assistencia.getSetor().getBytes(), 15));
            arquivo.write(Arrays.copyOf(assistencia.getDescricao().getBytes(), 250));
            arquivo.write(Arrays.copyOf(assistencia.getAnexo().getBytes(), 250));
            arquivo.write(Arrays.copyOf(assistencia.getServicoRealizado().getBytes(), 250));
            arquivo.write(Arrays.copyOf(assistencia.getIncluidoPor().getBytes(), 40));
            arquivo.write(Arrays.copyOf(assistencia.getFormaPagto().getBytes(), 10));
            arquivo.write(Arrays.copyOf(assistencia.getCondPagto().getBytes(), 2));
            arquivo.write(Arrays.copyOf(assistencia.getSituacao().getBytes(), 15));
            arquivo.write(Arrays.copyOf(assistencia.getSalvar().getBytes(), 1));
            arquivo.writeBoolean(assistencia.isOrcamento());
            arquivo.writeDouble(assistencia.getSubTotal());
            arquivo.writeDouble(assistencia.getDescontos());
            arquivo.writeDouble(assistencia.getTotal());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao escrever no arquivo", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void regravar(Assistencia assistencia, int posicao) {
        try {
            arquivo.seek((posicao - 1) * 1008);
            arquivo.writeInt(assistencia.getCodigo());
            arquivo.writeInt(assistencia.getCodClie());
            arquivo.write(Arrays.copyOf(assistencia.getAtendente().getBytes(), 40));
            arquivo.write(Arrays.copyOf(assistencia.getTipoServico().getBytes(), 27));
            arquivo.write(Arrays.copyOf(assistencia.getEquipamento().getBytes(), 25));
            arquivo.write(Arrays.copyOf(assistencia.getMarca().getBytes(), 25));
            arquivo.write(Arrays.copyOf(assistencia.getModelo().getBytes(), 25));
            arquivo.write(Arrays.copyOf(assistencia.getSetor().getBytes(), 15));
            arquivo.write(Arrays.copyOf(assistencia.getDescricao().getBytes(), 250));
            arquivo.write(Arrays.copyOf(assistencia.getAnexo().getBytes(), 250));
            arquivo.write(Arrays.copyOf(assistencia.getServicoRealizado().getBytes(), 250));
            arquivo.write(Arrays.copyOf(assistencia.getIncluidoPor().getBytes(), 40));
            arquivo.write(Arrays.copyOf(assistencia.getFormaPagto().getBytes(), 10));
            arquivo.write(Arrays.copyOf(assistencia.getCondPagto().getBytes(), 2));
            arquivo.write(Arrays.copyOf(assistencia.getSituacao().getBytes(), 15));
            arquivo.write(Arrays.copyOf(assistencia.getSalvar().getBytes(), 1));
            arquivo.writeBoolean(assistencia.isOrcamento());
            arquivo.writeDouble(assistencia.getSubTotal());
            arquivo.writeDouble(assistencia.getDescontos());
            arquivo.writeDouble(assistencia.getTotal());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao escrever no arquivo", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public int qtdade() {
        try {
            return (int) (arquivo.length() / 1008);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro na leitura do arquivo", "Erro", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
    }

    private Assistencia ler(int posicao) {
        Assistencia assistencia = new Assistencia();
        try {
            arquivo.seek(posicao * 1008);
            if (arquivo.length() != 0) {
                assistencia.setCodigo(arquivo.readInt());
                assistencia.setCodClie(arquivo.readInt());
                byte[] atendente = new byte[40];
                arquivo.read(atendente);
                int tamAte = 40;
                for (int i = 0; i < atendente.length; i++) {
                    if (atendente[i] == 0) {
                        tamAte = i;
                        break;
                    }
                }
                assistencia.setAtendente(new String(Arrays.copyOf(atendente, tamAte)));
                byte[] tipoSer = new byte[27];
                arquivo.read(tipoSer);
                int tamTipoSer = 27;
                for (int i = 0; i < tipoSer.length; i++) {
                    if (tipoSer[i] == 0) {
                        tamTipoSer = i;
                        break;
                    }
                }
                assistencia.setTipoServico(new String(Arrays.copyOf(tipoSer, tamTipoSer)));
                byte[] equip = new byte[25];
                arquivo.read(equip);
                int tamEquip = 25;
                for (int i = 0; i < equip.length; i++) {
                    if (equip[i] == 0) {
                        tamEquip = i;
                        break;
                    }
                }
                assistencia.setEquipamento(new String(Arrays.copyOf(equip, tamEquip)));
                byte[] marca = new byte[25];
                arquivo.read(marca);
                int tamMarca = 25;
                for (int i = 0; i < marca.length; i++) {
                    if (marca[i] == 0) {
                        tamMarca = i;
                        break;
                    }
                }
                assistencia.setMarca(new String(Arrays.copyOf(marca, tamMarca)));
                byte[] modelo = new byte[25];
                arquivo.read(modelo);
                int tamModelo = 25;
                for (int i = 0; i < modelo.length; i++) {
                    if (modelo[i] == 0) {
                        tamModelo = i;
                        break;
                    }
                }
                assistencia.setModelo(new String(Arrays.copyOf(modelo, tamModelo)));
                byte[] setor = new byte[15];
                arquivo.read(setor);
                int tamSetor = 15;
                for (int i = 0; i < setor.length; i++) {
                    if (setor[i] == 0) {
                        tamSetor = i;
                        break;
                    }
                }
                assistencia.setSetor(new String(Arrays.copyOf(setor, tamSetor)));
                byte[] descricao = new byte[250];
                arquivo.read(descricao);
                int tamDescricao = 250;
                for (int i = 0; i < descricao.length; i++) {
                    if (descricao[i] == 0) {
                        tamDescricao = i;
                        break;
                    }
                }
                assistencia.setDescricao(new String(Arrays.copyOf(descricao, tamDescricao)));
                byte[] anexo = new byte[250];
                arquivo.read(anexo);
                int tamAnexo = 250;
                for (int i = 0; i < anexo.length; i++) {
                    if (anexo[i] == 0) {
                        tamAnexo = i;
                        break;
                    }
                }
                assistencia.setAnexo(new String(Arrays.copyOf(anexo, tamAnexo)));
                byte[] serReal = new byte[250];
                arquivo.read(serReal);
                int tamSerReal = 250;
                for (int i = 0; i < serReal.length; i++) {
                    if (serReal[i] == 0) {
                        tamSerReal = i;
                        break;
                    }
                }
                assistencia.setServicoRealizado(new String(Arrays.copyOf(serReal, tamSerReal)));
                byte[] incluPor = new byte[40];
                arquivo.read(incluPor);
                int tamIncluPor = 40;
                for (int i = 0; i < incluPor.length; i++) {
                    if (incluPor[i] == 0) {
                        tamIncluPor = i;
                        break;
                    }
                }
                assistencia.setIncluidoPor(new String(Arrays.copyOf(incluPor, tamIncluPor)));
                byte[] formaPagto = new byte[10];
                arquivo.read(formaPagto);
                int tamFormaPagto = 10;
                for (int i = 0; i < formaPagto.length; i++) {
                    if (formaPagto[i] == 0) {
                        tamFormaPagto = i;
                        break;
                    }
                }
                assistencia.setFormaPagto(new String(Arrays.copyOf(formaPagto, tamFormaPagto)));
                byte[] condPagto = new byte[2];
                arquivo.read(condPagto);
                int tamCondPagto = 2;
                for (int i = 0; i < condPagto.length; i++) {
                    if (condPagto[i] == 0) {
                        tamCondPagto = i;
                        break;
                    }
                }
                assistencia.setCondPagto(new String(Arrays.copyOf(condPagto, tamCondPagto)));
                byte[] situacao = new byte[15];
                arquivo.read(situacao);
                int tamSituacao = 15;
                for (int i = 0; i < situacao.length; i++) {
                    if (situacao[i] == 0) {
                        tamSituacao = i;
                        break;
                    }
                }
                assistencia.setSituacao(new String(Arrays.copyOf(situacao, tamSituacao)));
                byte[] salvar = new byte[1];
                arquivo.read(salvar);
                int tamSavar = 15;
                for (int i = 0; i < salvar.length; i++) {
                    if (salvar[i] == 0) {
                        tamSavar = i;
                        break;
                    }
                }
                assistencia.setSalvar(new String(Arrays.copyOf(salvar, tamSavar)));
                assistencia.setOrcamento(arquivo.readBoolean());
                assistencia.setSubTotal(arquivo.readDouble());
                assistencia.setDescontos(arquivo.readDouble());
                assistencia.setTotal(arquivo.readDouble());
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro na leitura do arquivo", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return assistencia;
    }

    public List<Assistencia> listarTodos() {
        List<Assistencia> lista = new ArrayList<Assistencia>();
        Assistencia assistencia;
        int i = 0;
        while (i < qtdade()) {
            assistencia = ler(i);
            lista.add(assistencia);
            i++;
        }
        return lista;
    }

    public void fecharArquivo() {
        try {
            arquivo.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao fechar o arquivo", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
