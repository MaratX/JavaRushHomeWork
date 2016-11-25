package com.javarush.test.level30.lesson15.big01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by HMF on 24.11.2016.
 */
public class ConsoleHelper
{
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message){
        System.out.println(message);
    }
    public static String readString(){
        String message;

        while (true) {

            try {
                message = reader.readLine();
                break;

            } catch (IOException e) {
                System.out.println("��������� ������ ��� ������� ����� ������. ���������� ��� ���.");
            }
        }
        return message;

    }

    public static int readInt() {

        int i;

        while (true) {

            try {
                i = Integer.parseInt(readString());
                break;

            } catch (NumberFormatException e) {
                System.out.println("��������� ������ ��� ������� ����� ������. ���������� ��� ���.");
            }
        }
        return i;
    }
}
