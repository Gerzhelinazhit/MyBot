package bot.currency;

import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CurrencyTaker {
    public void getCurrency() throws IOException {
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet("http://www.nbrb.by/API/ExRates/Rates?Periodicity=0");
        HttpResponse response = client.execute(request);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String line = "";
            line = rd.readLine();
            System.out.println(line);
        JSONObject parsedObject = new JSONObject(line);
        int cur_id = parsedObject.getInt("Cur_ID");


    }

}
