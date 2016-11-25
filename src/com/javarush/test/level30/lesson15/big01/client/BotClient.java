package com.javarush.test.level30.lesson15.big01.client;

import com.javarush.test.level30.lesson15.big01.ConsoleHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class BotClient extends Client{
    private static final String WELCOME_TEXT = "������ ������. � ���. ������� �������: ����, ����, �����, ���, �����, ���, ������, �������.";

    private static volatile Set<String> botNames = new HashSet<>();
    //18.2.
    public class BotSocketThread extends SocketThread {

        //19.1.
        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {

            sendTextMessage(WELCOME_TEXT);
            super.clientMainLoop();
        }

        //19.2.
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

    //18.3.
    //18.3.1.
    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    //18.3.2.
    @Override
    protected boolean shouldSentTextFromConsole() {
        return false;
    }

    //18.3.3.
    @Override
    protected String getUserName() {
        String botName;
        if (botNames.size() >= 100) throw new RuntimeException("����� ����� ��������� ���������� ������");
        do {
            botName = String.format("date_bot_%02d", new Random().nextInt(100));
        } while (botNames.contains(botName));
        botNames.add(botName);
        return botName;
    }

    //18.4.
    public static void main(String[] args) {
        new BotClient().run();
    }
}