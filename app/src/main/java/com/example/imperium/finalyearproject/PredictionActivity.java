package com.example.imperium.finalyearproject;

import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PredictionActivity extends AppCompatActivity {

    private TextView predictionPercentage;
    private TextView colourCodeText;
    private CheckBox damSimulationCheckbox;
    private String predictionResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prediction);

        predictionPercentage = (TextView) findViewById(R.id.predictionPercentage);
        colourCodeText = (TextView) findViewById(R.id.colourCodeText);
        damSimulationCheckbox = (CheckBox) findViewById(R.id.damSimulationCheckBox);

        new JSONAsyncTask().execute();
    }

    public void updatePrediction(View view) {
        if(damSimulationCheckbox.isChecked()){
            new JSONAsyncTask().execute();
        }
        else{
            new JSONAsyncTask().execute();
        }
    }

    class JSONAsyncTask extends AsyncTask<String, Void, Boolean> {


        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected Boolean doInBackground(String... urls) {
            try {
                //Log.d("HTTP", "Making http request");
                URL url = new URL("http://connect.bakguicraft.com:5000/test");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    predictionResult = readStream(urlConnection.getInputStream());
                    predictionResult = predictionResult.replaceAll("[^-?0-9]+", " ");
                    predictionResult = predictionResult.replaceAll("\\s+","");
                    Log.v("OUTPUT", predictionResult);
                } catch (IOException e) {
                    System.out.println(e);
                } finally {
                    urlConnection.disconnect();
                }
                return true;

            } catch (Exception e) {
                System.out.println(e);
            }
            return false;
        }

        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            predictionPercentage.setText(predictionResult);
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
    }

    private String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuffer response = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response.toString();
    }
}
