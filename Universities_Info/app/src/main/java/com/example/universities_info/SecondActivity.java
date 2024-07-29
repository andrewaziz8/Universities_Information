package com.example.universities_info;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.io.InputStream;
import java.io.InputStreamReader;
import android.os.AsyncTask;
import android.util.Log;
import android.app.ProgressDialog;
import android.widget.TextView;
import java.util.Map;
import java.util.HashMap;



public class SecondActivity extends AppCompatActivity {
    ProgressDialog progressDialog;
    Map<String, String> domainmap = new HashMap<>();
    Map<String, String> webpagemap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        String Country = getIntent().getStringExtra("country");

        // Update the TextView with the country name
        TextView textView = findViewById(R.id.textView);
        textView.setText("Here are the list of Universities in " + Country + ":");

        if ("United States".equals(Country)) {
            Country = "United+States";
        }

        if ("United Kingdom".equals(Country)) {
            Country = "United+Kingdom";
        }

        new fetchUniversityNames().execute(Country);
    }

    private class fetchUniversityNames extends AsyncTask<String, Void, ArrayList<String>> {
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(SecondActivity.this);
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
            progressDialog.show();

        }

        protected ArrayList<String> doInBackground (String... params){
            String cntry = params[0];
            ArrayList<String> universityNames = new ArrayList<String>();

            try {
                URL url = new URL("http://universities.hipolabs.com/search?country=" + cntry);

                Log.d("SecondActivity", url.toString());

                HttpURLConnection http = (HttpURLConnection) url.openConnection();
                http.setRequestMethod("GET");
                http.connect();

                InputStream inputStream = http.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                String line = "";
                String data = "";

                while ((line = bufferedReader.readLine()) != null) {
                    data = data + line;
                }

                if (!data.isEmpty()) {
                    JSONArray jsonArray = new JSONArray(data);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String name = jsonObject.getString("name");
                        JSONArray webpages = jsonObject.getJSONArray("web_pages");
                        String web = webpages.getString(0);
                        String domains = jsonObject.getString("domains");
                        domainmap.put(name, domains);
                        webpagemap.put(name, web);

                        universityNames.add(name);
                    }
                }

                inputStream.close();
                bufferedReader.close();
                http.disconnect();
            }

            catch (Exception e) {
                e.printStackTrace();
            }

            return universityNames;

        }

        protected void onPostExecute(ArrayList<String> universityNames) {
            super.onPostExecute(universityNames);

            if(progressDialog != null && progressDialog.isShowing()){
                progressDialog.dismiss();
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(SecondActivity.this, android.R.layout.simple_list_item_1, universityNames);
            ListView listView = (ListView) findViewById(R.id.listView2);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String clickeduni = universityNames.get(position);

                    Intent i = new Intent(SecondActivity.this, ThirdActivity.class);

                    i.putExtra("unievrity", clickeduni);
                    i.putExtra("domains", domainmap.get(clickeduni));
                    i.putExtra("webpage", webpagemap.get(clickeduni));

                    startActivity(i);
                }
            });
        }

    }

}