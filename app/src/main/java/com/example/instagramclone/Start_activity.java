package com.example.instagramclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.instagramclone.Fragments.HomeFragment;
import com.example.instagramclone.Fragments.NotificationFragment;
import com.example.instagramclone.Fragments.ProfileFragment;
import com.example.instagramclone.Fragments.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

public class Start_activity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private Fragment selectorFragment;
    FloatingActionButton btnadd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);
        bottomNavigationView = findViewById(R.id.bottom);
        btnadd = findViewById(R.id.fab);
        bottomNavigationView.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), android.R.color.transparent));
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(Start_activity.this,PostActivity.class));

            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.navhome:
                        selectorFragment= new HomeFragment();

                        break;
                    case R.id.navsearch:
                        selectorFragment= new SearchFragment();

                        break;
                    case R.id.navadd:
                        selectorFragment = null;


//                        startActivity( new Intent(Start_activity.this,PostActivity.class));


                        break;
                    case R.id.navheart:
                        selectorFragment= new NotificationFragment();

                        break;
                    case R.id.navprofile:
//                        Intent intent = new Intent(Start_activity.this, Start_activity.class);
//                        intent.putExtra("PublisherId", FirebaseAuth.getInstance().getCurrentUser().getUid());
//                        startActivity(intent);
//                        finish();
                        getSharedPreferences("Profile",MODE_PRIVATE).edit().putString("ProfileId",FirebaseAuth.getInstance().getCurrentUser().getUid()).apply();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ProfileFragment()).commit();
//                        selectorFragment= new ProfileFragment();
                        selectorFragment = null;

                        break;
                }
               if (selectorFragment!=null){
                   getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectorFragment).commit();
               }
               return true;
            }

        });
        Bundle intent = getIntent().getExtras();
        if (intent!=null){
            String profileid =intent.getString("PublisherId");
            getSharedPreferences("Profile",MODE_PRIVATE).edit().putString("ProfileId",profileid).apply();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ProfileFragment()).commit();

        }else{
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();


        }
    }
}