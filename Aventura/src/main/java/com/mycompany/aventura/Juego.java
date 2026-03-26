/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aventura;

import java.util.ArrayList;
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

    public Juego() {
        enemigos = new ArrayList<>();
        enemigos.add(new Enemigo("Monstruo", 8, 10, 10));
        /* enemigos.add(new Enemigo("Monstruo", 10, 3));
        enemigos.add(new Enemigo("Gigante", 10, 5));
        enemigos.add(new Enemigo("Mago", 1, 7));*/

        armas = new ArrayList<>();
        armas.add(new Arma("Cuchillo", 6));
        armas.add(new Arma("Espada larga", 7));
        armas.add(new Arma("Espada", 5));
        armas.add(new Arma("Porra", 8));

        armasEspecial = new ArrayList<>();
        armasEspecial.add(new Arma("Espada de acero", 7));
        armasEspecial.add(new Arma("Sable", 8));
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
        Random r = new Random();
        int menu = 0;
        Enemigo enemigo = enemigos.get(0);
        int danioMonstruo;
        int randomArma = r.nextInt(0, armas.size() - 1);

        while (enemigo != null && jugador.getVida() > 0 && enemigo.getVida() > 0 && menu != 3) {
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
                        int danioJugador = r.nextInt(0, jugador.getArma().getDanioMaximo());
//Probabilidad 20% y daño*2 

                        enemigo.atacar();
                        if (enemigo.getVida() <= 0) {
                            enemigo.setDerrotado(true);
                        }
                        System.out.println("Atacas con " + enemigo.getTipo() + " y haces " + danioJugador + " de daño");
//Si enemigo tiene vida, el atace                    
                        if (enemigo.getVida() >= 1) {
                            danioMonstruo = r.nextInt(0, enemigo.getDanioMaximo());
                            Jugador j=(Jugador) jugador;
                            j.restarHP(danioMonstruo);
                            System.out.println("El enemigo te ataca y te hace " + danioMonstruo + " de daño");
                        }
                        break;
//Opcion para curar vida
                    case 2:
                        System.out.println("Nombre:" + jugador.getNombre() + " Vida:" + jugador.getVida() + " Tipo de arma:"
                                + jugador.getArma().get(randomArma).getTipo() + " Danio maximo"
                                + jugador.getArma().get(randomArma).getDanioMaximo());
                        j.sumarHP(2);
                        System.out.println("Te curas 2 puntos");
                        break;
                    case 3:
                        System.out.println("FIN DEL COMBATE");
                        break;
                }
            }
        }

        if (jugador.getVida() <= 0) {
            jugadorMuerte = true;
        } else {
            jugadorMuerte = false;
        }

        /* if (jugadorMuerte==true){
            malFinal();
        }else{
            buenFinal();
        }*/
        return jugadorMuerte;
    }

    public void malFinal() {
        System.out.println("El jugador perdió");
    }

    public void buenFinal() {
        System.out.println("El jugador ganado el combate ");
        //  enemigoMuerte = true;
        //  eligirMenu();
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
        jugador.sumarVida(2);
        System.out.println("Lugar de la batalla ha sido cambiado");
        System.out.println("Jugador curar vida(+2) y el jugador tiene vida: "
                + jugador.getVida());
        jugador.setArma(armasEspecial);
        System.out.println("Ahora el jugador tiene armas: " + jugador.getArma().get(0).getTipo()
                + " y " + jugador.getArma().get(1).getTipo());
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
}
