package com.example.kreditbot.bot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface TelegramService {

    SendMessage welcome(Update update);

}
