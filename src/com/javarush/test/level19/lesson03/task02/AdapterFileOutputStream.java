package com.javarush.test.level19.lesson03.task02;

/* Адаптер
Используйте класс AdapterFileOutputStream, чтобы адаптировать FileOutputStream к новому интерфейсу AmigoStringWriter
*/

import java.io.FileOutputStream;
import java.io.IOException;

public class AdapterFileOutputStream implements AmigoStringWriter {

    FileOutputStream _file;
    AdapterFileOutputStream(FileOutputStream file){
        _file = file;
    }

    public void flush() throws IOException{
        _file.flush();
    }

    public void writeString(String s) throws IOException{
        _file.write(s.getBytes());
    }
    public void close() throws IOException{
        _file.close();
    }
}

