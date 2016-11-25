package com.javarush.test.level30.lesson15.big01;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

/**
 * Created by HMF on 24.11.2016.
 */
public class Server
{
    final private static Map<String, Connection> connectionMap = new java.util.concurrent.ConcurrentHashMap<>();

    public static void main(String... args)
    {
        ConsoleHelper.writeMessage("������� ���� �������:");
        int port = ConsoleHelper.readInt();
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            ConsoleHelper.writeMessage("������ ������� �� �����: " + port);
            while (true) {
                Socket socket = serverSocket.accept();
                new Handler(socket).start();
            }
        } catch (IOException e) {
            ConsoleHelper.writeMessage(e.getMessage());
        }
    }


    public static void sendBroadcastMessage(Message message) {

        for (String clientName : connectionMap.keySet()) {
            try {
                connectionMap.get(clientName).send(message);
            } catch (IOException e) {
                ConsoleHelper.writeMessage("�� ���� ��������� ��������� ������� � ������: " + clientName);
            }
        }
    }

    private static class Handler extends Thread {

        private Socket socket;

        public Handler(Socket socket) {
            super();
            this.socket = socket;
        }

        @Override
        public void run() {
            //Task 11.1
            ConsoleHelper.writeMessage("����������� ���������� � ��������� �������� � �������: " +
                    socket.getRemoteSocketAddress());

            //Task 11.2
            Connection connection = null;
            String clientName = null;
            try {
                connection = new Connection(socket);

                //Task 11.3
                clientName = serverHandshake(connection);
                //Task 11.4
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, clientName));

                //Task 11.5
                sendListOfUsers(connection, clientName);
                //Task 11.6
                serverMainLoop(connection, clientName);

            } catch (IOException e) {
                handleHandlerException(e, connection);
            } catch (ClassNotFoundException e) {
                handleHandlerException(e, connection);
            }
            // disconnecting client
            if (clientName != null) {
                connectionMap.remove(clientName);
                sendBroadcastMessage(new Message(MessageType.USER_REMOVED, clientName));
            }

            ConsoleHelper.writeMessage(String.format("���������� � ��������� ������� (%s) �������.", socket.getRemoteSocketAddress()));
        }

        private void handleConnectionExcetion(Exception e) {
            ConsoleHelper.writeMessage("��������� ������ ��� ������� ���������� ���������� � �������� � �������: " +
                    socket.getRemoteSocketAddress() + "%n" +
                    "��� ������: " + e.getClass().getSimpleName() + "%n" +
                    "����� ������: " + e.getMessage());
            try { socket.close(); } catch (IOException e_) { /* NOP */ }
        }

        private void handleHandShakeExcetion(Exception e, Connection connection) {
            ConsoleHelper.writeMessage("��������� ������ ��� \"�����������\" � �������� � �������: " +
                    socket.getRemoteSocketAddress() + "%n" +
                    "��� ������: " + e.getClass().getSimpleName() + "%n" +
                    "����� ������: " + e.getMessage());
            try {connection.close(); socket.close(); } catch (IOException e_) { /* NOP */ }
        }

        private void handleHandlerException(Exception e, Connection connection) {
            ConsoleHelper.writeMessage("��������� ������ ��� ������ ������� � ��������� �������: " +
                    socket.getRemoteSocketAddress() + "%n" +
                    "��� ������: " + e.getClass().getSimpleName() + "%n" +
                    "����� ������: " + e.getMessage());
            try {
                if (connection != null)
                    connection.close();
                socket.close();
            } catch (IOException e_) { /* NOP */ }
        }

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {
            boolean accepted = false;
            String name = null;
            while (!accepted) {
                connection.send(new Message(MessageType.NAME_REQUEST));
                Message message = connection.receive();
                if (message.getType() == MessageType.USER_NAME) {
                    name = message.getData();
                    if (!name.isEmpty() && connectionMap.get(name) == null) {
                        connectionMap.put(name, connection);
                        connection.send(new Message(MessageType.NAME_ACCEPTED));
                        accepted = true;
                    }
                }
            }
            return name;
        }

        private void sendListOfUsers(Connection connection, String userName) throws IOException {
            for (String clientName : connectionMap.keySet()) {
                if (!clientName.equals(userName))
                    connection.send(new Message(MessageType.USER_ADDED, clientName));
            }
        }

        private void serverMainLoop(Connection connection, String userName) throws
                IOException, ClassNotFoundException{
            while (!Thread.currentThread().isInterrupted()) {
                Message message = connection.receive();
                if (message.getType() == MessageType.TEXT) {
                    String messageText = userName + ": " + message.getData();
                    sendBroadcastMessage(new Message(MessageType.TEXT, messageText));
                } else ConsoleHelper.writeMessage(
                        String.format("������! ������������ ��� ��������� (MessageType.%s) �� �������: %s",
                                message.getType().toString() ,userName)
                );
            }
        }
    }



}
