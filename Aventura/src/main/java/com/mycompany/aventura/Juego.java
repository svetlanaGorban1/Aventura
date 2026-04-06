/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aventura;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author damt110
 */
public class Juego {

    private ArrayList<Enemigo> enemigos;
    private ArrayList<Arma> armas;
    private ArrayList<Arma> armasEspecial;
    private Jugador jugador;
    private int min;
    private int max;
    private int contador;
    boolean enemigoMuerte = false;

    Scanner teclado = new Scanner(System.in);
    Random r = new Random();

    public Juego() {
        enemigos = new ArrayList<>();
        enemigos.add(new Enemigo("Monstruo", 8, 7, 10));
        enemigos.add(new Enemigo("Monstruo", 6, 6, 10));
        enemigos.add(new Enemigo("Gigante", 7, 6, 10));
        enemigos.add(new Enemigo("Mago", 5, 7, 10));

        armas = new ArrayList<>();
        armas.add(new Arma("Cuchillo", 6));
        armas.add(new Arma("Espada larga", 7));
        armas.add(new Arma("Espada", 5));
        armas.add(new Arma("Porra", 8));

        armasEspecial = new ArrayList<>();
        armasEspecial.add(new Arma("Espada de acero", 9));
        armasEspecial.add(new Arma("Sable", 6));
        armasEspecial.add(new Arma("Sable de acero", 7));
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
        jugador = new Jugador(nombre, armas.get(0), 5, 10);
        eligirMenu();
    }

    public Enemigo buscarEnemigo() {
        for (Enemigo en : enemigos) {
            if (en != null && en.isDerrotado() == false) {
                return en;
            }
        }
        return null;
    }

    //metodo de combate
    public boolean combateConMonstrue() {
        boolean jugadorMuerte = false;
        int menu = 0;
        Enemigo enemigo = enemigos.get(0);

        do {
            System.out.println("¿Que queres haser? 1.Atacar 2.Curar(vida+2) 3.Fin del combate");
            menu = teclado.nextInt();
            enemigo = buscarEnemigo();
            if (enemigo == null) {
                sur();
            } else {
                switch (menu) {
//Opcion para atacar
                    case 1:
                        System.out.println("Nombre:" + jugador.getNombre() + " Vida:" + jugador.getVida() + " Tipo de arma:"
                                + jugador.getArma().getTipo() + ", Danio maximo "
                                + jugador.getArma().getDanioMaximo());
//Danio que el jugador hace de monstruo (numero aleatorio)
                        int danioJugador = jugador.atacar();
                        if (enemigo instanceof Defendible) {
                            Defendible defensa = (Defendible) enemigo;
                            danioJugador = defensa.modificarDanioRecibido(danioJugador);
                        }
                        enemigo.restarHP(danioJugador);
                        if (enemigo.getVida() <= 0) {
                            enemigo.setDerrotado(true);
                        }
                        System.out.println("Atacas con " + enemigo.getTipo() + " y haces " + danioJugador
                                + " de daño y vida de jugador:" + jugador.getVida());
//Si enemigo tiene vida, el atace                    
                        if (enemigo.getVida() >= 1) {
                            int danioMonstruo;
                            danioMonstruo = enemigo.atacar();

                            jugador.restarHP(danioMonstruo);
                            System.out.println("El enemigo te ataca y te hace " + danioMonstruo + " de daño");
                        }
                        break;
//Opcion para curar vida
                    case 2:
                        System.out.println("Nombre:" + jugador.getNombre() + " Vida:" + jugador.getVida() + " Tipo de arma:"
                                + jugador.getArma().getTipo() + " Danio maximo"
                                + jugador.getArma().getDanioMaximo());
                        jugador.sumarHP(2);
                        System.out.println("Te curas 2 puntos");
                        break;
                    case 3:
                        System.out.println("FIN DEL COMBATE");
                        break;
                }
            }
        } while (enemigo != null && jugador.getVida() > 0 && enemigo.getVida() > 0 && menu != 3);

        if (jugador.getVida() <= 0) {
            jugadorMuerte = true;
        } else {
            jugadorMuerte = false;
        }

        return jugadorMuerte;
    }

    public void malFinal() {
        System.out.println("El jugador perdió");
    }

    public void buenFinal() {
        System.out.println("El jugador ganado el combate ");
    }

    public void norte() {
        System.out.println("Emcuentras un monstruo y se va a iniciar el combate");
        boolean resultado = combateConMonstrue();
        if (resultado == true) {
            malFinal();
        }
    }

    //methodo que cambio citio y vida
    public void bosque() {
        jugador.sumarHP(2);
        System.out.println("Lugar de la batalla ha sido cambiado");
        System.out.println("Jugador curar vida(+2) y el jugador tiene vida: "
                + jugador.getVida());
        System.out.println("Cambiar arma por arma especial:");
        ordenarArmasEspeciales();
        jugador.setArma(armasEspecial.get(max));
        System.out.println("Ahora el jugador tiene una arma especial: " + jugador.getArma().getTipo());
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
            System.out.println(" ¿Qué dirección eliges, " + jugador.getNombre() + "? 1.norte 2.bosque 3.FIN");
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
        } while (menu != 3);
    }
    
    public void ordenarArmasEspeciales(){
        Collections.sort(armasEspecial);
    }
}
