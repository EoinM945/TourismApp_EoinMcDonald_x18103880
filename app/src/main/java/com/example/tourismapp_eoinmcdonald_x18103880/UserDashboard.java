package com.example.tourismapp_eoinmcdonald_x18103880;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.tourismapp_eoinmcdonald_x18103880.HelperClasses.CategoriesAdapter;
import com.example.tourismapp_eoinmcdonald_x18103880.HelperClasses.CategoriesHelperClass;
import com.example.tourismapp_eoinmcdonald_x18103880.HelperClasses.FeaturedAdapter;
import com.example.tourismapp_eoinmcdonald_x18103880.HelperClasses.FeaturedHelperClass;
import com.example.tourismapp_eoinmcdonald_x18103880.HelperClasses.MostViewedAdapter;
import com.example.tourismapp_eoinmcdonald_x18103880.HelperClasses.MostViewedHelperClass;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class UserDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    static final float END_SCALE = 0.7f;

    RecyclerView featuredRecycler, mostViewedRecycler, categoriesRecycler;
    RecyclerView.Adapter adapter;

    ImageView menuIcon;
    LinearLayout contentView;

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_dashboard);

        //Hooks
        featuredRecycler = findViewById(R.id.featured_recycler);
        mostViewedRecycler = findViewById(R.id.most_viewed_recycler);
        categoriesRecycler = findViewById(R.id.categories_recycler);
        menuIcon = findViewById(R.id.menu_icon);
        contentView = findViewById(R.id.content);

        //menu hooks
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        

        navigationDrawer();


        //recycler view
        featuredRecycler();
        mostViewedRecycler();
        categoriesRecycler();


    }

    private void navigationDrawer() {

        //navigation Drawer
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(drawerLayout.isDrawerVisible(GravityCompat.START)){
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                else{
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });
        
        animimateNavigationDrawer();

    }

    private void animimateNavigationDrawer() {

        drawerLayout.setScrimColor(getResources().getColor(R.color.colorPrimary));
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);

                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);
            }


        });


    }


    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerVisible(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }


    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_maps:
                startActivity(new Intent(getApplicationContext(), MapsActivity.class));
                break;
            case R.id.nav_home:
                startActivity(new Intent(getApplicationContext(), UserDashboard.class));
                break;
            case R.id.nav_logout:
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
                break;

        }


        return true;
    }

    private void featuredRecycler() {

        featuredRecycler.setHasFixedSize(true);
        featuredRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<FeaturedHelperClass> featuredLocations = new ArrayList<>();

        featuredLocations.add(new FeaturedHelperClass(R.drawable.bar, "Murphy's Pub", "Local Irish traditional bar with drinks, food and live music"));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.dublin2, "Dublin City", "Lots to do in Dublin City"));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.dublin1, "Temple Bar", "Why not explore Temple Bar?"));

        adapter = new FeaturedAdapter(featuredLocations);
        featuredRecycler.setAdapter(adapter);

    }

    private void mostViewedRecycler() {

        mostViewedRecycler.setHasFixedSize(true);
        mostViewedRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<MostViewedHelperClass> mostViewedLocations = new ArrayList<>();

        mostViewedLocations.add(new MostViewedHelperClass(R.drawable.bar, "Murphy's Pub", "Local Irish traditional bar with drinks, food and live music"));
        mostViewedLocations.add(new MostViewedHelperClass(R.drawable.dublin2, "Dublin City", "Lots to do in Dublin City"));
        mostViewedLocations.add(new MostViewedHelperClass(R.drawable.dublin1, "Temple Bar", "Why not explore Temple Bar?"));

        adapter = new MostViewedAdapter(mostViewedLocations);
        mostViewedRecycler.setAdapter(adapter);
    }

    private void categoriesRecycler() {

        categoriesRecycler.setHasFixedSize(true);
        categoriesRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<CategoriesHelperClass> categoriesLocations = new ArrayList<>();

        categoriesLocations.add(new CategoriesHelperClass(R.drawable.restaurant, "Restaurants"));
        categoriesLocations.add(new CategoriesHelperClass(R.drawable.pub, "Bars/Clubs"));
        categoriesLocations.add(new CategoriesHelperClass(R.drawable.maps, "Discover"));


        adapter = new CategoriesAdapter(categoriesLocations);
        categoriesRecycler.setAdapter(adapter);
    }



}