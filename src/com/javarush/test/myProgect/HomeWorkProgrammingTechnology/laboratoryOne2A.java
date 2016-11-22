package com.javarush.test.myProgect.HomeWorkProgrammingTechnology;

import java.util.ArrayList;

/**
 * Created by HMF on 16.11.2016.
 */
public class laboratoryOne2A
{
    private int initialMatrix[] = {2, 4, 7, 89, 1, 23, 63, 98, 9, 33, 54, 12, 90, 1};


    public static void main(String... args)
    {
        laboratoryOne2A l = new laboratoryOne2A();

        for(int i : l.initialMatrix){
            System.out.print(i + " ");
        }
        System.out.println();
        l.initialMatrix = l.sort(l.initialMatrix);

        for(int i : l.initialMatrix){
            System.out.print(i + " ");
        }
    }
    /*----------------------
       Сравнение на  минимум
   ------------------------ */
    private boolean min(int one, int two){
        return one > two;
    }
    /*----------------------
        Сортировка N^2 ('2А')
    ------------------------ */
    private int[] sort(int [] list){
        for(int i = 0; i < list.length ; i++){
            for(int j = i + 1; j < list.length ; j++){
                if(this.min(list[i], list[j])){
                    int p = list[i];
                    list[i] = list[j];
                    list[j] = p;
                }
            }
        }
        return list;
    }
}















