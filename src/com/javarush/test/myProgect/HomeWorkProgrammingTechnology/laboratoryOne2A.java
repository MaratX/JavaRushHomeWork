package com.javarush.test.myProgect.HomeWorkProgrammingTechnology;

import java.util.ArrayList;

/**
 * Created by HMF on 16.11.2016.
 */
public class laboratoryOne2A
{
    private int initialMatrix [] = {2,4,7,89,1,23,63,98,9};




    public static void main(String... args){
        ArrayList<Integer> list = new ArrayList<>();

        list.add(2);
        list.add(4);
        list.add(1);
        list.add(9);
        list.add(8);

        laboratoryOne2A l = new laboratoryOne2A();

        ArrayList<Integer> ResultList = l.sort2A(list);
        for(int i : ResultList){
            System.out.println(i);
        }


    }


    public ArrayList<Integer> sort2A(ArrayList<Integer> list1){
        ArrayList<Integer> list = list1;
        for(int i = 0; i < list.size() - 1; i++){
            int y = list.get(i);
            int position = 0;
            for(int j = 1; j < list.size(); j++){
                if(y > list.get(j)){
                    y = list.get(j);
                    position = j;
                }
            }
            if(y != list.get(i)){
                int p = list.get(i);
                list.set(i, list.get(position));
                list.set(position, (Integer) list.get(p));
            }
        }



        return list;
    }











    public int[] sort(int [] innerMatrix){
        int pop [] = innerMatrix;

        for(int i = 0; i < innerMatrix.length; i++){
            for(int j = i + 1; j < pop.length; j++){
                if(pop[j] < pop[i]){
                    pop[i] = innerMatrix[j];
                    pop[j] = innerMatrix[i];
                }
            }
        }

        return pop;
    }

    public int[] getInitialMatrix()
    {
        return initialMatrix;
    }

    public void setInitialMatrix(int[] initialMatrix)
    {
        this.initialMatrix = initialMatrix;
    }
}
