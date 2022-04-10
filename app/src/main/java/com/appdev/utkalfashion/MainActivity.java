package com.appdev.utkalfashion;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.appdev.utkalfashion.Adapters.CategoryAdapter;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {


    private RecyclerView mCategoryRecyclerView;
    private CategoryAdapter categoryAdapter;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        setSupportActionBar(findViewById(R.id.toolbar_main));
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        LinearLayout headerView = (LinearLayout) navigationView.getHeaderView(0);
        de.hdodenhof.circleimageview.CircleImageView profileView =
                headerView.findViewById(R.id.profile_image);
        TextView userNameView = headerView.findViewById(R.id.login_display);
        TextView userMailView = headerView.findViewById(R.id.email);

        userNameView.setOnClickListener(v -> {
            if(mAuth.getCurrentUser()==null){
                Intent intent = new Intent(MainActivity.this,
                        LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, findViewById(R.id.toolbar_main),
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        if(mAuth.getCurrentUser()!=null){
            Glide.with(this).load(Objects.requireNonNull(mAuth.getCurrentUser()).getPhotoUrl()).into(profileView);
            userNameView.setText(mAuth.getCurrentUser().getDisplayName());
            userMailView.setText(mAuth.getCurrentUser().getEmail());
            userMailView.setVisibility(View.VISIBLE);
        }

        navigationView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_my_orders:
                    openMyOrdersActivity();
                    break;
                    case R.id.nav_my_cart:
                        openMyCart();
                        break;
                case R.id.nav_logout:
                    logoutApp();
                    //drawer.closeDrawers();
                    break;
            }
            return true;
        });

        this.mCategoryRecyclerView = findViewById(R.id.category_recycler_view);
        this.categoryAdapter = new CategoryAdapter(this);
        this.mCategoryRecyclerView.setAdapter(this.categoryAdapter);
        this.mCategoryRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private void openMyOrdersActivity(){
        startActivity(new Intent(this, MyOrders.class));
    }

    private void openMyCart(){
        startActivity(new Intent(this, MyCart.class));
    }

    private void logoutApp(){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(MainActivity.this,
                SplashActivity.class));
        finish();
    }
}