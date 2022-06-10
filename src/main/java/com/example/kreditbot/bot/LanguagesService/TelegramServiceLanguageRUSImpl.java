package com.example.kreditbot.bot.LanguagesService;

import com.example.kreditbot.bot.constants.ConstantKiril;
import com.example.kreditbot.bot.constants.ConstantRus;
import com.example.kreditbot.bot.constants.ConstantUzb;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TelegramServiceLanguageRUSImpl implements TelegramLanguageService{
    @Override
    public SendMessage  registerPhoneNumber(Update update) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(update.getMessage().getChatId()));
        message.setText(ConstantRus.ShareContactRus);

        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setResizeKeyboard(true);
        markup.setOneTimeKeyboard(true);
        markup.setSelective(true);

        List<KeyboardRow> rows = new ArrayList<>();
        KeyboardRow row1 = new KeyboardRow();


        KeyboardButton button1 = new KeyboardButton(ConstantRus.ShareContactButtonRus);
        button1.setRequestContact(true);
        row1.add(button1);
        rows.add(row1);
        markup.setKeyboard(rows);
        message.setReplyMarkup(markup);


        return message;
    }

    @Override
    public SendMessage registerName(Update update) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(update.getMessage().getChatId()));
        message.setText(ConstantRus.ShareNameRus);

        return message;
    }

    @Override
    public SendMessage menu(Update update) {

        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(update.getMessage().getChatId()));
        message.setText(ConstantRus.MenuRus);

        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setResizeKeyboard(true);
        markup.setOneTimeKeyboard(true);
        markup.setSelective(true);

        List<KeyboardRow> rows = new ArrayList<>();
        KeyboardRow row1 = new KeyboardRow();

        KeyboardButton button1 = new KeyboardButton(ConstantRus.ShareMenuButtonRus);
        KeyboardButton button2 = new KeyboardButton(ConstantRus.ShareMenuButtonRusOrder);
        KeyboardButton button3 = new KeyboardButton(ConstantRus.ShareMenuButtonRusSavatcha);
        KeyboardButton button4 = new KeyboardButton(ConstantRus.ShareMenuButtonRusProfile);

        row1.add(button1);
        row1.add(button2);
        row1.add(button3);
        row1.add(button4);

        rows.add(row1);

        markup.setKeyboard(rows);

        message.setReplyMarkup(markup);


        return null;
    }

    @Override
    public SendMessage category(Update update) {
        return null;
    }

    @Override
    public SendMessage order(Update update) {
        return null;
    }

    @Override
    public SendMessage savatcha(Update update) {
        return null;
    }

    @Override
    public SendMessage profil(Update update) {
        return null;
    }
}
