package com.leeway.imperium.finalyearproject;


import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
public class AdvancedFragment extends Fragment {

    private TextView riverLevel;
    private TextView tempLevel;
    private TextView tidalLevel;
    private TextView rainLevel;
    private TextView sevenDayLevel;
    private TextView twoWeekLevel;
    private TextView oneMonthLevel;

    private String results;
    private String[] parts;

    protected View mView;

    public AdvancedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_advanced, container, false);
        this.mView = view;

        riverLevel = (TextView) mView.findViewById(R.id.riverData);
        tempLevel = (TextView) mView.findViewById(R.id.temperatureData);
        tidalLevel = (TextView) mView.findViewById(R.id.tidalData);
        rainLevel = (TextView) mView.findViewById(R.id.rainfallData);
        sevenDayLevel = (TextView) mView.findViewById(R.id.sevenDayData);
        twoWeekLevel = (TextView) mView.findViewById(R.id.twoWeekData);
        oneMonthLevel = (TextView) mView.findViewById(R.id.oneMonthData);

        new JSONAsyncTask().execute();

        // Inflate the layout for this fragment
        return view;
    }

    class JSONAsyncTask extends AsyncTask<String, Void, Boolean> {

        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected Boolean doInBackground(String... urls) {
            try {
                //Log.d("HTTP", "Making http request");
                URL url = new URL("http://connect.bakguicraft.com/forecasts");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                int code = urlConnection.getResponseCode();
                System.out.println(code);
                try {
                    results = readStream(urlConnection.getInputStream());
                    Log.v("OUTPUT", results);
                    results = results.replaceAll("[^-?0-9]+", " ");
                    Log.v("OUTPUT", results);
                    parts = results.split(" ");
                    //results = results.replaceAll("\\s+","");
                    //Log.v("OUTPUT", predictionResult);
                    Log.v("OUTPUT", parts[2]);
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
            riverLevel.setText(parts[1] + "." + parts[2] + "m");
            tempLevel.setText(parts[3] + " C");
            tidalLevel.setText(parts[4] + "." + parts[5] + "m");
            rainLevel.setText(parts[6]);
            sevenDayLevel.setText(parts[7] + "%");
            twoWeekLevel.setText(parts[8] + "%");
            oneMonthLevel.setText(parts[9] + "%");
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
