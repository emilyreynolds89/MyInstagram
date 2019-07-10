package com.codepath.myinstagram;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.codepath.myinstagram.fragment.FeedFragment;
import com.codepath.myinstagram.fragment.PostFragment;
import com.codepath.myinstagram.fragment.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //refreshButton = findViewById(R.id.btnRefresh);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.homeItem);

        final FragmentManager fragmentManager = getSupportFragmentManager();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment;
                switch (menuItem.getItemId()) {
                    case R.id.profileItem:
                        //Intent profileIntent = new Intent(HomeActivity.this, ProfileActivity.class);
                        //startActivity(profileIntent);

                        fragment = new ProfileFragment();
                        break;
                    case R.id.postItem:
                        //Intent postIntent = new Intent(HomeActivity.this, PostActivity.class);
                        //startActivity(postIntent);

                        fragment = new PostFragment();
                        break;
                    case R.id.homeItem:
                        fragment = new FeedFragment();
                        break;
                    default:
                        fragment = new FeedFragment();
                        break;
                }
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
                return true;
            }
        });

        bottomNavigationView.setSelectedItemId(R.id.homeItem);
    }


}
