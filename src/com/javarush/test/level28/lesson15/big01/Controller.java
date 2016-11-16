package com.javarush.test.level28.lesson15.big01;

import com.javarush.test.level28.lesson15.big01.model.Provider;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by HMF on 14.11.2016.
 */
public class Controller
{
    private Provider [] providers;

    public Controller(Provider... pro)
    {
        if(pro.length == 0){
            throw new IllegalArgumentException();
        }
        providers = pro;

    }

    @Override
    public String toString()
    {
        return "Controller{" +
                "providers=" + Arrays.toString(providers) +
                '}';
    }
}
