/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aventura;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author damt110
 */
public class Juego {

    private Enemigo enemigo;
    private Jugador jugador;
    Arma arma1 = new Arma("Cuchillo", 6);
    Arma arma2 = new Arma("Espada larga", 7);
    Arma arma3 = new Arma("Espada", 5);
    Arma arma4 = new Arma("Porra", 8);
    Arma[] armas = {arma1, arma2, arma3, arma4};
    Scanner teclado = new Scanner(System.in);

    public void escenaInicial() {
        System.out.println("La Historia impieza");
        enemigo = new Enemigo("Monstruo", 10, 3);
        System.out.println("Introduce nombre del jugador");
        String nombre = teclado.nextLine();
        jugador = new Jugador(nombre, 10, armas);
        eligirMenu();
    }

    //metodo de combate
    public void combateConMonstrue() {
        Random r = new Random();
        while (jugador.getVida() > 0 && enemigo.getVida() > 0) {
//Danio que el jugador hace de monstruo (numero aleatorio y arma aleatorio)
            int randomArma = r.nextInt(0, armas.length);
            int danioJugador = r.nextInt(0, jugador.getArma()[randomArma].getDanioMaximo());
            enemigo.setVida(enemigo.getVida() - danioJugador);
            System.out.println("Atacas con " + enemigo.getTipo() + " y haces " + danioJugador + " de daño");
//Si monstruo tiene vidas, el atace                    
            if (enemigo.getVida() >= 1) {
                jugador.restarVida(enemigo.danioAtaque());
                System.out.println("El goblin te ataca y te hace " + enemigo.danioAtaque() + " de daño");
            }
        }
        if (jugador.getVida() == 0) {
            System.out.println("GAME OVER");
        } else if (enemigo.getVida() <= 0) {
            System.out.println("¡Has ganado el combate!");
        }
    }

    public void norte() {
        System.out.println("Emcuentras un monstruo y se va a iniciar el combate");
        System.out.println("Partida empieza");
        combateConMonstrue();
    }

    //methodo que cambio citio y vida
    public void bosque() {
        System.out.println("Lugar de la batalla ha sido cambiado");
        System.out.println("Jugador curar vida(+2) y enemigo curar su vida(+1)");
        jugador.sumarVida(2);
        enemigo.setVida(enemigo.getVida() + 1);
    }

    //fin del huego
    public void sur() {
        System.out.println("FIN DEL JUEGO");
    }

    //principal metodo para eligir acción
    public void eligirMenu() {
        int menu = 0;
        while (menu != 3) {
            System.out.println("¿Qué dirección eliges? 1.norte 2.bosque 3.sur");
            menu = teclado.nextInt();
            switch (menu) {
                case 1:
                    norte();
                    break;
                case 2:
                    bosque();
                    break;
                case 3:
                    sur();
                    break;
            }
        }
    }
}
