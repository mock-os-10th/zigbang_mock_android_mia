package com.soft.zigbang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private final String TAG = "zigBang MainActivity";
    private final Fragment mainFragment = new MainFragment();
    private final Fragment myHomeFragment = new MyHomeFragment();
    private final Fragment conciergeFragment = new ConciergeFragment();
    private final FragmentManager fm = getSupportFragmentManager();
    Fragment active = mainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigationView = findViewById(R.id.bottom_nav);
        navigationView.setOnNavigationItemSelectedListener(this);

        fm.beginTransaction().add(R.id.container, conciergeFragment, "3").hide(conciergeFragment).commit();
        fm.beginTransaction().add(R.id.container, myHomeFragment, "2").hide(myHomeFragment).commit();
        fm.beginTransaction().add(R.id.container, mainFragment, "1").commit();

        getHashKey();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_real_estate:
                fm.beginTransaction().hide(active).show(mainFragment).commit();
                active = mainFragment;
                return true;
            case R.id.item_my_home:
                fm.beginTransaction().hide(active).show(myHomeFragment).commit();
                active = myHomeFragment;
                return true;
            case R.id.item_concierge:
                fm.beginTransaction().hide(active).show(conciergeFragment).commit();
                active = conciergeFragment;
                return true;
        }
        return false;
    }

    private void getHashKey() {
        String keyHash = com.kakao.util.maps.helper.Utility.getKeyHash(this );
        Log.d(TAG, keyHash);
    }
}