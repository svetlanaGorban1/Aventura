/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aventura;

/**
 *
 * @author damt110
 */
public class Jugador {

     private String nombre;
    private Arma arma;

    public Jugador(String nombre, Arma arma, int vida, int vidaMaxima) {
        super(vida, vidaMaxima);
        this.nombre = nombre;
        this.arma = arma;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Arma getArma() {
        return arma;
    }

    public void setArma(Arma arma) {
        this.arma = arma;
    }

    public int golpeCritico(int danio) {
        int danioDeGolpeCritico = danio * 2;
        return danioDeGolpeCritico;
    }

    @Override
    public int atacar() {
        int danio = arma.getDanioMaximo();
        return danio;
    }
}
