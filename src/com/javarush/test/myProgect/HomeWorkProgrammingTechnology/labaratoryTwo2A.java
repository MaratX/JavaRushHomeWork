package com.javarush.test.myProgect.HomeWorkProgrammingTechnology;

/**
 * Created by HMF on 17.11.2016.
 */
public class labaratoryTwo2A
{
    public static void main(String... args){
        String [] list = {"one", "two", "three", "four"};
        String s = "two";
        int i = new labaratoryTwo2A().ResultSearsh(s, list);

        if(i >= 0){
            System.out.println("В массиве list найден элемент '"+ s  + "' на позицие : " + (i + 1));
        }else if (i == -1){
            System.out.println("В массиве отсутствует элемент '" +  s + "'");
        }

    }

    public Integer ResultSearsh(String s, String list[]){
        for(int i = 0; i < list.length; i++){
            if(list[i].equals(s)){
                return i;
            }
        }
        return -1;
    }
}
