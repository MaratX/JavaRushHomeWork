package com.javarush.test.level30.lesson15.big01.client;

import com.javarush.test.level30.lesson15.big01.ConsoleHelper;
import com.javarush.test.level30.lesson15.big01.Message;
import com.javarush.test.level30.lesson15.big01.MessageType;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by FarAway on 12.03.2016.
 */

//18.1.
public class BotClient extends Client {

    // Bots counter
    private static int botsCounter = 0;


    /** PSVM **/
    public static void main(String args []) {
        new BotClient().run();
    }


    /** inner class */
    public class BotSocketThread extends SocketThread {


        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {

            //	� ������� ������ sendTextMessage() ������� ��������� � �������
            sendTextMessage("������ ������. � ���. ������� �������: ����, ����, �����, ���, �����, ���, ������, �������.");

            // ������ ���������� clientMainLoop() ������������� ������
            super.clientMainLoop();

        }


        @Override
        protected void processIncomingMessage(String message) {

            // ������� � ������� ����� ����������� ��������� message
            ConsoleHelper.writeMessage(message);

            // �������� �� message ��� ����������� � ����� ���������. ��� ��������� ": "
            String senderName = "";
            String senderMessageText;

            if (message.contains(": ")) {
                senderName = message.substring(0, message.indexOf(": "));
                senderMessageText = message.substring(message.indexOf(": ") + 2);
            }
            else {
                senderMessageText = message;
            }


            SimpleDateFormat format = null;
            // ��������� ����� � ����������� �� ������ ��������� ���������. ���� ����� ���������:
            if ("����".equalsIgnoreCase(senderMessageText)) {
                format = new SimpleDateFormat("d.MM.YYYY");
            }
            else if ("����".equalsIgnoreCase(senderMessageText)) {
                format = new SimpleDateFormat("d");
            }
            else if ("�����".equalsIgnoreCase(senderMessageText)) {
                format = new SimpleDateFormat("MMMM");
            }
            else if ("���".equalsIgnoreCase(senderMessageText)) {
                format = new SimpleDateFormat("YYYY");
            }
            else if ("�����".equalsIgnoreCase(senderMessageText)) {
                format = new SimpleDateFormat("H:mm:ss");
            }
            else if ("���".equalsIgnoreCase(senderMessageText)) {
                format = new SimpleDateFormat("H");
            }
            else if ("������".equalsIgnoreCase(senderMessageText)) {
                format = new SimpleDateFormat("m");
            }
            else if ("�������".equalsIgnoreCase(senderMessageText)) {
                format = new SimpleDateFormat("s");
            }

            if (format != null)
            {
                sendTextMessage("���������� ��� " + senderName + ": " + format.format(Calendar.getInstance().getTime()));
            }

        }

    }

    /** methods **/

    @Override
    protected SocketThread getSocketThread() {
        //�� ������ ��������� � ���������� ������ ������ BotSocketThread
        return new BotSocketThread();
    }

    @Override
    protected boolean shouldSentTextFromConsole() {
        //�� ������ ������ ���������� false. �� �� �����, ����� ��� ��������� ����� ��������� � �������.
        return false;
    }

    @Override
    protected String getUserName() {
        // ����� ������ ������������ ����� ��� ����, ��������: date_bot_XX, ��� XX � ����� ����� �� 0 �� 99.
        // ���� ����� ������ ���������� ������ ��� ����� ��������, �� ������, ���� �� ������� ������� ������������������ ��������� �����, � ��� ������ ���� ������ �����.
        if (botsCounter == 99) {
            botsCounter = 0;
        }

        return "date_bot_" + botsCounter++;

    }
}