package bot.replyMenu;

import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class MenuUtil {
    public static String CALENDAR = "Календарь";
    public static String WEATHER = "Погода";
    public static String QUIZ = "Викторина";
    public static String CONVERTER = "Конвертер валют";
    public static String NOTES = "Заметки";
public static List<KeyboardRow> generateMenu(){
        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow row = new KeyboardRow();
        row.add(CALENDAR);
        row.add(WEATHER);
        keyboard.add(row);

        row = new KeyboardRow();
        row.add(NOTES);
        row.add(CONVERTER);
        keyboard.add(row);

        row = new KeyboardRow();
        row.add(QUIZ);
        keyboard.add(row);
        return keyboard;
    }
}
