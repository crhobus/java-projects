package ArquivoBinario;

public class RetanguloBinario implements Comparable<RetanguloBinario> {

    private Integer area, posicao;

    public Integer getArea() {
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

    public int compareTo(RetanguloBinario retanguloBinario) {
        return area.compareTo(retanguloBinario.getArea());
    }
}
