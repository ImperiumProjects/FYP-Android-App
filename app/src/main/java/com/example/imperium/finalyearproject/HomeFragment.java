package com.example.imperium.finalyearproject;


import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private Button disclaimerButton;

    protected View mView;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        this.mView = view;

        disclaimerButton = (Button) mView.findViewById(R.id.disclaimerButton);
        disclaimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup(mView);
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

    public void showPopup(View anchorView) {

        View popupView = getLayoutInflater().inflate(R.layout.popup_layout, null);

        PopupWindow popupWindow = new PopupWindow(popupView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        // Example: If you have a TextView inside `popup_layout.xml`
        TextView tv = (TextView) popupView.findViewById(R.id.tv);

        // Initialize more widgets from `popup_layout.xml`

        // If the PopupWindow should be focusable
        popupWindow.setFocusable(true);

        // If you need the PopupWindow to dismiss when when touched outside
        popupWindow.setBackgroundDrawable(new ColorDrawable());

        int location[] = new int[2];

        // Get the View's(the one that was clicked in the Fragment) location
        anchorView.getLocationOnScreen(location);

        // Using location, the PopupWindow will be displayed right under anchorView
        //popupWindow.showAtLocation(anchorView, Gravity.NO_GRAVITY,
                //location[0], location[1] + anchorView.getHeight());
        popupWindow.showAtLocation(anchorView, Gravity.CENTER, 0, 0);
    }

}
