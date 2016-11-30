package com.javarush.test.level34.lesson15.big01.controller;

import com.javarush.test.level34.lesson15.big01.view.View;
import com.javarush.test.level34.lesson15.big01.model.Model;

/**
 * Created by HMF on 30.11.2016.
 */
public class Controller
{
    private View view;
    private Model model;

    public Controller()
    {
        this.view = new View(this);
        this.model = new Model();
        this.view.init();
    }

    public static void main(String... args){

    }
}
