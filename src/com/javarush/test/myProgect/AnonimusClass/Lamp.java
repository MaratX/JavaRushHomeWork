package com.javarush.test.myProgect.AnonimusClass;

/**
 * Created by HMF on 15.11.2016.
 */
public class Lamp implements Electrocity
{
    @Override
    public void electroOn()
    {
        System.out.println("Lamp On");
    }
}
