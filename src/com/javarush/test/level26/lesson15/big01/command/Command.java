package com.javarush.test.level26.lesson15.big01.command;

import com.javarush.test.level26.lesson15.big01.exception.InterruptOperationException;

/**
 * Created by HMF on 24.11.2016.
 */
interface Command {
    void execute() throws InterruptOperationException;
}