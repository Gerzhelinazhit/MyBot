package bot.notes;

import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class NotesUtil {

    public List<List<InlineKeyboardButton>> generateMenu(){
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        List<InlineKeyboardButton> controlsRow = new ArrayList<>();
        controlsRow.add(createButton("Показать","Показать"));
        controlsRow.add(createButton("Очистить","Очистить"));
        keyboard.add(controlsRow);
        return  keyboard;
    }

    private InlineKeyboardButton createButton(String callBack, String text) {
        return new InlineKeyboardButton().setCallbackData(callBack).setText(text);
    }
}
