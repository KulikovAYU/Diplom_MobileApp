package com.example.fitclub.Activities;

import android.os.Bundle;

import com.example.fitclub.Models.Client;
import com.example.fitclub.R;
import com.example.fitclub.ViewModels.ClientDataViewModel;
import com.example.fitclub.utils.TimeFormatter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ClientDataActivity extends AppCompatActivity {

    private ClientDataViewModel clientDataViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_cabinet);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        //Подключим кнопку назад
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);


        clientDataViewModel = ViewModelProviders.of(this).get(ClientDataViewModel.class);

        clientDataViewModel.initializeClientInfo(this).observe(this, new Observer<Client>() {
            @Override
            public void onChanged(Client client) {
                FillClientData(client);
            }
        });

        clientDataViewModel.getClientData(Client.USERID);
    }



    //заполнить
    void FillClientData(Client client)
    {
        if (client == null)
            return;

        //имя фамилия
        TextView nameAndFamily = findViewById(R.id.clientNameAndFamilyId);
        nameAndFamily.setText(client.getClientName() + " "+ client.getClientFamily());

        //номер абонемента
        TextView numberAbonement = findViewById(R.id.cardNumberId);
        numberAbonement.setText(String.valueOf(client.getNumberAbonement()));

        //Тип абонемента
        TextView cardType = findViewById(R.id.cardTypeId);
        cardType.setText(client.getAbonementType());

        //Значек активности
        ImageView isActive = findViewById(R.id.itemIsActiveId);

        if (client.IsAсtive()) {
            isActive.setImageResource(R.drawable.ic_check_circle_green_24dp);
        } else if(client.IsFreeze())
        {
            isActive.setImageResource(R.drawable.ic_snowflake);
        }
        else {
            isActive.setImageResource(R.drawable.ic_remove_circle_red_24dp);
        }

        //активно до
        TextView activeFor = findViewById(R.id.cardActiveId);
        activeFor.setText(TimeFormatter.convertDate_d_M_y(client.getEndTime()));


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



}
