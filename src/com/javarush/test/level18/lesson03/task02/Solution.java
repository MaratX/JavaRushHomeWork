package com.javarush.test.level18.lesson03.task02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/* Минимальный байт
Ввести с консоли имя файла
Найти минимальный байт в файле, вывести его на экран.
Закрыть поток ввода-вывода
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int countMin = Byte.MAX_VALUE;
        try{
            String fileName = reader.readLine();
            FileInputStream fileInputStream = new FileInputStream(fileName);
            while (fileInputStream.available() > 0){
                int i = fileInputStream.read();
                if(i < countMin){
                    countMin = i;
                }
            }
            fileInputStream.close();
            reader.close();
            System.out.println(countMin);
        }catch (Exception e){
            System.out.println("INFO: " + e);
        }
    }
}
