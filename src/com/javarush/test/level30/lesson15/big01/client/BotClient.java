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

            //	С помощью метода sendTextMessage() отправь сообщение с текстом
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");

            // Вызови реализацию clientMainLoop() родительского класса
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
                        case "дата":
                            dateTimeformat = "d.MM.YYYY";
                            break;
                        case "день":
                            dateTimeformat = "d";
                            break;
                        case "месяц":
                            dateTimeformat = "MMMM";
                            break;
                        case "год":
                            dateTimeformat = "YYYY";
                            break;
                        case "время":
                            dateTimeformat = "H:mm:ss";
                            break;
                        case "час":
                            dateTimeformat = "H";
                            break;
                        case "минуты":
                            dateTimeformat = "m";
                            break;
                        case "секунды":
                            dateTimeformat = "s";
                            break;
                    }
                    if (dateTimeformat != null) {
                        String reply = String.format("Информация для %s: %s",
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
        //Он должен создавать и возвращать объект класса BotSocketThread
        return new BotSocketThread();
    }

    @Override
    protected boolean shouldSentTextFromConsole() {
        //Он должен всегда возвращать false. Мы не хотим, чтобы бот отправлял текст введенный в консоль.
        return false;
    }

    @Override
    protected String getUserName() {
        // метод должен генерировать новое имя бота, например: date_bot_XX, где XX – любое число от 0 до 99.
        // Этот метод должен возвращать каждый раз новое значение, на случай, если на сервере захотят зарегистрироваться несколько ботов, у них должны быть разные имена.
        if (botsCounter == 99) {
            botsCounter = 0;
        }

        return "date_bot_" + botsCounter++;

    }
}