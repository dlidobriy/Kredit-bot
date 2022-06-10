package com.example.kreditbot.bot.LanguagesService;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface TelegramLanguageService {
   SendMessage registerPhoneNumber(Update update);
   SendMessage registerName(Update update);
   SendMessage menu(Update update);
   SendMessage category(Update update);
   SendMessage order(Update update);
   SendMessage savatcha(Update update);
   SendMessage profil(Update update);
}
