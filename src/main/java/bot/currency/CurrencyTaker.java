package bot.currency;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

@Repository
public class CurrencyTaker {
    @Autowired
    Gson gson;

    public void getCurrency() throws IOException {
        File fileJson = new File("./resources/currency/currency.json");
    URL url = new URL("http://www.nbrb.by/API/ExRates/Rates?Periodicity=0");
        JSONArray json = readJsonFromUrl("http://www.nbrb.by/API/ExRates/Rates?Periodicity=0");
        System.out.println(json.toString());
    InputStream is = url.openStream();

//        HttpClient client = new DefaultHttpClient();
//
//        HttpGet request = new HttpGet("http://www.nbrb.by/API/ExRates/Rates?Periodicity=0");
//        HttpResponse response = client.execute(request);
//        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
//        String line = "";
//            line = rd.readLine();
//            System.out.println(line);
////        System.out.println(line.replaceAll("[\\[\\]]", ""));
//        String jsonString = gson.toJson(line);
//        FileWriter fileWriter= new FileWriter(fileJson);
//        fileWriter.write(jsonString);
//        fileWriter.flush();
//    JSONObject parsedObject = new JSONObject(line.replaceAll("[\\[\\]]", ""));
//
//        int cur_id = parsedObject.getInt("Cur_ID");
//        System.out.println(cur_id);



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
