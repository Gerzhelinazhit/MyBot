package bot;

import bot.converter.CurrencyConverter;
import bot.currency.CurrencyTaker;
import bot.dao.ClsQuestDao;
import bot.entity.ClsQuestEntity;
import bot.calendar.CalendarUtil;
import bot.config.Config;
import bot.converter.UserConverter;
import bot.dao.ClsAnswerDao;
import bot.dao.UserDao;
import bot.entity.ClsAnswerEntity;
import bot.notes.NotesFunctional;
import bot.notes.NotesUtil;
import bot.replyMenu.MenuUtil;
import bot.quiz.ResultFunctional;
import bot.quiz.QuestionGeneration;
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
    @Autowired
    private CurrencyConverter currencyConverter;
    @Autowired
    private NotesFunctional notesFunctional;
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private ResultFunctional resultFunctional;

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



//---------------------------/START/------------------------------------------------
            if (message_text.equals("/start")) {

                User userInfo = update.getMessage().getFrom();
                System.out.println(update.getMessage().getChatId());
                System.out.println(userInfo);

                userConverter.getUserInfo(userInfo);

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
                resultFunctional.increaseResult(chatId);
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }


// ------------------------- Конвертер валют -----------------------------------
            else if (message_text.equals(MenuUtil.CONVERTER)) {
                SendMessage message = new SendMessage()
                        .setChatId(chatId)
                        .setText(MenuUtil.CONVERTER);


                message.setText(currencyConverter.getCurrency());
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
// ------------------------- Обновление курса -----------------------------------
            else if (message_text.equals(MenuUtil.UPDATE_CURRENCY)) {
                SendMessage message = new SendMessage()
                        .setChatId(chatId)
                        .setText(MenuUtil.UPDATE_CURRENCY);

                try {
                    currencyTaker.takeCurrencyFromNBRB();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                message.setText("Курс обновлен.");
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
// ------------------------- Заметки -----------------------------------
            else if (message_text.equals(MenuUtil.NOTES)) {
                SendMessage message = new SendMessage()
                        .setChatId(chatId)
                        .setText(MenuUtil.NOTES);

                NotesUtil notesUtil = new NotesUtil();
                InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
                inlineKeyboardMarkup.setKeyboard(notesUtil.generateMenu());

                message.setReplyMarkup(inlineKeyboardMarkup);
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

            }
 // ------------------------- ДОБАВЛЕНИЕ ЗАМЕТКИ -----------------------------------
            else if (message_text.contains("+")) {
                SendMessage message = new SendMessage()
                        .setChatId(chatId)
                        .setText("Заметка добавлена");
                 notesFunctional.addNotes(chatId,message_text.replaceAll("[+]",""));

                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }


 // ------------------------- Топ Викторины -----------------------------------
            } else if(message_text.equals(MenuUtil.QUIZ_TOP)){
                SendMessage message = new SendMessage()
                        .setChatId(chatId)
                        .setText(resultFunctional.getResult());
                try {
                    execute(message);
                }catch (TelegramApiException e){
                    e.printStackTrace();
                }
            }

            else {
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
            } else if (call_data.equals("Очистить")) {


                SendMessage new_message = new SendMessage()
                        .setChatId(chatId)
                        .setText("Заметки очищены");
                notesFunctional.ClearNotes(chatId);
                try {
                    execute(new_message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else if (call_data.equals("Показать")) {

                String notes = (notesFunctional.getNotes(chatId));
                SendMessage new_message = new SendMessage()
                        .setChatId(chatId)
                        .setText("Ваши заметки: \n" + notes );

                try {
                    execute(new_message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }


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
