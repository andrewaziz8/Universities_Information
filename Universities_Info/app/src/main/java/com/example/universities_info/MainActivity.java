package com.example.universities_info;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.ArrayList;

import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.view.View;
import android.content.Intent;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] countryNames = getResources().getStringArray(R.array.country_list);
        int[] countryImages = {
                R.drawable.egypt,
                R.drawable.us,
                R.drawable.canada,
                R.drawable.france,
                R.drawable.germany,
                R.drawable.italy,
                R.drawable.uk,
                R.drawable.spain
        };

        CountryAdapter adapter = new CountryAdapter(this, countryNames, countryImages);

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String clickedCountry = countryNames[position];

                Intent i = new Intent(MainActivity.this, SecondActivity.class);

                i.putExtra("country", clickedCountry);

                startActivity(i);
            }
        });
    }
}