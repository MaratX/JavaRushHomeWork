package com.javarush.test.myProgect.AnonimusClass;

/**
 * Created by HMF on 15.11.2016.
 */
public class app
{
    public static void main(String... args){
        Swetcher s = new Swetcher();
        Radio r = new Radio();
        s.addElectricity(new Lamp());
        s.addElectricity(r);
        s.swithOn();
        s.removeElectrocity(r);
        s.swithOn();
        s.addElectricity(
                ()-> System.out.println("Cool")
        );
        s.swithOn();

    }
}
