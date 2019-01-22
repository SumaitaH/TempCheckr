package edu.cuny.qc.cs.tempcheckr;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.net.URLConnection;

import edu.cuny.qc.cs.tempcheckr.activities.Activity2;
import edu.cuny.qc.cs.tempcheckr.model.Weather;
//import android.widget.Toast;

public class fetchData extends AsyncTask<Void, Void, Void>  {
    String data;
    String dataParsed = "";
    String singleParsed = "";
    private String locationText;
    private String typeData;
    private String modifiedURL;
    private List<Weather> mylstWeather;
    static boolean changed = false;
    //private JsonArrayRequest ArrayRequest;
    //public static String data = "";



    fetchData(String getWebURL, String type){
       //apiURL= getUrl(getWebURL, type);
        locationText = getWebURL;
        typeData = type;
        mylstWeather = new ArrayList<>();
        System.out.println("This is location text: " + locationText);
;    }

    @Override
    protected Void doInBackground(Void... voids) {
        //background thread
        try {
            modifiedURL = getUrl(locationText,typeData);
            System.out.println("The modified url is: " + modifiedURL);
            URL website = new URL(modifiedURL);
            //URL website = new URL(url);
            URLConnection connection = website.openConnection();
            BufferedReader in = new BufferedReader( new InputStreamReader(connection.getInputStream(),"UTF8"));

            StringBuilder response = new StringBuilder();
            String inputLine;

            while ((inputLine = in.readLine()) != null)
                response.append(inputLine);

            in.close();
            System.out.println("the length of response is: " + response.length());
            //JSONArray jsonObj=new JSONArray(response.toString());
            JSONObject jsonObject = new JSONObject(response.toString());
            JSONObject dailyForecasts = jsonObject.getJSONObject("dailyForecasts");
            JSONObject forecastLocation = dailyForecasts.getJSONObject("forecastLocation");
            JSONArray forecast = forecastLocation.getJSONArray("forecast");
            System.out.println("forecast length is " + forecast.length());

            for (int i = 0; i < forecast.length(); i++) {

               JSONObject dailyForecast = forecast.getJSONObject(i);
               Weather weather = new Weather();


               weather.setWeekday(dailyForecast.getString("weekday"));
               weather.setDescription(dailyForecast.getString("description"));
               weather.setIconLink(dailyForecast.getString("iconLink"));
               weather.setHighTemp(dailyForecast.getString("highTemperature"));
               weather.setWindSpeed(dailyForecast.getString("lowTemperature"));
               mylstWeather.add(weather);

            }
            System.out.println("mylstweather is " + mylstWeather.size());
        }
        catch(IOException e){
            e.printStackTrace();
        }
        catch(JSONException e){
            e.printStackTrace();
        }


        return null;
    }

//            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//            //create an input stream
//            InputStream inputStream = httpURLConnection.getInputStream();
//            //create a buffered reader
//            BufferedReader bufferedReader  = new BufferedReader(new InputStreamReader(inputStream));
//            String line = "";
//            while(line!=null){
//                line = bufferedReader.readLine();
//                data = data + line;
//            }
            //parsing
            //    try {
            //        JSONArray JA = new JSONArray(data);
            //        for(int i = 0; i < JA.length(); i++) {
            //            JSONObject JO = (JSONObject) JA.get(i);
            //            singleParsed = "Name:" + JO.get("name") + "\n";
            //        }
            //    }catch(JSONException e)
            //    {
            //        e.printStackTrace();
            //    }



    @Override
    protected void onPostExecute(Void aVoid) {
     //after the background task has been done, you ca chance the UI
     //this is how we show to the user
        super.onPostExecute(aVoid);
        //System.out.println("i come here");
        //Activity2.URL_JSON.setText(this.modifiedURL);
        //Activity2.URL_JSON = this.modifiedURL;
        //Activity2.lstWeather = this.mylstWeather;
        //return mylstWeather;
        //Activity2.updateWeatherList(mylstWeather);
    }

    public boolean getChanged(){
        return changed;
    }
    public List getWeatherLst(){
        return mylstWeather;
    }

    public String getUrl(String inputText, String type){
        String webURL = "";
        if(type.equals("city")){
                webURL = "https://weather.cit.api.here.com/weather/1.0/report.json?product=forecast_7days_simple&name=" + inputText + "&app_id=83joEwu57v5M6pSLpP2E&app_code=vEsuM73ubIJT3KDfXE9_bQ";
        }
        else if(type.equals("zipcode")){
            webURL = "https://weather.cit.api.here.com/weather/1.0/report.json?product=forecast_7days_simple&name=" + inputText + "&app_id=83joEwu57v5M6pSLpP2E&app_code=vEsuM73ubIJT3KDfXE9_bQ";

        }
        return webURL;
    }

}
