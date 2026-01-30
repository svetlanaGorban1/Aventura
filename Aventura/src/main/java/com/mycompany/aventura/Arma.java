/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aventura;

import java.util.Random;

/**
 *
 * @author damt110
 */
public class Arma {

    private String tipo;
    private int danioMaximo;

    public Arma(String tipo, int danioMaximo) {
        this.tipo = tipo;
        this.danioMaximo = danioMaximo;
    }
       
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getDanioMaximo() {
        return danioMaximo;
    }

    public void setDanioMaximo(int danioMaximo) {
        this.danioMaximo = danioMaximo;
    }

    public int danioProducido() {
        Random r = new Random();
        int danio = r.nextInt(1, danioMaximo);
        return danio;
    }
    
}
