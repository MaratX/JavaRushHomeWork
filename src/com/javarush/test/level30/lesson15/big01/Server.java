package com.javarush.test.level30.lesson15.big01;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by HMF on 24.11.2016.
 */

public class Server {

    // ���� -��� �������, � �������� - ���������� � ���
    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();


    /** MAIN **/
    public static void main(String[] args) throws IOException {

        ConsoleHelper.writeMessage("������� ���� �������: ");
        int serverPort = ConsoleHelper.readInt();

        try (ServerSocket serverSocket = new ServerSocket(serverPort)) {

            ConsoleHelper.writeMessage("������ �������");

            while (true) {
                //�������
                Socket socket = serverSocket.accept();
                Handler handler = new Handler(socket);
                //��������� handler
                handler.start();
            }
        }

    }


    /** �������� ��������� ��� ���� **/
    public static void sendBroadcastMessage(Message message) {

        try {

            for (Connection connection : connectionMap.values()) {
                connection.send(message);
            }

        } catch (Exception e){
            e.printStackTrace();
            ConsoleHelper.writeMessage("��������� �� ����������");
        }

    }


    /**���������� Handler, � ������� ����� ����������� ����� ����������� � �������� **/
    private static class Handler extends Thread {

        private Socket socket;

        //Constructor
        public Handler(Socket socket) {

            this.socket = socket;
        }


        @Override
        public void run() {

            ConsoleHelper.writeMessage("������������ ���������� � ������� " + socket.getRemoteSocketAddress());
            String clientName = null;
            //������� Connection
            try (Connection connection = new Connection(socket)) {
                //�������� ���������, ��� ����������� ����� ���������� � ��������� �������
                ConsoleHelper.writeMessage("����������� � �����: " + connection.getRemoteSocketAddress());
                //�������� �����, ����������� ����������� � ��������, �������� ��� ������ �������
                clientName = serverHandshake(connection);
                //��������� ���� ���������� ���� ���������� �� ����� ����������������� ��������� (��������� � ����� USER_ADDED)
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, clientName));
                //�������� ������ ��������� � ������������ ����������
                sendListOfUsers(connection, clientName);
                //��������� ������� ���� ��������� ��������� ��������
                serverMainLoop(connection, clientName);


            } catch (IOException e) {
                ConsoleHelper.writeMessage("������ ��� ������ ������� � ��������� �������");
            } catch (ClassNotFoundException e) {
                ConsoleHelper.writeMessage("������ ��� ������ ������� � ��������� �������");
            }

            //����� ���� ��� ��� ���������� ����������, ������� ������ �� connectionMap
            connectionMap.remove(clientName);
            //� ����������� ��������� ��������� �������������
            sendBroadcastMessage(new Message(MessageType.USER_REMOVED, clientName));

            ConsoleHelper.writeMessage("���������� � ��������� ������� �������");

        }

        /** Handshake **/
        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {

            while (true) {
                // ������������ � ��������� ������� ������� ����� ������������
                connection.send(new Message(MessageType.NAME_REQUEST));
                // �������� ����� �������
                Message message = connection.receive();

                // ���������, ��� �������� ������� � ������ ������������
                if (message.getType() == MessageType.USER_NAME) {

                    //������� �� ������ ���, ���������, ��� ��� �� ������
                    if (message.getData() != null && !message.getData().isEmpty()) {

                        // � ������������ � ����� ������ ��� �� ��������� (��������� connectionMap)
                        if (connectionMap.get(message.getData()) == null) {

                            // �������� ������ ������������ � ���������� � ��� � connectionMap
                            connectionMap.put(message.getData(), connection);
                            // ��������� ������� ������� �������������, ��� ��� ��� �������
                            connection.send(new Message(MessageType.NAME_ACCEPTED));

                            // ������� �������� ��� � �������� ������������� ��������
                            return message.getData();
                        }
                    }
                }
            }
        }


        /** �������� ������ ���� ������������� **/
        private void sendListOfUsers(Connection connection, String userName) throws IOException {

            for (String key : connectionMap.keySet()) {
                Message message = new Message(MessageType.USER_ADDED, key);

                if (!key.equals(userName)) {
                    connection.send(message);
                }
            }
        }


        /** ������� ���� ��������� ��������� �������� **/
        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException {

            while (true) {

                Message message = connection.receive();
                // ���� �������� ��������� � ��� ����� (��� TEXT)
                if (message.getType() == MessageType.TEXT) {

                    String s = userName + ": " + message.getData();

                    Message formattedMessage = new Message(MessageType.TEXT, s);
                    sendBroadcastMessage(formattedMessage);
                } else {
                    ConsoleHelper.writeMessage("Error");
                }
            }
        }
    }
}
