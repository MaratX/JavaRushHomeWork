package com.javarush.test.level30.lesson15.big01.client;

import com.javarush.test.level30.lesson15.big01.ConsoleHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

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


                //19.2.1.
                ConsoleHelper.writeMessage(message);
                //19.2.2.
                String[] messageParts = message.split(": ");
                if (messageParts.length == 2) {
                    String messageAuthor = messageParts[0];
                    String messageText = messageParts[1].toLowerCase();
                    String dateTimeformat = null;
                    switch (messageText) {
                        case "����":
                            dateTimeformat = "d.MM.YYYY";
                            break;
                        case "����":
                            dateTimeformat = "d";
                            break;
                        case "�����":
                            dateTimeformat = "MMMM";
                            break;
                        case "���":
                            dateTimeformat = "YYYY";
                            break;
                        case "�����":
                            dateTimeformat = "H:mm:ss";
                            break;
                        case "���":
                            dateTimeformat = "H";
                            break;
                        case "������":
                            dateTimeformat = "m";
                            break;
                        case "�������":
                            dateTimeformat = "s";
                            break;
                    }
                    if (dateTimeformat != null) {
                        String reply = String.format("���������� ��� %s: %s",
                                messageAuthor,
                                new SimpleDateFormat(dateTimeformat).format(Calendar.getInstance().getTime())
                        );
                        sendTextMessage(reply);
                    }
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