package bot.currency;

import bot.dao.CurrencyDao;
import bot.entity.CurrencyEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.lang.reflect.Type;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.List;

@Repository
public class CurrencyTaker {
    @Autowired
    Gson gson;
    @Autowired
    CurrencyDao currencyDao;
    public void takeCurrencyFromNBRB() throws IOException {
        File fileJson = new File("./resources/currency/currency.json");
        JSONArray json = readJsonFromUrl("http://www.nbrb.by/API/ExRates/Rates?Periodicity=0");
        System.out.println(json.toString());
        FileWriter fileWriter= new FileWriter(fileJson);
       fileWriter.write(json.toString());
        fileWriter.flush();
        Type collectionType = new TypeToken<Collection<Currency>>(){}.getType();
        Collection<Currency> enums = gson.fromJson(String.valueOf(json), collectionType);

        List<CurrencyEntity> forDelete = currencyDao.getAll();
        for (CurrencyEntity item: forDelete) {
            currencyDao.delete(item);
        }
        for (Currency item: enums) {
            CurrencyEntity currencyEntity = new CurrencyEntity();
            currencyEntity.setId(item.getCur_ID());
            currencyEntity.setCurAbbreviation(item.getCur_Abbreviation());
            currencyEntity.setCurName(item.getCur_Name());
            currencyEntity.setCurOfficialRate(item.getCur_OfficialRate());
            currencyEntity.setCurScale(item.getCur_Scale());
            currencyDao.persist(currencyEntity);
        }
    }

    public static JSONArray readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
//           String jsonText1 = jsonText.replaceAll("[\\[\\]]", "");
            JSONArray json = new JSONArray(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

}
