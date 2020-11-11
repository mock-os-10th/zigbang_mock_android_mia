package com.soft.zigbang.src.main;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.soft.zigbang.R;
import com.soft.zigbang.src.BaseActivity;
import com.soft.zigbang.src.main.concierge.ConciergeFragment;
import com.soft.zigbang.src.main.interfaces.MainActivityView;
import com.soft.zigbang.src.main.models.ApartInfoResponse;
import com.soft.zigbang.src.main.myhome.MyHomeFragment;

import java.util.List;

public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    private final String TAG = "zigBang MainActivity";
    private final Fragment mainFragment = new MainFragment();
    private final Fragment myHomeFragment = new MyHomeFragment();
    private final Fragment conciergeFragment = new ConciergeFragment();
    private final FragmentManager fm = getSupportFragmentManager();
    Fragment active = mainFragment;

    private MainService mMainService;

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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}