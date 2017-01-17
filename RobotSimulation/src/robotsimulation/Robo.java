/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robotsimulation;

/**
 *
 * @author david
 */
public class Robo {
    public enum direcao{
        CIMA,BAIXO,DIREITA,ESQUERDA,NENHUM,DESLIGADO
    }
    
    private int x;
    private int y;

    /**
     *
     */
    public direcao direct = direcao.NENHUM;

    public direcao anterior = direcao.NENHUM;
    
    
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

    public direcao getDirect() {
        return direct;
    }

    public void setDirect(direcao direcao) {
        this.direct = direcao;
    }

    public direcao getAnterior() {
        return anterior;
    }

    public void setAnterior(direcao anterior) {
        this.anterior = anterior;
    }
    
}
