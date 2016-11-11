package com.javarush.test.level18.lesson10.bonus01;

/* Шифровка
Придумать механизм шифровки/дешифровки

Программа запускается с одним из следующих наборов параметров:
-e fileName fileOutputName
-d fileName fileOutputName
где
fileName - имя файла, который необходимо зашифровать/расшифровать
fileOutputName - имя файла, куда необходимо записать результат шифрования/дешифрования
-e - ключ указывает, что необходимо зашифровать данные
-d - ключ указывает, что необходимо расшифровать данные
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) {

        try{
            if(args[0] == "-e"){
                coder(args[1], args[2], -2);
            }else{
                coder(args[1], args[2], 2);
            }
        }catch (Exception e){
            System.out.println("Exception : " + e);
        }

    }
    public static void  coder(String input, String output, int i) throws Exception{

        FileInputStream inputStream = new FileInputStream(input);
        FileOutputStream outputStream = new FileOutputStream(output);

        while (inputStream.available() > 0){
            outputStream.write(inputStream.read() + i);
        }
    }

}
