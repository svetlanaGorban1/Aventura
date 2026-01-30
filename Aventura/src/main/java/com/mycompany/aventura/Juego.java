/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aventura;

import java.util.Scanner;

/**
 *
 * @author damt110
 */
public class Juego {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        Arma arma = new Arma("Cuchillo", 6);
        System.out.println("Introduce nombre del jugador");
        String nombre = teclado.nextLine();
        Jugador jugador = new Jugador(nombre, 10, arma);
        Enemigo enemigo = new Enemigo("Monstruo", 10, 3);
        
        int menu=0;
         while (jugador.getVida() > 0 && enemigo.getVida() > 0 && menu != 3) {
            System.out.println("¿Que queres haser? 1.Atacar 2.Cambio citio 3.Huir");
            menu = teclado.nextInt();
                    switch (menu) {
                        case 1:        
                System.out.println("Partida empieza");
        System.out.println("El jugador ataca al enemigo");
        int danioJugador = jugador.getArma().danioProducido();
        int vidaEnemigo = enemigo.getVida() - danioJugador;
        enemigo.setDanioMaximo(vidaEnemigo);
        System.out.println("Atacas con " + enemigo.getTipo() + " y haces " + danioJugador + " de daño");
        if (enemigo.getVida() >= 1) {
            System.out.println("El enemigo ataca al jugador");
            int danioEnemigo = enemigo.danioAtaque();
            jugador.restarVida(danioJugador);
            System.out.println("El enemigo te ataca y te hace " + danioEnemigo + " de daño");
        }
        break;
                        case 2:
                            System.out.println("");                  
        if (jugador.getVida() == 0) {
            System.out.println("GAME OVER");
        } else if (enemigo.getVida() <= 0) {
            System.out.println("¡Has ganado el combate!");
        }
         }
    }
}

}
