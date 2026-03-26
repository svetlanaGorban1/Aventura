/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aventura;

/**
 *
 * @author damt110
 */
public abstract class Personaje {
    private int vida;
    private int vidaMaxima;

    public Personaje(int vida, int vidaMaxima) {
        this.vida = vida;
        this.vidaMaxima = vidaMaxima;
    }

    public void restarHP(int cantidad) {
        if (cantidad > 0) {
            vida -= cantidad;
        }
    }

    public void sumarHP(int cantidad) {
        if ((vida + cantidad) < vidaMaxima) {
            vida += cantidad;
        }
    }

    public int getVida() {
        return vida;
    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public boolean estaVivo() {
        boolean resultado = true;
        return resultado;
    }

    public abstract int atacar();

}


