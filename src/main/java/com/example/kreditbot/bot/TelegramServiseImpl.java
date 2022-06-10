package com.example.kreditbot.bot;

import com.example.kreditbot.bot.constants.ConstantKiril;
import com.example.kreditbot.bot.constants.ConstantRus;
import com.example.kreditbot.bot.constants.ConstantUzb;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TelegramServiseImpl implements TelegramService {
    @Override
    public SendMessage welcome(Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
        sendMessage.setText("Salom " + update.getMessage().getFrom().getFirstName() + " " + (update.getMessage().getFrom().getLastName() == null ? "" : update.getMessage().getFrom().getLastName()));


        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setResizeKeyboard(true);
        markup.setOneTimeKeyboard(true);
        markup.setSelective(true);


        List<KeyboardRow> rows = new ArrayList<>();
        KeyboardRow row1 = new KeyboardRow();

        KeyboardButton button1 = new KeyboardButton(ConstantUzb.LanguageUzb);
        KeyboardButton button2 = new KeyboardButton(ConstantRus.LanguageRus);
        KeyboardButton button3 = new KeyboardButton(ConstantKiril.LanguageKiril);

        row1.add(button1);
        row1.add(button2);
        row1.add(button3);
        rows.add(row1);
        markup.setKeyboard(rows);

        sendMessage.setReplyMarkup(markup);

        return sendMessage;
    }
}
