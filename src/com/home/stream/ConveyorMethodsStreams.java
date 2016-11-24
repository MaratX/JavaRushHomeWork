package com.home.stream;

import sun.util.resources.LocaleData;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by HMF on 23.11.2016.
 */
public class ConveyorMethodsStreams
{
    private static ArrayList<Integer> listInt = new ArrayList<>();
    private static ArrayList<String> listString = new ArrayList<>();

    static {
        listInt.add(3);
        listInt.add(6);
        listInt.add(1);
        listInt.add(4);
        listInt.add(4);
        listInt.add(4);

        for(int i = 0; i < 1000000; i++)
        {
            listString.add("w");
        }

    }

    public static void main(String... args){
        ConveyorMethodsStreams CMS = new ConveyorMethodsStreams();
        //CMS.StreamFilterCounter(CMS.getListString());
        //CMS.StreamSkipe(CMS.getListInt());
        //CMS.StreamDistinc(CMS.getListString());
        System.out.println();
        CMS.StreamMap(CMS.getListString());

    }

    public void StreamFilterCounter(ArrayList<String> collections){
        System.out.println(collections.stream().filter("p"::equals).count());
    }

    public void StreamSkipe(ArrayList<Integer> col){
        System.out.println(col.stream().skip(3).count());
    }
    public void StreamDistinc(ArrayList<String> col){
        col.stream().distinct().forEach(x -> System.out.print(" " + x));
    }
    public void StreamMap(ArrayList<String> col){
        col.stream().map(x -> x + "_1").forEach(x -> System.out.println(x + " "));
    }




    public static ArrayList<Integer> getListInt()
    {
        return listInt;
    }

    public static void setListInt(ArrayList<Integer> listInt)
    {
        ConveyorMethodsStreams.listInt = listInt;
    }

    public static ArrayList<String> getListString()
    {
        return listString;
    }

    public static void setListString(ArrayList<String> listString)
    {
        ConveyorMethodsStreams.listString = listString;
    }
}
