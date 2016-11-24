package com.javarush.test.level26.lesson15.big01;

import java.util.HashMap;

/**
 * Created by HMF on 24.11.2016.
 */
public class CurrencyManipulatorFactory
{
    static HashMap<String, CurrencyManipulator> map = new HashMap<>();
    static boolean isExist = false;

    private CurrencyManipulatorFactory(){

    }

    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode){
        isExist = false;
        CurrencyManipulator current;

        if (map.containsKey(currencyCode))
            return map.get(currencyCode);
        else {
            current = new CurrencyManipulator(currencyCode);
            map.put(currencyCode, current);
            return current;
        }
    }
}
