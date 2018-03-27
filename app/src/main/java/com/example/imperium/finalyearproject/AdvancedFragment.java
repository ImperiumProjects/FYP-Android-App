package com.example.imperium.finalyearproject;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class AdvancedFragment extends Fragment {

    protected View mView;


    public AdvancedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_advanced, container, false);
        this.mView = view;

        // Inflate the layout for this fragment
        return view;
    }

}
