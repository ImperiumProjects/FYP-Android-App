package com.example.imperium.finalyearproject;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Config;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

public class PredictionActivity extends AppCompatActivity {

    private TextView predictionPercentage;
    private TextView colourCodeText;
    private ArrayList<String> students;
    private JSONArray result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prediction);

        predictionPercentage = (TextView) findViewById(R.id.predictionPercentage);
        colourCodeText = (TextView) findViewById(R.id.colourCodeText);

        new JSONAsyncTask().execute();

        int num = Integer.parseInt((String)predictionPercentage.getText());
        if(num < 30){
            colourCodeText.setTextColor(Color.parseColor("#06840a"));
            colourCodeText.setText("Green ");
        } else if (num >= 30 & num < 80){
            colourCodeText.setTextColor(Color.parseColor("#edb10e"));
            colourCodeText.setText("Amber ");
        }
        else{
            colourCodeText.setTextColor(Color.parseColor("#c90c0c"));
            colourCodeText.setText("Red ");
        }
    }

    public void updatePrediction() {
        new JSONAsyncTask().execute();
    }

    class JSONAsyncTask extends AsyncTask<String, Void, Boolean> {


        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected Boolean doInBackground(String... urls) {
            try {
                Log.d("HTTP", "Making http request");
                URL url = new URL("http://connect.bakguicraft.com:5000/prediction");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    Log.d("HTTP", urlConnection.getResponseMessage());
                } catch (IOException e) {
                    System.out.println(e);
                } finally {
                    urlConnection.disconnect();
                }
                return true;

            } catch (Exception e) {
                Log.d("HTTP", e.toString());
                System.out.println(e);
            }

            /*try {
                Log.d("HTTP", "Making php request");
                URL url = new URL("http://connect.bakguicraft.com:5000/php_python");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    Log.d("HTTP", urlConnection.getResponseMessage());
                    while(in.available() > 0){
                        char c = (char)in.read();
                        Log.d("OUTPUT", "Char: " + c);
                    }
                } catch (IOException e) {
                    System.out.println(e);
                } finally {
                    urlConnection.disconnect();
                }
                return true;


            } catch (Exception e) {
                Log.d("HTTP", e.toString());
                System.out.println(e);
            }*/
            return false;
        }

        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
        }
    }
}
