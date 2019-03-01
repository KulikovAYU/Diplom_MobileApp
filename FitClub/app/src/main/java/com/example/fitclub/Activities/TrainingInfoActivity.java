package com.example.fitclub.Activities;

import android.os.Bundle;

import com.example.fitclub.Managers.Manager;
import com.example.fitclub.Models.CommercialTraining;
import com.example.fitclub.Models.Training;
import com.example.fitclub.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TrainingInfoActivity extends AppCompatActivity {

    protected Training mTraining;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        if (getIntent().getExtras() != null)
        {
            //получим информацию о тренировке
            Bundle arguments = (Bundle) (getIntent().getExtras()).get("selected_training");

            if (arguments.getSerializable("selected_training") instanceof Training)
                mTraining = (Training)arguments.getSerializable("selected_training");
        }

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
    private void LoadTrainingInfo()
    {
        //установим дату (она в тренировке)
        setTitle( mTraining.getTimeDayDate());

        //имя тренировки
        TextView trainingName = (TextView)findViewById(R.id.item_trainingNameId);
        trainingName.setText(mTraining.getTrainingName());

        ImageView replacedImage = (ImageView)findViewById(R.id.item_isReplacedId);
        if (!mTraining.getIsReplaced())
            replacedImage.setVisibility(View.GONE);
        //имя зала
        TextView gymName = (TextView)findViewById(R.id.item_gymNameId);
        gymName.setText(mTraining.getGymName());

        //время начала
        TextView startTime = (TextView)findViewById(R.id.item_startTrainingTimeId);
        startTime.setText(mTraining.getStartTimeHHmm());

        //время окончания
        TextView endTime =  (TextView)findViewById(R.id.item_endTrainingTimeId);
        endTime.setText(mTraining.getEndTimeHHmm());

        //имя инструктора
        TextView coachName = (TextView)findViewById(R.id.item_CoachNameId);
        coachName.setText(mTraining.getCoachName());

        //ДОБАВИТЬ ФОТО ТРЕНЕРА

        //интенсивность
        TextView levelName = (TextView)findViewById(R.id.item_levelId);
        levelName.setText(mTraining.getLevelName());

        //Если занятие не популярное, то скроем
        if (!mTraining.getIsPopular())
        {
            LinearLayout layoutIsPopular = (LinearLayout)findViewById(R.id.item_isPopularId);
            layoutIsPopular.setVisibility(View.GONE);
        }

        //Признак не коммерческого класса
        if (!(mTraining instanceof CommercialTraining))
        {

            LinearLayout layoutCommercial = (LinearLayout)findViewById(R.id.item_isCommercialId);
            layoutCommercial.setVisibility(View.GONE);

            LinearLayout layoutCommercialInfo = (LinearLayout)findViewById(R.id.item_commercial_training_infoId);
            layoutCommercialInfo.setVisibility(View.GONE);

            LinearLayout layoutCommercialInfoDivider = (LinearLayout)findViewById(R.id.item_divider2Id);
            layoutCommercialInfoDivider.setVisibility(View.GONE);

            TextView mustRegister = (TextView)findViewById(R.id.item_MustToWriteId);
            mustRegister.setVisibility(View.GONE);
        }
        else
        {
            //Признак коммерческого класса
            CommercialTraining commercialTraining = (CommercialTraining)mTraining;

            //получим поле количество свободных мест
            TextView freePlaces = (TextView)findViewById(R.id.item_freePlaceId);
            freePlaces.setText(commercialTraining.getStrFreePlacesCount());

            //получим поле количество свободных мест
            TextView finished = (TextView)findViewById(R.id.item_isFinishedId);

            Button btnRegister = (Button)findViewById(R.id.item_registerId);

            //если тренировка не закончена и места есть, то скрываем (подумать)
            if (commercialTraining.getRecordingIsPossible())
            {
                btnRegister.setVisibility(View.VISIBLE);
                finished.setVisibility(View.GONE);
            }else
            {
                btnRegister.setVisibility(View.GONE);
            }


        }


        //Тип программы
        TextView trainingType = (TextView)findViewById(R.id.item_descriptionTypeId);
        trainingType.setText(mTraining.getProgramType());

        //Описание тернировки
        TextView trainingDesc = (TextView)findViewById(R.id.item_descriptionId);
        trainingDesc.setText(mTraining.getDescription());




    }


    public void OnProfileClick(View view) {

        //Здесь необходимо получить тренера !!!!
      //  Bundle buf = new Bundle();
       // buf.putSerializable("selected_training",item);

       // Manager.GoToActivity(this, CoachInfoActivity.class,buf,"selected_training");
        Manager.GoToActivity(this, CoachInfoActivity.class,null,"selected_training");//Пока загглушка
    }
}