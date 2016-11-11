package com.javarush.test.level26.lesson15.big01;

import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

/**
 * Created by HMF on 07.11.2016.
 */
public class ConsoleHelper
{



    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "common_en");

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message)
    {
        System.out.println(message);
    }



    public static String readString() throws InterruptOperationException
    {
        String message = "";
        try
        {
            message = reader.readLine();
            if (message.equalsIgnoreCase(res.getString("operation.EXIT")))
                throw new InterruptOperationException();
        }
        catch (IOException ignored)
        {
        }
        return message;
    }
}
