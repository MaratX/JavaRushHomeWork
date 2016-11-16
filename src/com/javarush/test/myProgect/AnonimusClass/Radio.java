package com.javarush.test.myProgect.AnonimusClass;

/**
 * Created by HMF on 15.11.2016.
 */
public class Radio implements Electrocity
{
    public void PlayMuzic(){
        System.out.println("Radio On");
    }

    @Override
    public void electroOn()
    {
        PlayMuzic();
    }
}
