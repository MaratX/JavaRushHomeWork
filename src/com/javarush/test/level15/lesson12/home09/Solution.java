package com.javarush.test.level15.lesson12.home09;

/* Парсер реквестов
Считать с консоли URl ссылку.
Вывести на экран через пробел список всех параметров (Параметры идут после ? и разделяются &, например, lvl=15).
URL содержит минимум 1 параметр.
Если присутствует параметр obj, то передать его значение в нужный метод alert.
alert(double value) - для чисел (дробные числа разделяются точкой)
alert(String value) - для строк

Пример 1
Ввод:
http://javarush.ru/alpha/index.html?lvl=15&view&name=Amigo
Вывод:
lvl view name

Пример 2
Ввод:
http://javarush.ru/alpha/index.html?obj=3.14&name=Amigo
Вывод:
obj name
double 3.14
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try
        {
            ArrayList<Double> d = new ArrayList<Double>();
            ArrayList<String> ss = new ArrayList<String>();
            ArrayList<String> stro = new ArrayList<String>();
            String stroka = reader.readLine();
            String[] aStroka = stroka.split("\\?");
            String[] a2Stroka = aStroka[1].split("&");
            for(String s : a2Stroka){
                String[] a3Stroka = s.split("=");
                if(a3Stroka[0].toString().equals("obj")){
                    stro.add(a3Stroka[0].toString());
                    if(a3Stroka[1] != null){
                        try
                        {
                            d.add(Double.parseDouble(a3Stroka[1]));

                        }catch (Exception e){
                            ss.add(a3Stroka[1].toString());
                        }
                    }
                }else {
                    stro.add(a3Stroka[0].toString());
                }

            }
            for(int i = 0; i < stro.size(); i++){
                if(i < stro.size() -1)
                {
                    System.out.print(stro.get(i) + " ");
                }
                else {
                    System.out.println(stro.get(i));
                }
            }
            for(Double dd : d){
                alert(dd);
            }
            for(String sss : ss){
                alert(sss);
            }
        }catch (Exception e){
            System.out.println("INFO_: " + e );
        }
    }

    public static void alert(double value) {
        System.out.println("double " + value);
    }

    public static void alert(String value) {
        System.out.println("String " + value);
    }
}
