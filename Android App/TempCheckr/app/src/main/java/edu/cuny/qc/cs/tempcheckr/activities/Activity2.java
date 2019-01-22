package edu.cuny.qc.cs.tempcheckr.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Toast;
import com.android.volley.Request;


import edu.cuny.qc.cs.tempcheckr.R;
import edu.cuny.qc.cs.tempcheckr.adapter.RvAdapter;
import edu.cuny.qc.cs.tempcheckr.model.Weather;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Activity2 extends AppCompatActivity {
    private static RequestQueue requestQueue ;
    private static RecyclerView myrv;
    public List<Weather> lstWeather = new ArrayList<>();
    String URL_JSON;
    private JsonObjectRequest objectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //lstWeather = new ArrayList<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        myrv = findViewById(R.id.rv);
        URL_JSON = getIntent().getStringExtra("URL");
        jsoncall();
    }

    public void jsoncall() {

    JSONObject a = new JSONObject();

    objectRequest = new JsonObjectRequest(Request.Method.GET, URL_JSON, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                System.out.println("i come here");

                JSONObject jsonObject = null;




               // for (int i = 0 ; i<response.length();i++) {

                    //Toast.makeText(getApplicationContext(),String.valueOf(i),Toast.LENGTH_SHORT).show();

                    try {

                        jsonObject = new JSONObject(response.toString());
                        JSONObject dailyForecasts = jsonObject.getJSONObject("dailyForecasts");
                        JSONObject forecastLocation = dailyForecasts.getJSONObject("forecastLocation");
                        JSONArray forecast = forecastLocation.getJSONArray("forecast");
                        System.out.println("forecast length is " + forecast.length());

                        for (int j = 0; j < forecast.length(); j++) {

                            JSONObject dailyForecast = forecast.getJSONObject(j);
                            Weather weather = new Weather();


                            weather.setWeekday(dailyForecast.getString("weekday"));
                            weather.setDescription(dailyForecast.getString("description"));
                            weather.setIconLink(dailyForecast.getString("iconLink"));
                            weather.setHighTemp(dailyForecast.getString("highTemperature"));
                            weather.setWindSpeed(dailyForecast.getString("lowTemperature"));
                            lstWeather.add(weather);

                        }
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                //}


                Toast.makeText(Activity2.this,"Size of Liste "+String.valueOf(lstWeather.size()),Toast.LENGTH_SHORT).show();
                Toast.makeText(Activity2.this, lstWeather.get(1).toString(),Toast.LENGTH_SHORT).show();

                setRvadapter(lstWeather);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        requestQueue = Volley.newRequestQueue(Activity2.this);
        requestQueue.add(objectRequest);
    }



    public void setRvadapter (List<Weather> lst) {

        RvAdapter myAdapter = new RvAdapter(this,lst) ;
        myrv.setLayoutManager(new LinearLayoutManager(this));
        myrv.setAdapter(myAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        //inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);

    }
}