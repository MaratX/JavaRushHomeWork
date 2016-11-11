package com.javarush.test.level18.lesson03.task05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;




/* Сортировка байт
Ввести с консоли имя файла
Считать все байты из файла.
Не учитывая повторений - отсортировать их по байт-коду в возрастающем порядке.
Вывести на экран
Закрыть поток ввода-вывода

Пример байт входного файла
44 83 44

Пример вывода
44 83
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream inputStream = new FileInputStream(reader.readLine());
        ArrayList<Byte> list = new ArrayList<Byte>();
        while (inputStream.available() > 0){
            byte j = (byte) inputStream.read();
            int p = 0;
            for(int i = 0; i < list.size(); i++){
                if(list.get(i) == j){
                    p = 1;
                }
            }
            if(p == 0){
                list.add(j);
            }
        }
        Collections.sort(list);
        for(byte i : list){
            System.out.println(i);
        }
    }
}
