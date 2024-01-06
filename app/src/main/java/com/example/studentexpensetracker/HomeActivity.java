package com.example.studentexpensetracker;

import static com.example.studentexpensetracker.R.id.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import org.checkerframework.checker.nullness.qual.NonNull;

//import android.widget.Toolbar;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;

    //Fragment

    private DashBoardFragment dashBoardFragment;
    private IncomeFragment incomeFragment;
    private ExpenseFragment expenseFragment;

    // Firebase Auth

    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar=findViewById(R.id.my_toolbar);
        toolbar.setTitle("Student Expense Tracker");
        setSupportActionBar(toolbar);

        mAuth = FirebaseAuth.getInstance();

        bottomNavigationView=findViewById(R.id.bottomNavigationBar);
        frameLayout=findViewById(R.id.main_frame);
        DrawerLayout drawerLayout=findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(
                this    ,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close
        );

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView=findViewById(R.id.naView);
        navigationView.setNavigationItemSelectedListener(this);


        dashBoardFragment=new DashBoardFragment();
        incomeFragment=new IncomeFragment();
        expenseFragment= new ExpenseFragment();

        setFragment(dashBoardFragment);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {


//                switch (item.getItemId()){
//
//                    case R.id.dashboard:
//                        setFragment(dashBoardFragment);
//                        bottomNavigationView.setItemBackgroundResource(R.color.dashboard_color);
//                        return true;
//
//                    case   R.id.income:
//                        setFragment(incomeFragment);
//                        bottomNavigationView.setItemBackgroundResource(R.color.income_color);
//                        return true;
//
//                    case R.id.expense:
//                        setFragment(expenseFragment);
//                        bottomNavigationView.setItemBackgroundResource(R.color.expense_color);
//                        return true;
//
//                    default:
//                        return false;
//                }

                // Using if statement


                if(item.getItemId() == R.id.dashboard){

                    setFragment(dashBoardFragment);
                        bottomNavigationView.setItemBackgroundResource(R.color.dashboard_color);
                        return true;

                }else if (item.getItemId() == R.id.income){

                    setFragment(incomeFragment);
                        bottomNavigationView.setItemBackgroundResource(R.color.income_color);
                        return true;

                }else if (item.getItemId()  == R.id.expense){

                    setFragment(expenseFragment);
                        bottomNavigationView.setItemBackgroundResource(R.color.expense_color);
                        return true;

                }else{
                    return false;
                }
            }


        });

    }

    private void setFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame,fragment);
        fragmentTransaction.commit();
    }


    @Override
    public void onBackPressed() {

        DrawerLayout drawerLayout=findViewById(R.id.drawer_layout);

        if(drawerLayout.isDrawerOpen(GravityCompat.END)){
            drawerLayout.closeDrawer(GravityCompat.END);
        }else{
            super.getOnBackPressedDispatcher();
        }


    }

    public void displaySelectedListener(int itemId){
        Fragment fragment = null;

        // Using an "if-else" statement to correct the below Switch
        //statement because the Switch cases require a constant variable

        if(itemId == R.id.dashboard){
            //Handle dashboard
            fragment=new DashBoardFragment();
        }else if (itemId == R.id.income){
            fragment =new IncomeFragment();
        }else if (itemId  == R.id.expense){
            fragment = new ExpenseFragment();
        }else if(itemId == R.id.logout){
            mAuth.signOut();
            startActivity(new Intent(getApplicationContext(),MainActivity.class));

        }else{
          throw new IllegalStateException("Unexpected value: " + itemId);

        }


//        switch (itemId){
//            case R.id.dashboard:
//                fragment = new DashBoardFragment();
//                break;
//
//            case R.id.income:
//                fragment = new IncomeFragment();
//                break;
//
//           case R.id.expense:
//                fragment = new ExpenseFragment();
//                break;
//            default:
//                throw new IllegalStateException("Unexpected value: " + itemId);
//        }

        if(fragment!=null){

            FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.main_frame,fragment);
            ft.commit();

        }

        DrawerLayout drawerLayout=findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        displaySelectedListener(item.getItemId());
        return true;
    }
}