package br.com.rest.mars.model;

import java.util.Objects;

/**
 *
 * @author crhobus
 */
public class Posicao {

    private int x;
    private int y;
    private String orientacao;//NORTH, EAST, WEST, SOUTH

    public Posicao() {
        this.x = 0;
        this.y = 0;
        this.orientacao = "N";
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getOrientacao() {
        return orientacao;
    }

    public void setOrientacao(String orientacao) {
        this.orientacao = orientacao;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.x;
        hash = 29 * hash + this.y;
        hash = 29 * hash + Objects.hashCode(this.orientacao);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Posicao other = (Posicao) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        if (!Objects.equals(this.orientacao, other.orientacao)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ", " + this.orientacao + ")";
    }
}
