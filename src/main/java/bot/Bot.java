package bot;

import bot.calendar.CalendarUtil;
import bot.config.Config;
import bot.converter.UserConverter;
import bot.currency.CurrencyTaker;
import bot.dao.ClsAnswerDao;
import bot.dao.ClsQuestDao;
import bot.dao.UserDao;
import bot.entity.ClsAnswerEntity;
import bot.entity.ClsQuestEntity;
import bot.replyMenu.MenuUtil;
import bot.victorina.QuestionGeneration;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.List;

import static java.lang.Math.toIntExact;

@Component
public class Bot extends TelegramLongPollingBot {

    private static final String WEATHER_FOR_NOW = "☂ Погода сейчас";
    private LocalDate currentShownDates = new LocalDate();

    @Autowired
    private ClsQuestDao questDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private ClsAnswerDao answerDao;
    @Autowired
    private CurrencyTaker currencyTaker;

    String answer = new String();
    String comment = new String();


    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message_text = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            User user = update.getMessage().getFrom();
            System.out.println(update.getMessage().getChatId());
            System.out.println(user);
            System.out.println(message_text);
            List<ClsQuestEntity> questList = questDao.getAll();
            List<ClsAnswerEntity> answerList = answerDao.getAll();
            try {
                currencyTaker.getCurrency();
            } catch (IOException e) {
                e.printStackTrace();
            }

//---------------------------/START/------------------------------------------------
            if (message_text.equals("/start")) {

                User userInfo = update.getMessage().getFrom();
                System.out.println(update.getMessage().getChatId());
                System.out.println(userInfo);
                UserConverter userConverter = new UserConverter();

                userDao.persist(userConverter.getUserInfo(userInfo));
                SendMessage message = new SendMessage() // Create a message object object
                        .setChatId(chatId)
                        .setText(message_text);
                ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
                keyboardMarkup.setKeyboard(MenuUtil.generateMenu());
                message.setReplyMarkup(keyboardMarkup);
                message.setText("Выберите пункт меню");

                try {
                    execute(message); // Sending our message object to user
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

            }
            // ------------------------- КАЛЕНДАРЬ -----------------------------------
            else if (message_text.equals(MenuUtil.CALENDAR)) {

                SendMessage message = new SendMessage()
                        .setChatId(chatId)
                        .setText(MenuUtil.CALENDAR);
                CalendarUtil calendar = new CalendarUtil();
                currentShownDates = LocalDate.now();

                InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
                inlineKeyboardMarkup.setKeyboard(calendar.generateKeyboard(currentShownDates));
                System.out.println(LocalDate.now());

                message.setReplyMarkup(inlineKeyboardMarkup);
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                calendar.generateKeyboard(LocalDate.now());
                System.out.println(calendar.generateKeyboard(LocalDate.now()));
            }
//TODO make weather feature
            // ------------------------- ПОГОДА -----------------------------------
            else if (message_text.equals(MenuUtil.WEATHER)) {
                SendMessage message = new SendMessage()
                        .setChatId(chatId)
                        .setText(MenuUtil.WEATHER);

            }
            //TODO Make quiz
            // ------------------------- ВИКТОРИНА -----------------------------------
            else if (message_text.equals(MenuUtil.QUIZ) || message_text.equals("Конечно")) {
                SendMessage message = new SendMessage()
                        .setChatId(chatId)
                        .setText(MenuUtil.QUIZ);

                QuestionGeneration questionGeneration = new QuestionGeneration(questList, answerList);
                answer = questionGeneration.getAnswer();
                comment = questionGeneration.getComment();
                message.setText(questionGeneration.getQuestion());
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
//                for (ClsQuestEntity a : questList) {
//                    for (ClsAnswerEntity answ : answerList) {
//                        message.setText(a.getQuestText());
//                        verAQuest = a;
//                        answer=answ;
//                        try {
//                            execute(message);
//                            break;
//                        }
//                        catch (TelegramApiException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    break;
//                }
            }
//---------------------------Проверка правильности ответа-----------------------------
            else if (message_text.equals(answer)) {
                SendMessage message = new SendMessage()
                        .setChatId(chatId)
                        .setText(MenuUtil.QUIZ);
                message.setText("Правильно!  " + "\n" + comment);
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }

            //TODO make converter
// ------------------------- Конвертер валют -----------------------------------
            else if (message_text.equals(MenuUtil.CONVERTER)) {
                SendMessage message = new SendMessage()
                        .setChatId(chatId)
                        .setText(MenuUtil.CONVERTER);

            }
            //TODO make Notes
// ------------------------- Заметки -----------------------------------
            else if (message_text.equals(MenuUtil.NOTES)) {
                SendMessage message = new SendMessage()
                        .setChatId(chatId)
                        .setText(MenuUtil.NOTES);

            } else {
                SendMessage message = new SendMessage() // Create a message object object
                        .setChatId(chatId)
                        .setText(message_text);
                message.setText("Я пока не знаю что ответить");
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }


        /*
        while(m.find()) {
            System.out.println(txt.substring(m.start(), m.end()) + "");
            System.out.println(txt);
        }*/
          /*  switch (txt) {

                case "слава украине": {
                    sendMsg(msg, "Героям Слава!");
                    break;
                }
                case "слава": {
                    sendMsg(msg, "Украине!");
                    break;
                }
                case "рыжий": {
                    sendMsg(msg, "Наш Президент!");
                    break;
                }
                case "/start": {
                    sendMsg(msg, "Слава Украине!");
                    break;
                }
                case "/calendar": {
                    CalendarUtil calendar = new CalendarUtil();
                    long chatId = update.getMessage().getChatId();
                    SendMessage message = new SendMessage()
                            .setChatId(chatId)
                            .setText("You send /calendar");
                    InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
                    inlineKeyboardMarkup.setKeyboard(calendar.generateKeyboard(LocalDate.now()));
                    System.out.println(LocalDate.now());
                    message.setReplyMarkup(inlineKeyboardMarkup);
                    try {
                        execute(message);
                    } catch (TelegramApiException e1) {
                        e1.printStackTrace();
                    }
                    calendar.generateKeyboard(LocalDate.now());
                    System.out.println(calendar.generateKeyboard(LocalDate.now()));


                    break;
                }
                case "погода": {

                    YahooWeatherService service = null;
                    try {
                        service = new YahooWeatherService();
                    } catch (JAXBException e1) {
                        e1.printStackTrace();
                    }
                    Channel channel = null;
                    try {
                        channel = service.getForecast("2460286", DegreeUnit.CELSIUS);
                    } catch (JAXBException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    System.out.println(channel.getWind());
                    sendMsg(msg, channel.getTitle());
                    break;
                }
                default:
                    sendMsg(msg, "Я пока не знаю что ответить");
            }
           /* case "Слава": {
                sendMsg(msg, "");
                break;H
            }*/

        //TODO learn calendar to make notes
        else if (update.hasCallbackQuery()) {
            // Set variables
            String call_data = update.getCallbackQuery().getData();
            long message_id = update.getCallbackQuery().getMessage().getMessageId();
            long chatId = update.getCallbackQuery().getMessage().getChatId();
            System.out.println(call_data);
            if (call_data.equals(">")) {
                InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
                CalendarUtil calendar = new CalendarUtil();

                LocalDate nextMonth = currentShownDates.plusMonths(1).withDayOfMonth(1);
                inlineKeyboardMarkup.setKeyboard(calendar.generateKeyboard(nextMonth));

                EditMessageText new_message = new EditMessageText()
                        .setChatId(chatId)
                        .setMessageId(toIntExact(message_id))
                        .setText("Следующий месяц")
                        .setReplyMarkup(inlineKeyboardMarkup);
                currentShownDates = nextMonth;

                try {
                    execute(new_message);
                } catch (TelegramApiException a) {
                    a.printStackTrace();
                }
            } else if (call_data.equals("<")) {
                InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
                CalendarUtil calendar = new CalendarUtil();

                LocalDate previousMonth = currentShownDates.minusMonths(1).withDayOfMonth(1);
                inlineKeyboardMarkup.setKeyboard(calendar.generateKeyboard(previousMonth));

                EditMessageText new_message = new EditMessageText()
                        .setChatId(chatId)
                        .setMessageId(toIntExact(message_id))
                        .setText("Предыдущий месяц")
                        .setReplyMarkup(inlineKeyboardMarkup);
                currentShownDates = previousMonth;
                try {
                    execute(new_message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }
     /*   if (txt.equals("Слава Украине"||"слава украине"||"Слава украине")){
            sendMsg(msg, "Героям Слава!");
        // Тут будет то, что выполняется при получении сообщения
        if (txt.equals("Слава"))
            sendMsg(msg, "Украине!");
        // Тут будет то, что выполняется при получении сообщения
        if (txt.equals("Рыжий"))
            sendMsg(msg, "Наш Президент!");
        // Тут будет то, что выполняется при получении сообщения
        if (txt.equals("/start"))
            sendMsg(msg, "Слава Украине!");
            // Тут будет то, что выполняется при получении сообщения
        }

        else sendMsg(msg, "Я пока не знаю что ответить");*/


    @SuppressWarnings("deprecation") // Означает то, что в новых версиях метод уберут или заменят
    private void sendMsg(Message msg, String text) {
        SendMessage s = new SendMessage();
        s.setChatId(msg.getChatId()); // Боту может писать не один человек, и поэтому чтобы отправить сообщение, грубо говоря нужно узнать куда его отправлять
        s.setText(text);
        try { //Чтобы не крашнулась программа при вылете Exception
            sendMessage(s);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotToken() {
        return Config.BOT_TOKEN;
        //Токен бота
    }

    @Override
    public String getBotUsername() {
        return Config.BOT_NAME;
        //возвращает юзера
    }

    @Deprecated
    public String grammarChecker(String txt) {

        txt = txt.toLowerCase();
        txt = txt.replaceAll("[\\p{Punct}&&[^/]]", "");
        txt = txt.replaceAll("[\\p{Digit}]", "");
        txt = txt.replaceAll("[№-№]", "");
        txt = txt.replaceAll("[\u20BD-\u20BD]", "");


        return txt;
    }
}
