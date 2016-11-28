package com.javarush.test.level32.lesson15.big01;

import javax.swing.text.html.HTMLDocument;
import java.io.File;

/**
 * Created by HMF on 25.11.2016.
 */
public class Controller
{
    private View view;
    private HTMLDocument document;
    private File currentFile;


    public void init(){};

    public static void main(String... args){
        //Создавать объект представления
        View view = new View();
        //Создавать контроллер, используя представление
        Controller controller = new Controller(view);
        //Устанавливать у представления контроллер
        view.setController(controller);
        //Инициализировать представление
        view.init();
        //Инициализировать контроллер. Контроллер должен инициализироваться после представления
        controller.init();

    }


    public Controller(View view)
    {
        this.view = view;
    }

    public void exit() {
        System.exit(0);
    }
}
