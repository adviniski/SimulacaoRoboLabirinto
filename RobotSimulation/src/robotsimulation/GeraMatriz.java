/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robotsimulation;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author david
 */
public class GeraMatriz {
    
    private char[][] matriz;
    
    private int LINHAS;
    private int COLUNAS;
    
    public int getLINHAS() {
        return LINHAS;
    }

    public void setLINHAS(int LINHAS) {
        this.LINHAS = LINHAS;
    }

    public int getCOLUNAS() {
        return COLUNAS;
    }

    public void setCOLUNAS(int COLUNAS) {
        this.COLUNAS = COLUNAS;
    }
    public char[][] getMatriz() {
        return matriz;
    }
    
    public GeraMatriz(String arquivo){
        FileInputStream stream;
        try {
            stream = new FileInputStream("src//mapas//"+arquivo);
            InputStreamReader reader = new InputStreamReader(stream);
            BufferedReader br = new BufferedReader(reader);
            String linha = br.readLine();
            
            LINHAS = Integer.valueOf(linha);
            linha = br.readLine();
            COLUNAS = Integer.valueOf(linha);
            linha = br.readLine();
            matriz = new char[LINHAS][COLUNAS];
            int i=0;
            while(linha != null) {
               for(int j = 0; j < COLUNAS; j++){
                   matriz[i][j] =  linha.charAt(j);
               }
               linha = br.readLine();
               i++;
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GeraMatriz.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GeraMatriz.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}