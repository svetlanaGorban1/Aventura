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
    private int vida;
    private Arma[] armas;
        
    public Jugador(String nombre, int vida, Arma[] armas) {
        this.nombre = nombre;
        this.vida = vida;
        this.armas = armas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public Arma[] getArma() {
        return armas;
    }

    public void setArma(Arma[] armas) {
        this.armas = armas;
    }

    public void restarVida(int cantidad) {
        vida -= cantidad;
    }

    public void sumarVida(int cantidad) {
        vida += cantidad;
    }
}
