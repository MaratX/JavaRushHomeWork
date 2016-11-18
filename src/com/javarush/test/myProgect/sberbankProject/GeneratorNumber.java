package com.javarush.test.myProgect.sberbankProject;

import java.util.Date;

/**
 * Created by HMF on 17.11.2016.
 */
public class GeneratorNumber
{
    public static void main(String... args){
        int counterr = 1;
        GeneratorNumber g = new GeneratorNumber();
        System.out.println("Гос номер вашей машины : " + g.GeneratorFilter(1, true) + " " + g.GeneratorFilter(3, false) + " " + g.GeneratorFilter(2, true));
    }
    public static final String DICTIONARY [] = {"А", "В", "Е", "К", "М", "Н", "О", "Р", "С", "Т", "У", "Х"};

    public String GeneratorFilter(int Characters, boolean isString){
        int counter = 1;
        String result = "";
        if(!isString){
            for(int j = 0; j < Characters; j++){
                result += GeneratorFirst(9, counter);
                counter++;
            }
        }else {
            for(int j = 0; j < Characters; j++){
                result += DICTIONARY[Integer.parseInt(GeneratorFirst(12, counter).toString())];
                counter++;
            }
        }
        return result;
    }
    public Long GeneratorFirst(int i, int u){
        Date d = new Date();
        return  ((d.getTime() * u^2) / i)%i;
    }
}
