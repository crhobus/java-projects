package br.com.rest.mars.service;

import br.com.rest.mars.model.Posicao;

/**
 *
 * @author crhobus
 */
public class MarsService {

    private final String[][] terreno = new String[5][5];

    public Posicao movimentarRobo(String comandos) throws Exception {
        Posicao pos = new Posicao();
        if (comandos == null
                || "".equals(comandos)) {//Caso não informou um comando o robô fica na posição inicial
            return pos;
        }
        char[] cmds = comandos.toCharArray();
        for (char c : cmds) {
            if (c != 'L'
                    && c != 'R'
                    && c != 'M') {
                throw new Exception("Entrada inválida");
            }
        }
        terreno[pos.getX()][pos.getY()] = pos.getOrientacao();
        return andar(pos, cmds);
    }

    private Posicao andar(Posicao pos, char[] cmds) throws Exception {
        int posXAux;
        int posYAux;
        for (char cmd : cmds) {
            posXAux = pos.getX();
            posYAux = pos.getY();
            if (cmd == 'L') {
                pos.setOrientacao(obterProxOrientacao(pos.getOrientacao(), false));
            }
            if (cmd == 'R') {
                pos.setOrientacao(obterProxOrientacao(pos.getOrientacao(), true));
            }
            if (cmd == 'M') {
                pos = obterPosicaoDeslocar(pos);
            }
            if (pos.getX() > 4
                    || pos.getX() < 0
                    || pos.getY() > 4
                    || pos.getY() < 0) {
                throw new Exception("Posição inválida");
            } else {
                terreno[posXAux][posYAux] = null;
                terreno[pos.getX()][pos.getY()] = pos.getOrientacao();
            }
        }
        return pos;
    }

    private Posicao obterPosicaoDeslocar(Posicao pos) {
        switch (pos.getOrientacao()) {
            case "N"://NORTH
                pos.setY(pos.getY() + 1);
                break;
            case "E"://EAST
                pos.setX(pos.getX() + 1);
                break;
            case "W"://WEST
                pos.setX(pos.getX() - 1);
                break;
            case "S"://SOUTH
                pos.setY(pos.getY() - 1);
                break;
        }
        return pos;
    }

    private String obterProxOrientacao(String orientacaoAtual, boolean sentidoHorario) {
        String orientacoes;
        if (sentidoHorario) {//Verifica se true irá girar 90 graus para direita
            if ("W".equalsIgnoreCase(orientacaoAtual)) {
                return "N";
            }
            orientacoes = "NESW";
        } else {//caso sentidoHorario for false irá girar 90 graus para esquerda
            if ("E".equalsIgnoreCase(orientacaoAtual)) {
                return "N";
            }
            orientacoes = "NWSE";
        }
        return orientacoes.substring(orientacoes.indexOf(orientacaoAtual) + 1, orientacoes.indexOf(orientacaoAtual) + 2);
    }
}
