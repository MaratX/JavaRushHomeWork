package com.home.stream;

import com.javarush.test.level24.lesson02.task02.Solution;
import sun.util.resources.LocaleData;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by HMF on 23.11.2016.
 */
public class ConveyorMethodsStreams
{


    public static void main(String... args)
    {
        ArrayList<String> list = new  ArrayList<String>();
        long l = System.currentTimeMillis();
        for(int i = 0; i <  1000000; i++){
            list.add(String.valueOf(new Random(12).nextInt()));
        }


        System.out.println(System.currentTimeMillis() - l);
    }

}