package com.example.universities_info;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.content.Intent;
import android.net.Uri;
import org.json.JSONArray;
import org.json.JSONException;



public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        String University = getIntent().getStringExtra("unievrity");
        String domain = getIntent().getStringExtra("domains");
        String webpage = getIntent().getStringExtra("webpage");

        String domainnames = "";
        try {
            JSONArray domains = new JSONArray(domain);
            for (int i = 0; i < domains.length(); i++) {
                if (i == domains.length() - 1) {
                    domainnames = domainnames + domains.getString(i);
                }
                else {
                    domainnames = domainnames + domains.getString(i) + ", ";
                }
            }
        }

        catch (JSONException e) {
            e.printStackTrace();
        }

        TextView textView = findViewById(R.id.mainTextView);
        textView.setText("Here are the details of " + University + ":");

        TextView nameTV = findViewById(R.id.nameTextView);
        nameTV.setText("Name: " + University);

        TextView domainTV = findViewById(R.id.domainTextView);
        domainTV.setText("Domains: " + domainnames);

        Button okbutton = findViewById(R.id.button2);
        okbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button visitbutton = findViewById(R.id.button);
        visitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (webpage != null && !webpage.isEmpty()) {
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(webpage));
                    startActivity(i);
                }
            }
        });

    }
}