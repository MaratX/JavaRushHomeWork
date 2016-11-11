package com.javarush.test.level19.lesson05.task01;

/* Четные байты
Считать с консоли 2 имени файла.
Вывести во второй файл все байты с четным индексом.
Пример: второй байт, четвертый байт, шестой байт и т.д.
Закрыть потоки ввода-вывода.
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream inputStream = new FileInputStream(reader.readLine());
        FileOutputStream outputStream = new FileOutputStream(reader.readLine());
        int i = 0;
        while (inputStream.available() > 0){
            i++;
            if(i%2 == 0){
                outputStream.write(inputStream.read());
            }else {
                inputStream.read();
            }
        }
        reader.close();
        inputStream.close();
        outputStream.close();
    }
}
