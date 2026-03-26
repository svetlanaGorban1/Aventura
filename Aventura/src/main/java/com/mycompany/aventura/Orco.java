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
public class Orco extends Enemigo implements Defendible {

    public Orco(String tipo, int danioMaximo, boolean derrotado, int vida, int vidaMaxima) {
        super(tipo, danioMaximo, derrotado, vida, vidaMaxima);
    }

    @Override
    public int atacar() {
        Random r = new Random();
        int danio = r.nextInt(0, getDanioMaximo());
        return danio;
    }
    
    @Override
    public int modificarDanioRecibido(){
        int danio=0;
        return danio;
    }
}
