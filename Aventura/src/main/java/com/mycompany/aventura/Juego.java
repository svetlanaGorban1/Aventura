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
    private int min;
    private int max;
   // boolean enemigoMuerto=true;
    Arma arma1 = new Arma("Cuchillo", 6);
    Arma arma2 = new Arma("Espada larga", 7);
    Arma arma3 = new Arma("Espada", 5);
    Arma arma4 = new Arma("Porra", 8);
    Arma[] armas = {arma1, arma2, arma3, arma4};
    Scanner teclado = new Scanner(System.in);

    public int leerOpcion(int min, int max) {
        this.min = min;
        this.max = max;
        int opcion = 0;
        while (opcion < min || opcion > max) {
            System.out.println("Introduce un numero desde " + min + " hasta " + max);
            opcion = teclado.nextInt();
        }
        return opcion;
    }

    public void escenaInicial() {
        System.out.println("La Historia impieza");
        enemigo = new Enemigo("Monstruo", 10, 3);
        System.out.println("Introduce nombre del jugador");
        String nombre = teclado.nextLine();
        jugador = new Jugador(nombre, 10, armas);
        eligirMenu();
    }

    //metodo de combate
    public boolean combateConMonstrue() {
        boolean jugadorMuerte = false;
        Random r = new Random();
        while (jugador.getVida() > 0 && enemigo.getVida() > 0) {
            //Danio que el jugador hace de monstruo (numero aleatorio y arma aleatorio)
            int randomArma = r.nextInt(0, armas.length);
            int danioJugador = r.nextInt(0, jugador.getArma()[randomArma].getDanioMaximo());
            enemigo.setVida(enemigo.getVida() - danioJugador);
            //Si monstruo tiene vidas, el atace                    
            if (enemigo.getVida() >= 1) {
                jugador.restarVida(enemigo.danioAtaque());
            }
        }
        if (jugador.getVida() == 0) {
            jugadorMuerte = true;
        } else {
            jugadorMuerte = false;
        }
        return jugadorMuerte;
    }

    public void malFinal() {
        System.out.println("El jugador perdió");
        sur();
    }

    public void buenFinal() {
        System.out.println("El jugador ganado el combate!");
        eligirMenu();
    }

    public void norte() {
        System.out.println("Emcuentras un monstruo y se va a iniciar el combate");
        combateConMonstrue();
        if (combateConMonstrue()) {
            malFinal();
        } else {
            buenFinal();
        }
    }

    //methodo que cambio citio y vida
    public void bosque() {
        System.out.println("Lugar de la batalla ha sido cambiado");
        System.out.println("Jugador curar vida(+2) y enemigo curar su vida(+2)");
        jugador.sumarVida(2);
        enemigo.setVida(enemigo.getVida() + 2);
        
    }

    //fin del huego
    public void sur() {
        System.out.println("Aquí es donde termina el juego");
        System.out.println("FIN DEL JUEGO");
    }

    //principal metodo para eligir acción
    public void eligirMenu() {
        System.out.println("¿Qué dirección eliges? 1.norte 2.bosque 3.sur");
        int menu = leerOpcion(1, 3);
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
