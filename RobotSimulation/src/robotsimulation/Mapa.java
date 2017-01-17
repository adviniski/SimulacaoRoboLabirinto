/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robotsimulation;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author david
 */
public class Mapa  extends JPanel 
        implements ActionListener{
    
    private final char[][] matriz;
    private final Image parede;
    private final Image chao;
    private final Image entrada;
    private final Image saida;
    private final Image robotD;
    private final Image robotE;
    private final Image robotC;
    private final Image robotB;
    Coordenada robo;
    private final int LINHAS;
    private final int COLUNAS;
    Coordenada descricao;
    private final Coordenada[][] coordenadas;
    private final int DELAY = 60;
    private final Timer timer;
    Robo ObjectRobo = new Robo();
    
    
    public Mapa(char gera[][], int width, int height){
        
        
        matriz = gera;
        
        this.LINHAS = width;
        this.COLUNAS = height;
        System.out.println(LINHAS);

        descricao = new Coordenada(20,20);
        
        coordenadas = new Coordenada[LINHAS][COLUNAS];
        
        ImageIcon referencia1 = new ImageIcon("src//img//0.png");
        chao = referencia1.getImage();
        
        ImageIcon referencia2 = new ImageIcon("src//img//1.png");
        parede = referencia2.getImage();
        
        ImageIcon referencia3 = new ImageIcon("src//img//2.png");
        entrada = referencia3.getImage();
        
        ImageIcon referencia4 = new ImageIcon("src//img//3.png");
        saida = referencia4.getImage();
        
        ImageIcon referencia5 = new ImageIcon("src//img//roboD.png");
        robotD = referencia5.getImage();
        
        ImageIcon referencia6 = new ImageIcon("src//img//roboE.png");
        robotE = referencia6.getImage();
        
        ImageIcon referencia7 = new ImageIcon("src//img//roboC.png");
        robotC = referencia7.getImage();
        
        ImageIcon referencia8 = new ImageIcon("src//img//roboB.png");
        robotB = referencia8.getImage();
        
        int tamanho = saida.getHeight(this);
        
        for(int x = 0; x < this.LINHAS; x++){
            for(int y = 0; y < this.COLUNAS; y++){
                Coordenada coord = new Coordenada(y * tamanho,x * tamanho);
                coordenadas[x][y] = coord;
            }
        }
        for(int x = 0; x < this.LINHAS; x++){
            for(int y = 0; y < this.COLUNAS; y++){
                if(matriz[x][y] == '2'){
                    robo = coordenadas[x][y];
                    ObjectRobo.setX(x);
                    ObjectRobo.setY(y);
                    if(y == 0){
                        ObjectRobo.setDirect(Robo.direcao.DIREITA);
                    } else if(x == 0){
                        ObjectRobo.setDirect(Robo.direcao.BAIXO);
                    } else if(y == COLUNAS-1){
                        ObjectRobo.setDirect(Robo.direcao.ESQUERDA);
                    } else if(x == LINHAS-1){
                        ObjectRobo.setDirect(Robo.direcao.CIMA);
                    }
                }
            }
        }
        timer = new Timer(DELAY, this);
        timer.start();
    }
    
    @Override
    public void paint(Graphics g) {
		Graphics2D graficos = (Graphics2D) g;
                
                for(int i=0; i<LINHAS;i++){
                    for(int j=0; j<COLUNAS;j++){
                        Coordenada posicao = coordenadas[i][j];
                        if(matriz[i][j]== '1'){
                            graficos.drawImage(parede, posicao.getX(), posicao.getY(), null);
                        }else
                        if(matriz[i][j]== '0'){
                            graficos.drawImage(chao, posicao.getX(), posicao.getY(), null);
                        }else
                        if(matriz[i][j]== '2'){
                            graficos.drawImage(entrada, posicao.getX(), posicao.getY(), null);
                        }else
                        if(matriz[i][j]=='3'){
                            graficos.drawImage(saida, posicao.getX(), posicao.getY(), null);
                        }
                    }
                }
                if(ObjectRobo.getDirect() == Robo.direcao.DIREITA){
                    graficos.drawImage(robotD, robo.getX(), robo.getY(), null);
                }else if(ObjectRobo.getDirect() == Robo.direcao.ESQUERDA){
                    graficos.drawImage(robotE, robo.getX(), robo.getY(), null);
                }else if(ObjectRobo.getDirect() == Robo.direcao.CIMA){
                    graficos.drawImage(robotC, robo.getX(), robo.getY(), null);
                }else if(ObjectRobo.getDirect() == Robo.direcao.BAIXO){
                    graficos.drawImage(robotB, robo.getX(), robo.getY(), null);
                }
                graficos.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 18));
                graficos.drawString(ObjectRobo.getDirect().toString(), descricao.getX(), descricao.getY());
                repaint();
                revalidate();
                g.dispose();
    }   
        @Override
    public void actionPerformed(ActionEvent e) {
        int x = ObjectRobo.getX();
        int y = ObjectRobo.getY();
        if(ObjectRobo.getDirect() == Robo.direcao.DIREITA){
            if(matriz[x+1][y] == '3'){
                ObjectRobo.setDirect(Robo.direcao.DESLIGADO);
            }else
            if(matriz[x+1][y] == '1' || matriz[x+1][y] == '2'){
                if(matriz[x][y+1] == '0'){
                    ObjectRobo.setY(ObjectRobo.getY()+1);
                    robo = coordenadas[x][y+1];
                }else if(matriz[x][y+1] == '3'){
                    ObjectRobo.setDirect(Robo.direcao.DESLIGADO);
                } 
                else if(matriz[x][y+1] == '1' && matriz[x-1][y] == '0'){
                    ObjectRobo.setDirect(Robo.direcao.CIMA);
                    ObjectRobo.setX(ObjectRobo.getX()-1);
                    robo = coordenadas[x-1][y];
                }else if(matriz[x][y+1] == '1' && matriz[x-1][y] == '1'){
                    ObjectRobo.setDirect(Robo.direcao.ESQUERDA);
                    ObjectRobo.setY(ObjectRobo.getY()-1);
                    robo = coordenadas[x][y-1];
                }
            }else if(matriz[x+1][y] == '0'){
                ObjectRobo.setDirect(Robo.direcao.BAIXO);
                ObjectRobo.setX(ObjectRobo.getX()+1);
                robo = coordenadas[x+1][y];
            }
        }else if(ObjectRobo.getDirect() == Robo.direcao.ESQUERDA){
            if(matriz[x-1][y] == '3'){
                ObjectRobo.setDirect(Robo.direcao.DESLIGADO);
            }else
            if(matriz[x-1][y] == '1' || matriz[x-1][y] == '2'){
                if(matriz[x][y-1] == '0'){
                    ObjectRobo.setY(ObjectRobo.getY()-1);
                    robo = coordenadas[x][y-1];
                }else if(matriz[x][y-1] == '3'){
                    ObjectRobo.setDirect(Robo.direcao.DESLIGADO);
                }
                else if(matriz[x][y-1] == '1' && matriz[x+1][y] == '0'){
                    ObjectRobo.setDirect(Robo.direcao.BAIXO);
                    ObjectRobo.setX(ObjectRobo.getX()+1);
                    robo = coordenadas[x+1][y];
                }else if(matriz[x][y-1] == '1' && matriz[x+1][y] == '1'){
                    ObjectRobo.setDirect(Robo.direcao.DIREITA);
                    ObjectRobo.setY(ObjectRobo.getY()+1);
                    robo = coordenadas[x][y+1];
                }
            }else if(matriz[x-1][y] == '0'){
                ObjectRobo.setDirect(Robo.direcao.CIMA);
                ObjectRobo.setX(ObjectRobo.getX()-1);
                robo = coordenadas[x-1][y];
            }
        }else if(ObjectRobo.getDirect() == Robo.direcao.CIMA){
            if(matriz[x][y+1] == '3'){
                ObjectRobo.setDirect(Robo.direcao.DESLIGADO);
            }else
            if(matriz[x][y+1] == '1' || matriz[x][y+1] == '2'){
                if(matriz[x-1][y] == '0'){
                    ObjectRobo.setX(ObjectRobo.getX()-1);
                    robo = coordenadas[x-1][y];
                }else if(matriz[x-1][y] == '3'){
                    ObjectRobo.setDirect(Robo.direcao.DESLIGADO);
                }else if(matriz[x-1][y] == '1' && matriz[x][y-1] == '0'){
                    ObjectRobo.setDirect(Robo.direcao.ESQUERDA);
                    ObjectRobo.setY(ObjectRobo.getY()-1);
                    robo = coordenadas[x][y-1];
                }else if(matriz[x-1][y] == '1' && matriz[x][y-1] == '1'){
                    ObjectRobo.setDirect(Robo.direcao.BAIXO);
                    ObjectRobo.setX(ObjectRobo.getX()+1);
                    robo = coordenadas[x+1][y];
                }
            }else if(matriz[x][y+1] == '0'){
                ObjectRobo.setDirect(Robo.direcao.DIREITA);
                ObjectRobo.setY(ObjectRobo.getY()+1);
                robo = coordenadas[x][y+1];
            }
        }else if(ObjectRobo.getDirect() == Robo.direcao.BAIXO){
            if(matriz[x][y-1] == '3'){
                ObjectRobo.setDirect(Robo.direcao.DESLIGADO);
            }else
            if(matriz[x][y-1] == '1' || matriz[x][y-1] == '2'){
                if(matriz[x+1][y] == '0'){
                    ObjectRobo.setX(ObjectRobo.getX()+1);
                    robo = coordenadas[x+1][y];
                }else if(matriz[x+1][y] == '3'){
                    ObjectRobo.setDirect(Robo.direcao.DESLIGADO);
                }else if(matriz[x+1][y] == '1' && matriz[x][y+1] == '0'){
                    ObjectRobo.setDirect(Robo.direcao.DIREITA);
                    ObjectRobo.setY(ObjectRobo.getY()+1);
                    robo = coordenadas[x][y+1];
                }else if(matriz[x+1][y] == '1' && matriz[x][y+1] == '1'){
                    ObjectRobo.setDirect(Robo.direcao.CIMA);
                    ObjectRobo.setX(ObjectRobo.getX()-1);
                    robo = coordenadas[x-1][y];
                }
            }else if(matriz[x][y-1] == '0'){
                ObjectRobo.setDirect(Robo.direcao.ESQUERDA);
                ObjectRobo.setY(ObjectRobo.getY()-1);
                robo = coordenadas[x][y-1];
            }
            
        }
        repaint();
    }

}
