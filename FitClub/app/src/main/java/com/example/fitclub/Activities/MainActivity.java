package com.example.fitclub.Activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;



import com.example.fitclub.Models.Training1;
import com.example.fitclub.Navigators.LeftPanelNavigator;
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
    LeftPanelNavigator mNavigator;

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


        LeftPanelNavigator.createInstance(this).GoTo(id);


        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();

        //зная mnItemId, можно загрузить фрагмент

        //предварительно проверив подключение к сети

        //загрузим фрагмент
        LeftPanelNavigator.createInstance(this).GoTo(mnItemId);

//        switch (mnItemId)
//        {
//
//            case R.id.gotoStartActivityId:
//            {
//                Intent intent = new Intent(this, StartActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                this.startActivity(intent);
//            }
//            break;
//
//            case R.id.trainingListId:
//            {
//                FragmentTransaction fragmentTransaction =  getSupportFragmentManager().beginTransaction();
//                if (getSupportFragmentManager().findFragmentByTag(FragmentMainTrainingList.TAG) == null) {
//                    FragmentMainTrainingList mainTrainingListFragment =  FragmentMainTrainingList.newInstance();
//                    fragmentTransaction.add(R.id.fragments_content, mainTrainingListFragment, mainTrainingListFragment.TAG);
//                }
//                fragmentTransaction.commit();
//            }
//            break;
//
////добавляем переходы к фрагментам
//
//        }


//        try {
//            if (mnItemId != -1)
//                NavigationManager.Instance().Invoke(mnItemId, this);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }


//////interfaces imolemetation\\\

    //событие при клике по элементу списка тренировки
    @Override
    public void onListFragmentInteraction(Training1 item) {

        Bundle buf = new Bundle();
        buf.putSerializable("selected_training", item);

     //   Manager.GoToActivity(this, TrainingInfoActivity.class, buf, "selected_training");

        //передадим информацию о тренировке в фрагмент инфоромации о тренировке
        //   ((TrainingListFragmentFragmentPageManager) manager).AddTrainingInfoFragment(buf);


        Toast.makeText(this, "Тренировка :" + item.getTrainingName(), Toast.LENGTH_LONG).show();
    }


    //проверка подключения к сети
    @Override
    public void CheckConnection(int nId) {

        LeftPanelNavigator.createInstance(this).GoTo(nId);


    }



}