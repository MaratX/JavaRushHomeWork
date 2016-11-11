package com.javarush.test.level18.lesson05.task02;

/* Подсчет запятых
С консоли считать имя файла
Посчитать в файле количество символов ',', количество вывести на консоль
Закрыть потоки. Не использовать try-with-resources

Подсказка: нужно сравнивать с ascii-кодом символа ','
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream reader1 = new FileInputStream(reader.readLine());


        int j = 0;
        while (reader1.available() > 0){
            if(reader1.read() == ','){j++;}
        }
        reader.close();
        reader1.close();

        System.out.println(j);
    }

}
