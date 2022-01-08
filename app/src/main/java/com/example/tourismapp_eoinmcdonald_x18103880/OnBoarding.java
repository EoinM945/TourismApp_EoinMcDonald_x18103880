package com.example.tourismapp_eoinmcdonald_x18103880;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.ramotion.paperonboarding.PaperOnboardingFragment;
import com.ramotion.paperonboarding.PaperOnboardingPage;
import com.ramotion.paperonboarding.listeners.PaperOnboardingOnRightOutListener;

import java.util.ArrayList;


public class OnBoarding extends AppCompatActivity {

    private FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_on_boarding);

        fragmentManager = getSupportFragmentManager();

        final PaperOnboardingFragment paperOnboardingFragment = PaperOnboardingFragment.newInstance(getDataForOnboarding());

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, paperOnboardingFragment);
        fragmentTransaction.commit();

        paperOnboardingFragment.setOnRightOutListener(new PaperOnboardingOnRightOutListener() {
            @Override
            public void onRightOut() {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Fragment bf = new BlankFragment();
                fragmentTransaction.replace(R.id.fragment_container, bf);
                fragmentTransaction.commit();
            }
        });


        Button button = (Button) findViewById(R.id.continue_btn);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });



    }

    private ArrayList<PaperOnboardingPage> getDataForOnboarding() {

        PaperOnboardingPage scr1 = new PaperOnboardingPage("Restaurants", "Plenty of fine dining to be found in the city with a wide array of cuisines.",
                Color.parseColor("#ffb174"), R.drawable.restaurant, R.drawable.restaurant);
        PaperOnboardingPage scr2 = new PaperOnboardingPage("Pubs/Clubs", "Lots of Pubs, Bars and Clubs to visit and drink pints of beer or spirits.",
                Color.parseColor("#22eaaa"), R.drawable.pub, R.drawable.pub);
        PaperOnboardingPage scr3 = new PaperOnboardingPage("Explore", "Explore the city for other places of interest during your visit.",
                Color.parseColor("#ee5a5a"), R.drawable.maps, R.drawable.maps);


        ArrayList<PaperOnboardingPage> elements = new ArrayList<>();
        elements.add(scr1);
        elements.add(scr2);
        elements.add(scr3);
        return elements;

    }
}