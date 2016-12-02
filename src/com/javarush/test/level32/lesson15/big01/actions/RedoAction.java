package com.javarush.test.level32.lesson15.big01.actions;

/**
 * Created by HMF on 02.12.2016.
 */
import com.javarush.test.level32.lesson15.big01.View;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Класс возврата действия RedoAction
 */
public class RedoAction extends AbstractAction {

    private View view;

    public RedoAction(View view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        view.redo();
    }
}
