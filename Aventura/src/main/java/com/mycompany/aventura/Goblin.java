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

    public Goblin(String tipo, int danioMaximo, int vida, int vidaMaxima) {
        super(tipo, danioMaximo, vida, vidaMaxima);
    }

    @Override
    public int atacar() {
        int danio = super.atacar();
        Random r = new Random();
        int aleatorio = r.nextInt(1, 100);
        if (aleatorio > 20) {
            danio = danio * 2;
        }
        return danio;
    }
}
