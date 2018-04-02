package com.leeway.imperium.finalyearproject;


import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * A simple {@link Fragment} subclass.
 */
public class PredictionFragment extends Fragment implements View.OnClickListener {

    private TextView predictionPercentage;
    private TextView colourCodeText;
    private CheckBox damSimulationCheckbox;
    private String predictionResult;
    private Button updateButton;

    protected View mView;


    public PredictionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_prediction, container, false);
        this.mView = view;

        // Inflate the layout for this fragment
        predictionPercentage = (TextView) mView.findViewById(R.id.predictionPercentage);
        colourCodeText = (TextView) mView.findViewById(R.id.colourCodeText);
        damSimulationCheckbox = (CheckBox) mView.findViewById(R.id.damSimulationCheckBox);
        updateButton = (Button) mView.findViewById(R.id.predictButton);

        new JSONAsyncTask().execute();

        updateButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.predictButton :
                makePrediction(mView);
                break;
        }
    }

    public void makePrediction(View v) {
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
                URL url = new URL("http://connect.bakguicraft.com/test");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                int code = urlConnection.getResponseCode();
                System.out.println(code);
                try {
                    predictionResult = readStream(urlConnection.getInputStream());
                    Log.v("OUTPUT", predictionResult);
                    predictionResult = predictionResult.replaceAll("[^-?0-9]+", " ");
                    Log.v("OUTPUT", predictionResult);
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
            predictionPercentage.setText(predictionResult + "%");
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
