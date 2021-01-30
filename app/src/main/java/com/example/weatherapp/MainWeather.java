package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.time.LocalDate;

public class MainWeather extends AppCompatActivity {
    String name="";
    Double lat,longt;
    RequestQueue requestQueue;
    TextView _city,_country,_temp,_humidity,_pressure,_wind,_weatherText;
    ImageView _weatherIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestQueue=Volley.newRequestQueue(getApplicationContext());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_weather);
        lat=getIntent().getDoubleExtra("lat",0.0);
        longt=getIntent().getDoubleExtra("longt",0.0);

        _city=(TextView)findViewById(R.id.city);
        _country=(TextView)findViewById(R.id.country);
        _temp=(TextView)findViewById(R.id.temp);
        _humidity=(TextView)findViewById(R.id.humidity);
        _pressure=(TextView)findViewById(R.id.pressure);
        _wind=(TextView)findViewById(R.id.wind);
        _weatherText=(TextView)findViewById(R.id.weatherText);
        _weatherIcon=(ImageView)findViewById(R.id.weatherIcon);

        Log.d("Main", String.valueOf(lat));
        Log.d("Main", String.valueOf(longt));

        name=getIntent().getStringExtra("cityName");
        Log.d("Main",""+name);
        getWeather();
    }

    public void getWeather(){
        if(name!=null){
        String URL="https://api.weatherapi.com/v1/current.json?key=9913027004844c97bab191144200812&q="+name+"";
        Log.d("Main","This is name method");
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Log.d("Main",String.valueOf(response));
                JSONObject location,current,condition;
                try {
                    location=response.getJSONObject("location");
                    String cityName=location.getString("name");
                    String countryName=location.getString("country");
                    _city.setText(cityName);
                    _country.setText(countryName);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    current=response.getJSONObject("current");
                    String temp=current.getString("temp_c");
                    condition=current.getJSONObject("condition");
                    String weatherText=condition.getString("text");
                    String iconCode=condition.getString("code");
                    String wind_kph=current.getString("wind_kph");
                    String pressure=current.getString("pressure_mb");
                    String humidity=current.getString("humidity");

                    _temp.setText(temp);
                    _weatherText.setText(weatherText);
                    _wind.setText(wind_kph);
                    _pressure.setText(pressure);
                    _humidity.setText(humidity);
                    changeIcon(iconCode);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
        Log.d("Main",String.valueOf(error));
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
    else if(lat!=0.0 && longt!=0.0){
            String URL="https://api.weatherapi.com/v1/current.json?key=9913027004844c97bab191144200812&q="+lat+","+longt+"";
            Log.d("Main","This is latlong method");
            JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.d("Main",String.valueOf(response));
                    JSONObject location,current,condition;
                    try {
                        location=response.getJSONObject("location");
                        String cityName=location.getString("name");
                        String countryName=location.getString("country");
                        _city.setText(cityName);
                        _country.setText(countryName);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    try {
                        current=response.getJSONObject("current");
                        String temp=current.getString("temp_c");
                        condition=current.getJSONObject("condition");
                        String iconCode=condition.getString("code");
                        String weatherText=condition.getString("text");
                        String wind_kph=current.getString("wind_kph");
                        String pressure=current.getString("pressure_mb");
                        String humidity=current.getString("humidity");

                        _temp.setText(temp);
                        _weatherText.setText(weatherText);
                        _wind.setText(wind_kph);
                        _pressure.setText(pressure);
                        _humidity.setText(humidity);
                        changeIcon(iconCode);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("Main",String.valueOf(error));
                }
            });
            requestQueue.add(jsonObjectRequest);
    }
    }

    public void changeIcon(String iconCode){

        switch (iconCode){
            //Sunny
            case "1000":{
                _weatherIcon.setImageResource(R.drawable.sun);
                break;
            }
            //Partly clouds
            case "1003":{
                _weatherIcon.setImageResource(R.drawable.partlycloud);
                break;
            }
            //Cloudy
            case "1006":{
                _weatherIcon.setImageResource(R.drawable.cloudy);
                break;
            }
            //Overcast
            case "1009":{
                _weatherIcon.setImageResource(R.drawable.overcast);
                break;
            }
            //Mist
            case "1030":{
                _weatherIcon.setImageResource(R.drawable.mist);
                break;
            }
            //Patchy rain possible
            case "1063":{
                _weatherIcon.setImageResource(R.drawable.patchyrain);
                break;
            }
            //Patchy snow possible
            case "1066": {
                _weatherIcon.setImageResource(R.drawable.patchsnow);
                break;
            }

            //Thundery outbreaks possible
            case "1087":{
                _weatherIcon.setImageResource(R.drawable.thunder);
                break;
            }

            //Fog
            case "1135":{
                _weatherIcon.setImageResource(R.drawable.fog);
                break;
            }
            //Light drizzle
            case "1153":{
                _weatherIcon.setImageResource(R.drawable.lightdrizzle);
                break;
            }
            //Light rain
            case "1183":{
                _weatherIcon.setImageResource(R.drawable.lightrain);
                break;
            }
            //Moderate rain
            case "1189":{
                _weatherIcon.setImageResource(R.drawable.moderaterain);
                break;
            }
            //Heavy rain
            case "1195":{
                _weatherIcon.setImageResource(R.drawable.heavyrain);
                break;
            }


        }

    }
}


