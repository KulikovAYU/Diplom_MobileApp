package com.example.fitclub.Activities;

import android.os.Bundle;
import android.view.Menu;
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

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import static com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener;


public class MainActivity extends AppCompatActivity
        implements OnNavigationItemSelectedListener, IOnListFragmentInteractionListener, IOnConnectionListener {


    //фикс повторного создания фрагмента при повороте экрана
    private int mnItemId = -1;

    AbstractNavigator mNavigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Bundle bund = getIntent().getExtras();
        if (bund != null) {
            mnItemId = bund.getInt("Item");
        }


        mNavigator = LeftPanelNavigator.createInstance(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

//        try {
//            NavigationManager.Instance().Invoke(id, this);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);


        mNavigator.GoTo(id);


        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();

        //зная mnItemId, можно загрузить фрагмент
        //загрузим фрагмент
        LeftPanelNavigator.createInstance(this).GoTo(mnItemId);

    }


//////interfaces imolemetation\\\

    //событие при клике по элементу списка тренировки
    @Override
    public void onListFragmentInteraction(Training item) {

        TrainingListNavigator.createInstance(this).GoToTrainingInfo(item);
        //Отладочный код
        //Toast.makeText(this, "Тренировка :" + item.getTrainingName(), Toast.LENGTH_LONG).show();
    }

    //проверка подключения к сети
    @Override
    public boolean CheckConnection(int nId) {

        mNavigator.GoTo(nId);

        return mNavigator.IsConnected();
    }
}