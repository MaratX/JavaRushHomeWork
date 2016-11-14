package com.javarush.test.level28.lesson15.big01;

import com.javarush.test.level28.lesson15.big01.model.Provider;

import java.util.ArrayList;

/**
 * Created by HMF on 14.11.2016.
 */
public class Controller
{
    private ArrayList<Provider> providers = new ArrayList<Provider>();

    public Controller(Provider... pro)
    {
        if(pro != null){
            for(Provider p : pro){
                providers.add(p);
            }
        }else {
            new IllegalArgumentException();
        }
    }

    @Override
    public String toString()
    {
        return "Controller{" +
                "providers=" + providers +
                '}';
    }
}
