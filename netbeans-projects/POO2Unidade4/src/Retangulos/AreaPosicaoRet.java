package Retangulos;

public class AreaPosicaoRet implements Comparable<AreaPosicaoRet> {

    private Integer posicao, area;

    public int getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Integer getPosicao() {
        return posicao;
    }

    public void setPosicao(Integer posicao) {
        this.posicao = posicao;
    }

    public int compareTo(AreaPosicaoRet areaPosicaoRet) {
        return area.compareTo(areaPosicaoRet.getArea());
    }
}
