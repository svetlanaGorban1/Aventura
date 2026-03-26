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
public class Goblin extends Enemigo {

    public Goblin(String tipo, int danioMaximo, boolean derrotado, int vida, int vidaMaxima) {
        super(tipo, danioMaximo, derrotado, vida, vidaMaxima);
    }

    @Override
    public int atacar() {
        int danioCritico = atacar() * 2;
        return danioCritico;
    }
}
