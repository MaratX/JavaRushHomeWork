package com.javarush.test.level18.lesson10.home03;

/* Два в одном
Считать с консоли 3 имени файла
Записать в первый файл содержимого второго файла, а потом дописать в первый файл содержимое третьего файла
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        FileOutputStream outputStream = new FileOutputStream(reader.readLine());

        FileInputStream inputStream12 = new FileInputStream(reader.readLine());
        FileInputStream inputStream13 = new FileInputStream(reader.readLine());

        ArrayList<Integer> list = new ArrayList<Integer>();

        while (inputStream12.available() > 0){
            list.add(inputStream12.read());
        }
        while (inputStream13.available() > 0){
            list.add(inputStream13.read());
        }


        for(int i = 0; i < list.size(); i++){
            outputStream.write(list.get(i));
        }

        reader.close();
        inputStream12.close();
        inputStream13.close();
        outputStream.close();
    }
}
