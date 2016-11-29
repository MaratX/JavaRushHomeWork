package com.javarush.test.level27.lesson15.big01.kitchen;

import com.javarush.test.level27.lesson15.big01.ConsoleHelper;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by HMF on 29.11.2016.
 */
public class Waitor implements Observer
{

    @Override
    public void update(Observable observable, Object o)
    {
        if (o instanceof Order && observable instanceof Cook)
        {
            Cook cook = (Cook) observable;
            Order order = (Order) o;

            ConsoleHelper.writeMessage(order + " was cooked by " + cook);
        }
    }
}
