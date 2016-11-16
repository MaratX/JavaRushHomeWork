package com.javarush.test.myProgect.AnonimusClass;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HMF on 15.11.2016.
 */
public class Swetcher
{
    private List <Electrocity> electrocitys = new ArrayList<>();

    public void addElectricity(Electrocity e){
        electrocitys.add(e);
    }
    public void removeElectrocity(Electrocity e){
        electrocitys.remove(e);
    }

    public void swithOn(){
        for(Electrocity e : electrocitys)
            e.electroOn();
    }

}
