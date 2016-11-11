package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by HMF on 09.11.2016.
 */
public class ConsoleHelper
{
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public void writeMessage(String message){
        System.out.println(message);
    }

    public String readString() throws IOException{
        return reader.readLine();
    }
    public List<Dish> getAllDishesForOrder() throws Exception{

        return null;
    }
}
