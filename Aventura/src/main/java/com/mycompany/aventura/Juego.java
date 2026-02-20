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

    private Enemigo[] enemigos;
    private Jugador jugador;
    private int min;
    private int max;
    private int contador;
    boolean enemigoMuerte;
    Arma arma1 = new Arma("Cuchillo", 6);
    Arma arma2 = new Arma("Espada larga", 7);
    Arma arma3 = new Arma("Espada", 5);
    Arma arma4 = new Arma("Porra", 8);
    Arma[] armas = {arma1, arma2, arma3, arma4};
    Arma armaEsp1 = new Arma("Espada de acero", 7);
    Arma armaEsp2 = new Arma("Sable", 8);
    Arma[] armasEspecial = {armaEsp1, armaEsp2};
    Scanner teclado = new Scanner(System.in);

    public Juego() {
        this.enemigos = new Enemigo[3];
        enemigos[0] = new Enemigo("Monstruo", 10, 3);
        enemigos[1] = new Enemigo("Gigante", 10, 5);
        enemigos[2] = new Enemigo("Mago", 1, 7);
    }

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
        System.out.println("Introduce nombre del jugador");
        String nombre = teclado.nextLine();
        jugador = new Jugador(nombre, 10, armas);
        eligirMenu();
    }

    public Enemigo buscarEnemigo() {
        for (int i = 0; i < enemigos.length; i++) {
            if (enemigos[i] != null && enemigos[i].isDerrotado() == false) {
                return enemigos[i];
            }
        }
        return null;
    }

    //metodo de combate
    public boolean combateConMonstrue() {
        boolean jugadorMuerte = false;
        Random r = new Random();
        int menu = 0;
        Enemigo enemigo = enemigos[0];
        int danioMonstruo;
        int randomArma = r.nextInt(0, armas.length - 1);

        while (jugador.getVida() > 0 && enemigo.getVida() > 0 && menu != 3) {
            System.out.println("¿Que queres haser? 1.Atacar 2.Curar(vida+2) 3.Fin del combate");
            menu = teclado.nextInt();
            enemigo = buscarEnemigo();
            if (enemigo == null) {
                sur();
            } else {
                switch (menu) {
//Opcion para atacar
                    case 1:
                        int probabilidad = r.nextInt(0, 100);
                        System.out.println("Nombre:" + jugador.getNombre() + " Vida:" + jugador.getVida() + " Tipo de arma:"
                                + jugador.getArma()[0].getTipo() + ", Danio maximo " + jugador.getArma()[0].getDanioMaximo());
//Danio que el jugador hace de monstruo (numero aleatorio)
                        int danioJugador = r.nextInt(0, jugador.getArma()[0].getDanioMaximo());
//Probabilidad 20% y daño*2 
                        if (probabilidad < 20) {
                            danioJugador = jugador.golpeCritico(danioJugador);
                        }
                        enemigo.setVida(enemigo.getVida() - danioJugador);
                        if (enemigo.getVida() <= 0) {
                            enemigo.setDerrotado(true);
                        }
                        System.out.println("Atacas con " + enemigo.getTipo() + " y haces " + danioJugador + " de daño");
//Si enemigo tiene vida, el atace                    
                        if (enemigo.getVida() >= 1) {
                            danioMonstruo = r.nextInt(0, enemigo.getDanioMaximo());
                            jugador.restarVida(danioMonstruo);
                            System.out.println("El enemigo te ataca y te hace " + danioMonstruo + " de daño");
                        }
                        break;
//Opcion para curar vida
                    case 2:
                        System.out.println("Nombre:" + jugador.getNombre() + " Vida:" + jugador.getVida() + " Tipo de arma:"
                                + jugador.getArma()[randomArma].getTipo() + " Danio maximo" + jugador.getArma()[randomArma].getDanioMaximo());
                        jugador.sumarVida(2);
                        System.out.println("Te curas 2 puntos");
                        break;
                    case 3:
                        System.out.println("FIN DEL COMBATE");
                        eligirMenu();
                        break;
                }
            }
        }

        if (jugador.getVida() <= 0) {
            jugadorMuerte = true;
        } else {
            jugadorMuerte = false;
            contador++;
        }
        return jugadorMuerte;
    }

    public void malFinal() {
        System.out.println("El jugador perdió");
    }

    public void buenFinal() {
        System.out.println("El jugador ganado el combate " + contador + " veces");
        enemigoMuerte = true;
        //eligirMenu();
    }

    public void norte() {
        System.out.println("Emcuentras un monstruo y se va a iniciar el combate");
        boolean resultado = combateConMonstrue();
        if (resultado == true) {
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
        jugador.setArma(armasEspecial);
        System.out.println("Ahora el jugador tiene armas: " + jugador.getArma()[0].getTipo()
                + " y " + jugador.getArma()[1].getTipo());
    }

    //fin del juego
    public void sur() {
        System.out.println("Aquí es donde termina el juego");
        System.out.println("FIN DEL JUEGO");
    }

    //principal metodo para eligir acción
    public void eligirMenu() {
        int menu = 0;
        do {
            System.out.println(" ¿Qué dirección eliges, " + jugador.getNombre() + "? 1.norte 2.bosque 3.sur");
            menu = leerOpcion(1, 3);
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
        } while (enemigoMuerte == false && menu != 3);
    }
}
