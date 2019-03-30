package com.example.fitclub.Activities;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.fitclub.Models.Training;
import com.example.fitclub.Models.User;
import com.example.fitclub.Navigators.TrainingListNavigator;
import com.example.fitclub.R;
import com.example.fitclub.ViewModels.SelectedTrainingViewModel;
import com.example.fitclub.utils.TimeFormatter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class TrainingInfoActivity extends AppCompatActivity {

    protected Training mTraining;
    protected AlertDialog progressDlg;

    protected SelectedTrainingViewModel mSelectedTrainingViewModel;

    protected Boolean mbIswriting = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getIntent().getExtras() != null) {
            //получим информацию о тренировке
            Bundle arguments = (Bundle) (getIntent().getExtras()).get("ItemSelected_training");

            if (arguments.getSerializable("selected_training") instanceof Training)
                mTraining = (Training) arguments.getSerializable("selected_training");
        }


        //подключим view model
        mSelectedTrainingViewModel = ViewModelProviders.of(this).get(SelectedTrainingViewModel.class);

        mSelectedTrainingViewModel.initializeTrainingInfo(this).observe(this,new Observer<Training>() {
            @Override
            public void onChanged(Training training) {
                mTraining = training;
                LoadTrainingInfo();
            }
        });


        mSelectedTrainingViewModel.getTrainingInfo(mTraining.getTrtainingId(),mTraining.getStartTime());

        mSelectedTrainingViewModel.initializeIsWriting(this).observe(this,new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isWriting) {
                mbIswriting = isWriting;
                CheckWriting(isWriting);
            }
        });


        setContentView(R.layout.activity_training_info);
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

        LoadTrainingInfo();
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

    //загружаем информацио о тренировке
    private void LoadTrainingInfo() {

        if (mTraining == null)
            return;

        //установим дату (она в тренировке)
        setTitle(TimeFormatter.convertTimeEEEMMMd(mTraining.getStartTime()));

        //имя тренировки
        TextView trainingName = (TextView) findViewById(R.id.item_trainingNameId);
        trainingName.setText(mTraining.getTrainingName());

        ImageView replacedImage = (ImageView) findViewById(R.id.item_isReplacedId);
        if (!mTraining.getIsReplaced())
            replacedImage.setVisibility(View.GONE);
        //имя зала
        TextView gymName = (TextView) findViewById(R.id.item_gymNameId);
        gymName.setText(mTraining.getGymName());

        //время начала
        TextView startTime = (TextView) findViewById(R.id.item_startTrainingTimeId);
        startTime.setText(TimeFormatter.convertTimeHHmm(mTraining.getStartTime()));

        //время окончания
        TextView endTime = (TextView) findViewById(R.id.item_endTrainingTimeId);
        endTime.setText(TimeFormatter.convertTimeHHmm(mTraining.getEndTime()));

        //имя инструктора
        TextView coachName = (TextView) findViewById(R.id.item_CoachNameId);
        coachName.setText(mTraining.getCoachFamily() + " " + mTraining.getCoachName());

        //интенсивность
        TextView levelName = (TextView) findViewById(R.id.item_levelId);
        levelName.setText(mTraining.getLevelName());

        //Если занятие не популярное, то скроем
        if (!mTraining.getIsPopular()) {
            LinearLayout layoutIsPopular = (LinearLayout) findViewById(R.id.item_isPopularId);
            layoutIsPopular.setVisibility(View.GONE);
        }

        //Признак не коммерческого класса
        if (!(mTraining.getIsMustPay())) {

            LinearLayout layoutCommercial = (LinearLayout) findViewById(R.id.item_isCommercialId);
            layoutCommercial.setVisibility(View.GONE);

            LinearLayout layoutCommercialInfo = (LinearLayout) findViewById(R.id.item_commercial_training_infoId);
            layoutCommercialInfo.setVisibility(View.GONE);

            LinearLayout layoutCommercialInfoDivider = (LinearLayout) findViewById(R.id.item_divider2Id);
            layoutCommercialInfoDivider.setVisibility(View.GONE);

            TextView mustRegister = (TextView) findViewById(R.id.item_MustToWriteId);
            mustRegister.setVisibility(View.GONE);
        } else {
            //Признак коммерческого класса
            //  CommercialTraining commercialTraining = (CommercialTraining)mTraining;

            //получим поле количество свободных мест
            TextView freePlaces = (TextView) findViewById(R.id.item_freePlaceId);
            //получим кол-во свободных мест
            int freeplacescount = mTraining.getPlacesCount() - mTraining.getBusyPlacesCount();
            freePlaces.setText(String.valueOf(freeplacescount));

            //получим поле количество свободных мест
            TextView finished = (TextView) findViewById(R.id.item_isFinishedId);

            Button btnRegister = (Button) findViewById(R.id.item_registerId);

            //если тренировка не закончена и места есть, то скрываем (подумать)
            if (mTraining.getRecordingIsPossible()) {
                btnRegister.setVisibility(View.VISIBLE);
                findViewById(R.id.item_vacation_places1_infoId).setVisibility(View.VISIBLE);
                finished.setVisibility(View.GONE);
            } else {
                findViewById(R.id.item_vacation_places1_infoId).setVisibility(View.GONE);
                btnRegister.setVisibility(View.GONE);
            }
        }

        //Тип программы
        TextView trainingType = (TextView) findViewById(R.id.item_descriptionTypeId);
        trainingType.setText(mTraining.getProgramType());

        //Описание тернировки
        TextView trainingDesc = (TextView) findViewById(R.id.item_descriptionId);
        trainingDesc.setText(mTraining.getDescription());

        //получить фото тренера
        ImageView coachPhoto = (ImageView)findViewById(R.id.CoachImageView);

        mSelectedTrainingViewModel.setImage(mTraining.getCoachId(),coachPhoto);

        mSelectedTrainingViewModel.bIsAlereadyWriting(User.USERID,mTraining.getTrtainingId());
    }
    Button btnRegister;
    public void CheckWriting(Boolean aBoolean)
    {
        //проверим записан ли текущий пользователь на тренировку
        LinearLayout sucessWriting = findViewById(R.id.item_vacation_places_infoId1);
        btnRegister = (Button) findViewById(R.id.item_registerId);
        if(aBoolean) // в случае успешной записи
        {
            btnRegister.setText(getString(R.string.AbortWriting));
            //получим поле количество свободных мест
            sucessWriting.setVisibility(View.VISIBLE);
        }
        else
        {
            btnRegister.setText(getString(R.string.Writing));
            sucessWriting.setVisibility(View.GONE);
        }
    }

    //получить тренера
    public void OnProfileClick(View view) {
        TrainingListNavigator.createInstance(this).GoToCoachInfo(mTraining);
    }

    //записаться на тренировку
    public void WriteToTrainingClick(View view) {


        if (btnRegister.getText().equals(getString(R.string.AbortWriting)))
        {
            AlertDialog alert = new AlertDialog.Builder(this).setMessage("Вы уверены, что хотите отменить запись?").setNegativeButton("НЕТ", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                        return;
                }
            }).setPositiveButton("ДА", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    View layout = getLayoutInflater().inflate(R.layout.preentry,null);
                    progressDlg = new AlertDialog.Builder(TrainingInfoActivity.this).setView(layout).create();
                    progressDlg.show();
                    mSelectedTrainingViewModel.createRegistrationOnTraining(User.USERID,mTraining.getTrtainingId(),mTraining.getStartTime(),progressDlg);
                }
            }).create();
            alert.show();
            Button nbutton = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
            //Set negative button text color
            nbutton.setTextColor(Color.rgb(255,214,0));

            Button pbutton = alert.getButton(DialogInterface.BUTTON_POSITIVE);
            //Set positive button text color
            pbutton.setTextColor(Color.rgb(255,214,0));
        }
        else
        {
            View layout = getLayoutInflater().inflate(R.layout.preentry,null);
            progressDlg = new AlertDialog.Builder(TrainingInfoActivity.this).setView(layout).create();
            progressDlg.show();
            mSelectedTrainingViewModel.createRegistrationOnTraining(User.USERID,mTraining.getTrtainingId(),mTraining.getStartTime(),progressDlg);
        }
    }
}


