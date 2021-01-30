package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SearchCityName extends AppCompatActivity {

    private EditText SearchCityName;
    private Button Search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_city_name);

        SearchCityName=(EditText)findViewById(R.id.cityName);
        Search=(Button)findViewById(R.id.search);


        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=SearchCityName.getText().toString();
                Intent intent=new Intent(SearchCityName.this,MainWeather.class);
                intent.putExtra("cityName",name);
                startActivity(intent);
            }

        });
    }
}
