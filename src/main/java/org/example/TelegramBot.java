package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramBot extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        return System.getenv("BOT_NAME");
    }

    @Override
    public String getBotToken() {
        return System.getenv("BOT_TOKEN");
    }

    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()){
            Long chatId = update.getMessage().getChatId();
            String message = update.getMessage().getText();

            switch(message) {
                case "/start":
                    sendGreetingMessage(chatId);
                    break;
                default:
                    break;
            }
        }
    }

    private void sendGreetingMessage(long chatID) {
        String message = "Tysiachi - это бот, который помогает со сдачей \"Тысяч\". \n" +
                "Он может помочь выбрать статью, перевести ее, а также сделать словарь.";
        sendMessage(chatID, message);
    }

    private void sendMessage(long chatID, String messageToSend){
        SendMessage message = SendMessage
                .builder()
                .chatId(String.valueOf(chatID))
                .text(messageToSend)
                .build();
        try {
            execute(message);
        }
        catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}
