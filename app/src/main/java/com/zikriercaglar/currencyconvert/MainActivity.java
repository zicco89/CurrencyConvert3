package com.zikriercaglar.currencyconvert;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView usdText = findViewById(R.id.usdText);
        TextView eurText = findViewById(R.id.eurText);
        TextView gbpText = findViewById(R.id.gbpText);
        TextView rubText = findViewById(R.id.rubText);

    }
    public void getRates(View view){
        try {
            DownloadData downloadData = new DownloadData();
            String url = "http://data.fixer.io/api/latest?access_key=4642f8b50507248f6b929cd172aa896a&format=1";

            downloadData.execute(url);
        }catch (Exception e){
        }
    }

    private class DownloadData extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            String result = "";
            URL url;
            HttpURLConnection httpURLConnection;

            try{
                url = new URL(strings[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

                int data = inputStreamReader.read();

                while(data > 0){
                    char character = (char)data;
                    result += character;

                    data = inputStreamReader.read();
                }

                return result;

            }catch (Exception e){
                return null;
            }

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            System.out.println("Alınan Data:\n"+s);
        }
    }

}
