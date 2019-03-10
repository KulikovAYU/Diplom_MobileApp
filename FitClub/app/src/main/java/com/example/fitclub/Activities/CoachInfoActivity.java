package com.example.fitclub.Activities;

import android.graphics.Bitmap;
import android.os.Bundle;

import com.example.fitclub.Models.Coach;
import com.example.fitclub.Models.Training1;
import com.example.fitclub.R;
import com.example.fitclub.ViewModels.CoachViewModel;
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

public class CoachInfoActivity extends AppCompatActivity {


    private CoachViewModel mCoachInfoViewModel;
    private Training1 mTraining;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach_info);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //Подключим кнопку назад
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        //подключим view model
        mCoachInfoViewModel = ViewModelProviders.of(this).get(CoachViewModel.class);
        mCoachInfoViewModel.SetContext(this);


        Bundle args = getIntent().getExtras();

        if (args != null) {
            //получим информацию о тренировке
            Bundle arguments = (Bundle) (args).get("ItemSelected_training_coach");

            if (arguments.getSerializable("selected_training_coach") instanceof Training1) {
                mTraining = (Training1) arguments.getSerializable("selected_training_coach");
                mCoachInfoViewModel.getCoach(mTraining).observe(this, new Observer<Coach>() {
                    @Override
                    public void onChanged(Coach coach) {
                        SetCoachData(coach);
                    }
                });
            }
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
    }


    void SetCoachData(Coach mCoach) {
        TextView nameAndFam = findViewById(R.id.item_coach_name_and_fam_id);

        //имя - фамилия
        nameAndFam.setText(mCoach.getCoachFamily() + " " + mCoach.getCoachName());

        //описание тренера
        TextView mCoachDesc = findViewById(R.id.item_coach_descriptionId);

        mCoachDesc.setText(mCoach.getCoachDesc());

        //фото
        ImageView mView = findViewById(R.id.item_coachPhotoId);

        Bitmap coachPhoto = mCoach.getCoachPhoto();
        if (coachPhoto != null) {
            mView.setImageBitmap(coachPhoto);
        }
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
