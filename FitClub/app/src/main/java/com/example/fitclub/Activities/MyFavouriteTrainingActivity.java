package com.example.fitclub.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.fitclub.Models.Training;
import com.example.fitclub.Navigators.AbstractNavigator;
import com.example.fitclub.Navigators.LeftPanelNavigator;
import com.example.fitclub.Navigators.TrainingListNavigator;
import com.example.fitclub.R;
import com.example.fitclub.abstracts.IOnConnectionListener;
import com.example.fitclub.abstracts.IOnListFragmentInteractionListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;



public class MyFavouriteTrainingActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, IOnListFragmentInteractionListener,IOnConnectionListener {

    AbstractNavigator mNavigator;
    private int  mnItemId = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_favourite_training);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        Bundle bund = getIntent().getExtras();
        if (bund != null) {
            mnItemId = bund.getInt("Item");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mNavigator = LeftPanelNavigator.createInstance(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id)
        {
            case R.id.trainingListId:
                startActivity(new Intent(this, TrainingListActivity.class).putExtra("Item", id));
                break;
            case R.id.myTrainingId:
                startActivity(new Intent(this, MyFavouriteTrainingActivity.class).putExtra("Item", id));
                break;

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        mNavigator.GoTo(id);
        return true;
    }


//без этого невозможно загрузить фрагмент
    @Override
    protected void onResume() {
        super.onResume();

        //зная mnItemId, можно загрузить фрагмент
        //загрузим фрагмент
        //LeftPanelNavigator.createInstance(this).GoTo(mnItemId);
        mNavigator.GoTo(mnItemId);
    }

    @Override
    public boolean CheckConnection(int nId) {

        mNavigator.GoTo(nId);

        return mNavigator.IsConnected();
    }

    //событие при клике по элементу списка тренировки
    @Override
    public void onListFragmentInteraction(Training item) {

        TrainingListNavigator.createInstance(this).GoToTrainingInfo(item);
        //Отладочный код
        //Toast.makeText(this, "Тренировка :" + item.getTrainingName(), Toast.LENGTH_LONG).show();
    }


}
