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

    Enemigo enemigo = new Enemigo("Monstruo", 10, 3);
    
    public void escenaInicial() {
        System.out.println("La Historia impieza");
        eligirMenu();
    }

    public Arma[] crearArrayDeArmas() {
        Arma arma1 = new Arma("Cuchillo", 6);
        Arma arma2 = new Arma("Espada larga", 7);
        Arma arma3 = new Arma("Espada", 5);
        Arma arma4 = new Arma("Porra", 8);
        Arma[] armas = {arma1, arma2, arma3, arma4};
        return armas;
    }

    public void cambiarArma(Jugador jugador) {
        Arma[] armas = crearArrayDeArmas();
        Random r = new Random();
        int randomArma = r.nextInt(0, 3);
        jugador.getArma().setTipo(armas[randomArma].getTipo());
        jugador.getArma().setDanioMaximo(armas[randomArma].getDanioMaximo());
    }

    //metodo para crear jugador
    public Jugador crearJugador() {
        Arma[] armas=crearArrayDeArmas();
        Scanner teclado = new Scanner(System.in);
        System.out.println("Introduce nombre del jugador");
        String nombre = teclado.nextLine();
        Jugador jugador = new Jugador(nombre, 10, armas[0]);
        return jugador;
    }

    //metodo de combate
    public void norte(Jugador jugador) {
        
        System.out.println("Emcuentras un monstruo y se va a iniciar el combate");
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
    }

    //methodo que cambio citio y vida
    public void bosque(Jugador jugador) {
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
        Scanner teclado = new Scanner(System.in);
        Jugador jugador = crearJugador();
        int menu = 0;
        while (jugador.getVida() > 0 && enemigo.getVida() > 0 && menu != 3) {
            System.out.println("¿Qué dirección eliges? 1.norte 2.bosque 3.sur");
            menu = teclado.nextInt();
            switch (menu) {
                case 1:
                    norte(jugador);
                    break;
                case 2:
                    bosque(jugador);
                    break;
                case 3:
                    sur();
                    break;
            }
            if (jugador.getVida() == 0) {
                System.out.println("GAME OVER");
            } else if (enemigo.getVida() <= 0) {
                System.out.println("¡Has ganado el combate!");
            }
        }
    }

}
