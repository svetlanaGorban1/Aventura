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
public class Enemigo {

    private String tipo;
    private int vida;
    private int danioMaximo;
    private boolean derrotado;

    public boolean isDerrotado() {
        return derrotado;
    }

    public void setDerrotado(boolean derrotado) {
        this.derrotado = derrotado;
    }

    public Enemigo(String tipo, int vida, int danioMaximo) {
        this.tipo = tipo;
        this.vida = vida;
        this.danioMaximo = danioMaximo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getDanioMaximo() {
        return danioMaximo;
    }

    public void setDanioMaximo(int danioMaximo) {
        this.danioMaximo = danioMaximo;
    }
    
    public int danioAtaque() {
        Random r = new Random();
        int danio = r.nextInt(1, danioMaximo);
        return danio;
    }
}
