package com.example.barter10;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarItemView;
import com.google.android.material.navigation.NavigationBarMenu;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity {

    MeowBottomNavigation bottomNavigation;
    int goBack;
    ImageView btnSearch;
    AppBarLayout appBarLayout;
    FloatingActionButton upload;
    FirebaseAuth firebaseAuth;//getting the id of user when sign in
    public static String currentUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //getting the id of user when sign in
        firebaseAuth = FirebaseAuth.getInstance();
        currentUserId = firebaseAuth.getCurrentUser().getUid();

        bottomNavigation = findViewById(R.id.bot_nav);
        btnSearch = findViewById(R.id.btnSearch);

        upload = findViewById(R.id.upload_item);

        appBarLayout = findViewById(R.id.appbar);

        //replaceFragment(new MessageFragment());

        //declaring bottom navigation
        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.nhome));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.nlist));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.nmessage));
        bottomNavigation.add(new MeowBottomNavigation.Model(4, R.drawable.nprofile));

        //choosing in bottom navigation
        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Fragment fragment = null;

                switch(item.getId()){
                    case 1:
                        goBack = 1;
                        appbarVisibility(goBack);
                        fragment = new HomeFragment();

                        break;

                    case 2:
                        goBack = 2;
                        appbarVisibility(goBack);
                        fragment = new ListFragment();

                        break;

                    case 3:
                        goBack = 3;
                        appbarVisibility(goBack);
                        fragment = new MessageFragment();

                        break;

                    case 4:
                        goBack = 4;
                        appbarVisibility(goBack);
                        fragment = new ProfileFragment();
                        break;
                }
                loadFragment(fragment);
            }

        });

        //count notif
        bottomNavigation.setCount(1, "1");

        bottomNavigation.show(1, true);

        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {

            }
        });

        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {

            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, activityUpload.class));
            }
        });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new SearchFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.homeFrameLayout,fragment).commit();
                bottomNavigation.setVisibility(View.GONE);
            }
        });

    }

    /**private void replaceFragment(MessageFragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }**/

    public void appbarVisibility(int goBack){
        if (goBack != 1){
            appBarLayout.setVisibility(View.GONE);
        }else{
            appBarLayout.setVisibility(View.VISIBLE);
        }
    }


    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout,fragment)
                .commit();
    }

    @Override
    public void onBackPressed() {
        if(goBack == 1){
            Log.d("CDA", "onBackPressed Called");
            Intent setIntent = new Intent(Intent.ACTION_MAIN);
            setIntent.addCategory(Intent.CATEGORY_HOME);
            setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(setIntent);
        }

    }


    /**@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);


        MenuItem menuItem = menu.findItem(R.id.searchIcon);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }**/


}