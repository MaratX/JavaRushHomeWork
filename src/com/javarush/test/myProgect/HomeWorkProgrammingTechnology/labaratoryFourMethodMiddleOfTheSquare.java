package com.javarush.test.myProgect.HomeWorkProgrammingTechnology;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by HMF on 21.11.2016.
 */
public class labaratoryFourMethodMiddleOfTheSquare
{


    public static void ResiverMiddleOfTheSquare(int i, double j){
        Double k = Math.pow(j, j);
        System.out.println(i + " : "+ k.toString().substring(4, 8));
        if(i > 0){
            i--;
            ResiverMiddleOfTheSquare(i, k);
        }



    }

    public static double safetyValve(Double d){
        byte k  = d.byteValue();
        return 0;
    }

    public static void main(String... args){
        ResiverMiddleOfTheSquare(90, 0.04);
    }
}
