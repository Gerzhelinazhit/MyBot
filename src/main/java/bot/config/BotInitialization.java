package bot.config;


import bot.Bot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

@Component
public class BotInitialization {

 @Autowired
    public BotInitialization(Bot bot, TelegramBotsApi telegramBotsApi) {
        Config.load();
    try{
        telegramBotsApi.registerBot(bot);
    } catch (TelegramApiException ex){
        ex.printStackTrace();
    }

    }
}

