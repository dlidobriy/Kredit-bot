package com.example.kreditbot.bot;

import com.example.kreditbot.bot.LanguagesService.TelegramServiceLanguageKIRILImpl;
import com.example.kreditbot.bot.LanguagesService.TelegramServiceLanguageRUSImpl;
import com.example.kreditbot.bot.LanguagesService.TelegramServiceLanguageUZImpl;
import com.example.kreditbot.bot.constants.ConstantKiril;
import com.example.kreditbot.bot.constants.ConstantRus;
import com.example.kreditbot.bot.constants.ConstantUzb;
import com.example.kreditbot.entity.User;
import com.example.kreditbot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

import static com.example.kreditbot.bot.State.*;
import static com.example.kreditbot.bot.constants.ConstantKiril.*;
import static com.example.kreditbot.bot.constants.ConstantRus.*;
import static com.example.kreditbot.bot.constants.ConstantUzb.*;

@Component
@RequiredArgsConstructor
public class KreditBot extends TelegramLongPollingBot {
    @Value("${telegram_bot_username}")
    String username;
    @Value("${telegram_bot_botToken}")
    String BotToken;

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return BotToken;
    }


    final UserRepository userRepository;
    final TelegramServiseImpl telegramServise;
    final TelegramServiceLanguageUZImpl telegramServiceUzb;
    final TelegramServiceLanguageRUSImpl telegramServiceRUS;
    final TelegramServiceLanguageKIRILImpl telegramServiceKIRIL;


    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {

        User currentUser;


        if (update.hasMessage()) {
            Optional<User> optionalUser = userRepository.findByChatId(String.valueOf(update.getMessage().getChatId()));
            Message message = update.getMessage();
            if (message.hasText()) {
                if (message.getText().equals("/start")) {
                    if (optionalUser.isPresent()) {
                        currentUser = optionalUser.get();
                        currentUser.setState(START);
                        userRepository.save(currentUser);
                    } else {
                        currentUser = new User();
                        currentUser.setChatId(String.valueOf(update.getMessage().getChatId()));
                        currentUser.setState(START);
                        userRepository.save(currentUser);
                    }
                    execute(telegramServise.welcome(update));
                } else {
                    currentUser = optionalUser.get();
                    switch (currentUser.getState()) {
                        case State.START:
                            switch (update.getMessage().getText()) {
                                case ConstantUzb.LanguageUzb:
                                    currentUser.setState(REGISTER);
                                    currentUser.setLang(ConstantUzb.LanguageUzb);
                                    userRepository.save(currentUser);
                                    execute(telegramServiceUzb.registerPhoneNumber(update));
                                    break;
                                case ConstantRus.LanguageRus:
                                    currentUser.setState(REGISTER);
                                    currentUser.setLang(ConstantRus.LanguageRus);
                                    userRepository.save(currentUser);
                                    execute(telegramServiceRUS.registerPhoneNumber(update));
                                    break;
                                case ConstantKiril.LanguageKiril:
                                    currentUser.setState(REGISTER);
                                    currentUser.setLang(ConstantKiril.LanguageKiril);
                                    userRepository.save(currentUser);
                                    execute(telegramServiceKIRIL.registerPhoneNumber(update));
                                    break;
                                default:
                                    execute(telegramServise.welcome(update));
                            }
                            break;
                        case REGISTER:
                            String fullname = update.getMessage().getText();
                            if (fullname.length() > 3 && fullname.length() <= 30) {
                                switch (currentUser.getLang()) {
                                    case ConstantUzb.LanguageUzb:
                                        currentUser.setFullName(fullname);
                                        currentUser.setState(State.MENU);
                                        userRepository.save(currentUser);
                                        execute(telegramServiceUzb.registerName(update));
                                        break;
                                    case ConstantRus.LanguageRus:
                                        currentUser.setFullName(fullname);
                                        currentUser.setState(State.MENU);
                                        userRepository.save(currentUser);
                                        execute(telegramServiceRUS.registerName(update));
                                        break;
                                    case ConstantKiril.LanguageKiril:
                                        currentUser.setFullName(fullname);
                                        currentUser.setState(State.MENU);
                                        userRepository.save(currentUser);
                                        execute(telegramServiceKIRIL.registerName(update));
                                        break;
                                }
                            }
                            break;
                        case MENU:
                            switch (currentUser.getLang()) {
                                case ConstantUzb.LanguageUzb:
                                    currentUser.setState(MENU_CATEGORY);
                                    userRepository.save(currentUser);
                                    execute(telegramServiceUzb.menu(update));
                                    break;
                                case ConstantRus.LanguageRus:
                                    currentUser.setState(MENU_CATEGORY);
                                    userRepository.save(currentUser);
                                    execute(telegramServiceRUS.menu(update));
                                    break;
                                case ConstantKiril.LanguageKiril:
                                    currentUser.setState(MENU_CATEGORY);
                                    userRepository.save(currentUser);
                                    execute(telegramServiceKIRIL.menu(update));
                                    break;
                            }
                            break;
                        case MENU_CATEGORY:
                            switch (update.getMessage().getText()) {
                                //Uzb
                                case ShareMenuButtonCategory:
                                    execute(telegramServiceUzb.category(update));
                                    break;
                                case ShareMenuButtonUzbOrder:
                                    execute(telegramServiceUzb.order(update));
                                    break;
                                case ShareMenuButtonUzbSavatcha:
                                    execute(telegramServiceUzb.savatcha(update));
                                    break;
                                case ShareMenuButtonUzbProfile:
                                    execute(telegramServiceUzb.profil(update));
                                    break;
                                    //Rus
                                case ShareMenuButtonRus:
                                    execute(telegramServiceRUS.category(update));
                                    break;
                                case ShareMenuButtonRusOrder:
                                    execute(telegramServiceRUS.order(update));
                                    break;
                                case ShareMenuButtonRusSavatcha:
                                    execute(telegramServiceRUS.savatcha(update));
                                    break;
                                case ShareMenuButtonRusProfile:
                                    execute(telegramServiceRUS.profil(update));
                                    break;
                                    //Kiril
                                case ShareMenuButtonKiril:
                                    execute(telegramServiceKIRIL.category(update));
                                    break;
                                case ShareMenuButtonKirilOrder:
                                    execute(telegramServiceKIRIL.order(update));
                                    break;
                                case ShareMenuButtonKirilSavatcha:
                                    execute(telegramServiceKIRIL.savatcha(update));
                                    break;
                                case ShareMenuButtonKirilProfile:
                                    execute(telegramServiceKIRIL.profil(update));
                                    break;

                            }

                    }
                }
            } else if (message.hasContact()) {
                Contact contact = message.getContact();
                currentUser = optionalUser.get();
                switch (currentUser.getLang()) {
                    case ConstantUzb.LanguageUzb:
                        currentUser.setPhone(contact.getPhoneNumber());
                        userRepository.save(currentUser);
                        execute(telegramServiceUzb.registerName(update));
                        break;
                    case ConstantRus.LanguageRus:
                        currentUser.setPhone(contact.getPhoneNumber());
                        userRepository.save(currentUser);
                        execute(telegramServiceRUS.registerName(update));
                        break;
                    case ConstantKiril.LanguageKiril:
                        currentUser.setPhone(contact.getPhoneNumber());
                        userRepository.save(currentUser);
                        execute(telegramServiceKIRIL.registerName(update));
                        break;
                }
            }
        }
    }

}




