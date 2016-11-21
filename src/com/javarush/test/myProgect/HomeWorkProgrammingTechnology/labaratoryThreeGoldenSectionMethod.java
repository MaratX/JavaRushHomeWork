package com.javarush.test.myProgect.HomeWorkProgrammingTechnology;

import sun.util.resources.LocaleData;

import java.time.LocalTime;

/**
 * Created by HMF on 18.11.2016.
 */
public class labaratoryThreeGoldenSectionMethod
{
    private static final double DELTA_PHI = (1 + Math.sqrt(5) / 2);


    public static void main(String... args){
        labaratoryThreeGoldenSectionMethod l = new labaratoryThreeGoldenSectionMethod();
        System.out.println(l.goldenSectionSearchMIN(-2, 2, 0.001));
        System.out.println(l.goldenSectionSearchMAX(-8, 0, 0.001));


    }

    private double F(double x){
        return Math.pow(x + 1, 3) + 5 * Math.pow(x, 2);
    }


    public double goldenSectionSearchMIN(double startingPoint, double endPoint, double accuracy){
        double X1, X2;
        while (true){
            X1 = endPoint - (endPoint - startingPoint) / DELTA_PHI;
            X2 = startingPoint + (endPoint - startingPoint) / DELTA_PHI;
            if(F(X1) >= F(X2))
                startingPoint = X1;
            else
                endPoint = X2;
            if(Math.abs(endPoint - startingPoint) < accuracy)
                break;
        }
        return (startingPoint + endPoint) / 2;
    }


    public double goldenSectionSearchMAX(double startingPoint, double endPoint, double accuracy){
        double X1, X2;
        while (true){
            X1 = endPoint - (endPoint - startingPoint) / DELTA_PHI;
            X2 = startingPoint + (endPoint - startingPoint) / DELTA_PHI;
            if(F(X1) <= F(X2))
                startingPoint = X1;
            else
                endPoint = X2;
            if (Math.abs(endPoint - startingPoint) < accuracy)
                break;
        }
        return (startingPoint + endPoint) / 2;
    }

}