package com.javarush.test.level21.lesson16.big01;

import java.util.ArrayList;

/**
 * Created by HMF on 15.02.2016.
 */
public class Hippodrome
{
    public static ArrayList<Horse> horses = new ArrayList<Horse>();

    public static Hippodrome game;

    public ArrayList<Horse> getHorses()
    {
        return horses;
    }

    public static void main(String[] args) throws InterruptedException{
        game = new Hippodrome();
        game.horses.add(new Horse("lodaf", 3.0, 0));
        game.horses.add(new Horse("rudolf", 3.0, 0));
        game.horses.add(new Horse("walet", 3.0, 0));
        game.run();
        game.printWinner();

    }
    public  void run() throws InterruptedException{
        for(int i = 1; i <= 100; i++){
                move();
                print();
                Thread.sleep(100);

        }
    }
    public void move(){
        for(Horse horse : horses){
            horse.move();
        }
    }
    public void print(){
        for(Horse horse : horses){
            horse.print();
        }
        System.out.println("");
        System.out.println("");
    }
    public Horse getWinner(){
        double dis=0;
        Horse winner=null;
        for (Horse horse : horses){
            if (horse.getDistance() > dis) {
                dis=horse.getDistance();
                winner=horse;
            }
        }
        return winner;
    }
    public void printWinner(){
        System.out.println("Winner is " + getWinner().getName() + "!");
    }
}
