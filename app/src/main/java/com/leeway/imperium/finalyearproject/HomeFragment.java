package com.leeway.imperium.finalyearproject;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private Button disclaimerButton;
    private TextView textBody;

    protected View mView;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        this.mView = view;

        textBody = (TextView) mView.findViewById(R.id.textBody);
        textBody.setMovementMethod(new ScrollingMovementMethod());

        disclaimerButton = (Button) mView.findViewById(R.id.disclaimerButton);
        disclaimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                builder.setCancelable(true);
                builder.setTitle("Disclaimer");
                builder.setMessage(R.string.disclaimerText);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                builder.show();
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

}
