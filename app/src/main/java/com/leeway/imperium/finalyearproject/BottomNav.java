package com.leeway.imperium.finalyearproject;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

public class BottomNav extends AppCompatActivity {

    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;

    private HomeFragment homeFragment;
    private PredictionFragment predictionFragment;
    private AdvancedFragment advancedFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_nav);

        mMainFrame = (FrameLayout) findViewById(R.id.mainFrame);
        mMainNav = (BottomNavigationView) findViewById(R.id.mainNav);

        homeFragment = new HomeFragment();
        predictionFragment = new PredictionFragment();
        advancedFragment = new AdvancedFragment();

        setFragment(homeFragment);

        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navHome :
                        setFragment(homeFragment);
                        return true;

                    case R.id.navPrediction :
                        setFragment(predictionFragment);
                        return true;

                    case R.id.navAdvanced :
                        setFragment(advancedFragment);
                        return true;

                    default :
                        return false;
                }
            }
        });
    }

    public void updatePrediction(View v) {
        predictionFragment.makePrediction(v);
    }

    private void setFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.mainFrame, fragment);
        fragmentTransaction.commit();
    }
}
