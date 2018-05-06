package bot;

import bot.config.ApplicationConfiguration;
import bot.currency.CurrencyTaker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;

import java.io.IOException;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        SpringApplication.run(ApplicationConfiguration.class, args);

    }

}