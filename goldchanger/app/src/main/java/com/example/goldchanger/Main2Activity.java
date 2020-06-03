package com.example.goldchanger;

import androidx.appcompat.app.AppCompatActivity;

import android.net.http.HttpResponseCache;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class Main2Activity extends AppCompatActivity {
    TextView dolar,euro,textView;
    CheckBox dolar1,euro1;
    EditText miktar;
    int a;
    Double miktar1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        dolar = findViewById(R.id.textView8);
        euro    = findViewById(R.id.textView6);


        dolar1 = findViewById(R.id.checkBox5);
        euro1= findViewById(R.id.checkBox6);
        textView  = findViewById(R.id.textView);
        miktar= findViewById(R.id.editText3);
         a = 0;




    }

    public void button1(View view) {
        miktar1 = (Double.parseDouble(miktar.getText().toString()));
        if (dolar1.isChecked()){
            a=2;
        }
        if (euro1.isChecked()){
            a=3;
        }
        DownloadData downloadData = new DownloadData();
        String url = "http://data.fixer.io/api/latest?access_key=2e6d32cf7587d036e4364bb49fda54c4&format=1";
        downloadData.execute(url);

        DownloadData1 downloadData1 = new DownloadData1();
        String url1 = "https://free.currconv.com/api/v7/convert?q=USD_TRY&compact=ultra&apiKey=2fd637f07b5a0a753e0c";
        downloadData1.execute(url1);
    }


    private class DownloadData extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... strings) {

            String result = "";
            URL url;
            HttpURLConnection httpURLConnection;

            try {

                url = new URL(strings[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

                int data = inputStreamReader.read();

                while (data > 0) {

                    char character = (char) data;
                    result += character;

                    data = inputStreamReader.read();

                }


                return result;

            } catch (Exception e) {
                return null;
            }

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {

                JSONObject jsonObject = new JSONObject(s);
                String rates = jsonObject.getString("rates");

                JSONObject jsonObject1 = new JSONObject(rates);
                String turkishlira = jsonObject1.getString("TRY");
                euro.setText("EURO: " + turkishlira);
                if (a==3){
                    double eurotry = Double.parseDouble(turkishlira);
                   double impact = (eurotry*miktar1);
                   String impactstring = new Double(impact).toString();
                   textView.setText(impactstring);
                }


            } catch (Exception e) {

            }


        }
    }
    private class DownloadData1 extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... strings) {

            String result = "";
            URL url;
            HttpURLConnection httpURLConnection;

            try {

                url = new URL(strings[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

                int data = inputStreamReader.read();

                while (data > 0) {

                    char character = (char) data;
                    result += character;

                    data = inputStreamReader.read();

                }


                return result;

            } catch (Exception e) {
                return null;
            }

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {

                JSONObject jsonObject = new JSONObject(s);
                String base = jsonObject.getString("USD_TRY");
                dolar.setText("DOLAR:"+ base);
                if (a==2){
                    double usdtry = Double.parseDouble(base);
                    Double impact = (usdtry*miktar1);
                    String impactstring1 = new Double(impact).toString() ;
                    textView.setText(impactstring1);
                }







            } catch (Exception e) {

            }


        }
    }


}
