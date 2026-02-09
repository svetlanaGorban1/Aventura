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
    boolean enemigoMuerto;
    Arma arma1 = new Arma("Cuchillo", 6);
    Arma arma2 = new Arma("Espada larga", 7);
    Arma arma3 = new Arma("Espada", 5);
    Arma arma4 = new Arma("Porra", 8);
    Arma[] armas = {arma1, arma2, arma3, arma4};
    Arma armaEsp1 = new Arma("Espada de acero", 7);
    Arma armaEsp2 = new Arma("Sable", 8);
    Arma[] armasEspecial = {armaEsp1, armaEsp2};
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
        int menu = 0;
        while (jugador.getVida() > 0 && enemigo.getVida() > 0 && menu != 3) {
            System.out.println("¿Que queres haser? 1.Atacar 2.Curarte(+2HP) 3.Huir");
            menu = teclado.nextInt();
            int danioMonstruo;
            switch (menu) {
//Opcion para atacar
                case 1:
                    int probabilidad = r.nextInt(0, 100);
                    System.out.println("Nombre:" + jugador.getNombre() + ", Vida: " + jugador.getVida() + ", Tipo de arma: "
                            + jugador.getArma()[0].getTipo() + ", Danio maximo " + jugador.getArma()[0].getDanioMaximo());
//Danio que el jugador hace de monstruo (numero aleatorio)
                    int danioJugador = r.nextInt(0, jugador.getArma()[0].getDanioMaximo());
//Probabilidad 20% y daño*2 
                    if (probabilidad < 20) {
                        danioJugador = jugador.golpeCritico(danioJugador);
                    }
                    enemigo.setVida(enemigo.getVida() - danioJugador);
                    System.out.println("Atacas con " + enemigo.getTipo() + " y haces " + danioJugador + " de daño");
//Si enemigo tiene vidas, el atace                    
                    if (enemigo.getVida() >= 1) {
                        danioMonstruo = r.nextInt(0, enemigo.getDanioMaximo());
                        jugador.restarVida(danioMonstruo);
                        System.out.println("El enemigo te ataca y te hace " + danioMonstruo + " de daño");
                    }
                    break;
//Opcion para curar hp
                case 2:
                    System.out.println("Nombre:" + jugador.getNombre() + " ,HP: " + jugador.getVida() + " ,Tipo de arma: "
                            + jugador.getArma()[0].getTipo() + " ,Danio maximo " + jugador.getArma()[0].getDanioMaximo());
                    jugador.sumarVida(2);
                    System.out.println("Te curas 2 puntos");
                    danioMonstruo = r.nextInt(0, enemigo.getDanioMaximo());
                    jugador.restarVida(danioMonstruo);
                    System.out.println("El enemigo te ataca y te hace " + danioMonstruo + " de daño");
                    break;
                case 3:
                    System.out.println("Has huido. FIN DEL JUEGO");
                    break;
            }

            while (jugador.getVida() > 0 && enemigo.getVida() > 0) {
                //Danio que el jugador hace de enemigo (numero aleatorio y arma aleatorio)
                int randomArma = r.nextInt(0, armas.length);
                int danioJugador = r.nextInt(0, jugador.getArma()[randomArma].getDanioMaximo());
                enemigo.setVida(enemigo.getVida() - danioJugador);
                //Si enemigo tiene vidas, el atace                    
                if (enemigo.getVida() >= 1) {
                    jugador.restarVida(enemigo.danioAtaque());
                }
            }
            if (jugador.getVida() == 0) {
                jugadorMuerte = true;
            } else {
                jugadorMuerte = false;
            }
        }
        return jugadorMuerte;
    }

    public void malFinal() {
        System.out.println("El jugador perdió");
        sur();
    }

    public void buenFinal() {
        System.out.println("El jugador ganado el combate!");
        enemigoMuerto = true;
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
        jugador.sumarVida(2);
        System.out.println("Lugar de la batalla ha sido cambiado");
        System.out.println("Jugador curar vida(+2) y el jugador tiene vida: "
                + jugador.getVida());
        if (enemigoMuerto == true) {
            jugador.setArma(armasEspecial);
            System.out.println("Ahora el jugador tiene armas: " + jugador.getArma()[0].getTipo()
                    + " y " + jugador.getArma()[1].getTipo());
        } else {
            System.out.println("Comienza la batalla");
            combateConMonstrue();
        }
        if (combateConMonstrue()) {
            malFinal();
        } else {
            buenFinal();
        }
    }

    //fin del huego
    public void sur() {
        System.out.println("Aquí es donde termina el juego");
        System.out.println("FIN DEL JUEGO");
    }

    /* public void golpeCriticoEnCastillo() {

    }
     */
    //principal metodo para eligir acción
    public void eligirMenu() {
        System.out.println(" ¿Qué dirección eliges? " + jugador.getNombre() + " 1.norte 2.bosque 3.sur");
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
            /*case 4:
                golpeCriticoEnCastillo();
                break;*/
        }
    }
}
