package com.javarush.test.level30.lesson15.big01.client;


import com.javarush.test.level30.lesson15.big01.Connection;
import com.javarush.test.level30.lesson15.big01.ConsoleHelper;
import com.javarush.test.level30.lesson15.big01.Message;
import com.javarush.test.level30.lesson15.big01.MessageType;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by HMF on 24.11.2016.
 */
public class Client {

    protected Connection connection;
    private volatile boolean clientConnected = false;


    /** PSVM Client **/
    public static void main(String[] args) {

        Client client = new Client();
        client.run();
    }


    /** Methods **/
    /** run **/
    public void run() {

        // ��������� ����� �������� ����� � ������� ������ getSocketThread
        SocketThread socketThread = getSocketThread();
        // �������� ��������� ����� ��� daemon, ��� ����� ��� ����, ����� ��� ������
        // �� ��������� ��������������� ����� ��������� �������������.
        socketThread.setDaemon(true);
        // ��������� ��������������� �����
        socketThread.start();

        // ��������� ������� ����� �������, ���� �� �� ������� ����������� �� ������� ������
        try {
            synchronized (this) {
                this.wait();
            }
        } catch (InterruptedException e) {
            ConsoleHelper.writeMessage("������");
            return;
        }

        //����� ����, ��� ����� �������� �����������, ������� �������� clientConnected
        if (clientConnected) {
            ConsoleHelper.writeMessage("���������� �����������. ��� ������ �������� ������� 'exit'.");

            //�������� ��������� � ������� ���� ������ ���������. ���� ����� ������� ������� 'exit', �� ����� �� �����
            while (clientConnected) {
                String message;
                if (!(message = ConsoleHelper.readString()).equals("exit")) {
                    if (shouldSentTextFromConsole()) {
                        sendTextMessage(message);
                    }
                } else {
                    return;
                }
            }
        }
        else {
            ConsoleHelper.writeMessage("��������� ������ �� ����� ������ �������.");
        }
    }


    /** ������ ��������� ���� ������ ������� � ������� ��������� ��������**/
    protected String getServerAddress() {

        ConsoleHelper.writeMessage("������� ����� �������: ");
        return ConsoleHelper.readString();
    }


    /** ������ ����������� ���� ����� ������� � ���������� ��� **/
    protected int getServerPort() {

        ConsoleHelper.writeMessage("������� ���� �������: ");
        return ConsoleHelper.readInt();
    }


    /** ������ ����������� � ���������� ��� ������������ **/
    protected String getUserName() {

        ConsoleHelper.writeMessage("������� ��� ������������: ");
        return ConsoleHelper.readString();
    }


    protected boolean shouldSentTextFromConsole() {

        return true;
    }


    /** ������ ��������� � ���������� ����� ������ ������ SocketThread **/
    protected SocketThread getSocketThread() {

        return new SocketThread();
    }


    /**  ������� ����� ��������� ���������, ��������� ���������� ����� � ���������� ��� ������� ����� ���������� connection **/
    protected void sendTextMessage(String text) {

        try {
            connection.send(new Message(MessageType.TEXT, text));

        } catch (IOException e) {
            ConsoleHelper.writeMessage("������ ��������");
            clientConnected = false;
        }
    }


    /** SocketThread **/
    public class SocketThread extends Thread {

        /** Methods **/
        public void run() {

            try {
                // ������ ����� ������ ������ java.net.Socket c �������� ������� � �����
                Socket socket = new Socket(getServerAddress(), getServerPort());

                // ������ ������ ������ Connection, ��������� �����
                Client.this.connection = new Connection(socket);


                clientHandshake();
                clientMainLoop();


            } catch (IOException e) {
                notifyConnectionStatusChanged(false);
            } catch (ClassNotFoundException e) {
                notifyConnectionStatusChanged(false);
            }

        }


        /** ���� ����� ����� ������������� ������� ���� ��������� ��������� ������� **/
        protected void clientMainLoop() throws IOException, ClassNotFoundException {

            while (true) {

                // � ����� �������� ���������, ��������� ���������� connection
                Message message = connection.receive();

                switch (message.getType()) {

                    // ���� ��� ��������� ��������� (��� TEXT), ��������� ��� � ������� ������ processIncomingMessage()
                    case TEXT:
                        processIncomingMessage(message.getData());
                        break;

                    // ���� ��� ��������� � ����� USER_ADDED, ��������� ��� � ������� ������ informAboutAddingNewUser()
                    case USER_ADDED:
                        informAboutAddingNewUser(message.getData());
                        break;

                    // ���� ��� ��������� � ����� USER_REMOVED, ��������� ��� � ������� ������ informAboutDeletingNewUser()
                    case USER_REMOVED:
                        informAboutDeletingNewUser(message.getData());
                        break;

                    default:
                        throw new IOException("Unexpected MessageType");
                }
            }
        }


        /** clientHandshake **/
        protected void clientHandshake() throws IOException, ClassNotFoundException {

            while (true) {

                // � ����� �������� ���������, ��������� ���������� connection
                Message message = connection.receive();

                switch (message.getType()) {

                    // 	���� ��� ����������� ��������� NAME_REQUEST (������ �������� ���)
                    case NAME_REQUEST: {

                        // ��������� ���� ����� ������������ � ������� ������ getUserName()
                        // ������� ����� ��������� � ����� USER_NAME � ��������� ������, ��������� ��������� �������.
                        String userName = getUserName();
                        connection.send(new Message(MessageType.USER_NAME, userName));
                        break;
                    }

                    // ���� ��� ����������� ��������� NAME_ACCEPTED (������ ������ ���)
                    case NAME_ACCEPTED: {

                        // ������ ������ ������ ��� �������, ����� �� ���� �������� �������� ������, �� ����� ����� ����.
                        // ������ ��� � ������� ������ notifyConnectionStatusChanged(), ������� � ���� true. ����� ����� ����� �� ������.
                        notifyConnectionStatusChanged(true);
                        return;
                    }

                    default: {
                        throw new IOException("Unexpected MessageType");
                    }
                }
            }
        }


        /** ������ �������� ����� message � ������� **/
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
        }


        /** ������ �������� � ������� ���������� � ���, ��� �������� � ������ userName ������������� � ���� **/
        protected void informAboutAddingNewUser(String userName) {
            ConsoleHelper.writeMessage("�������� " + userName + " ������������� � ����");
        }


        /**  ������ �������� � �������, ��� �������� � ������ userName ������� ��� **/
        protected void informAboutDeletingNewUser(String userName) {
            ConsoleHelper.writeMessage("�������� " + userName + " ������� ���");
        }


        /** ������������� �������� ���� clientConnected ������ Client � ������������ �
         ���������� ����������.
         ��������� (���������� ���������) �������� ����� ������ Client **/
        protected void notifyConnectionStatusChanged(boolean clientConnected) {


            Client.this.clientConnected = clientConnected;

            synchronized (Client.this) {
                Client.this.notify();
            }
        }
    }
}