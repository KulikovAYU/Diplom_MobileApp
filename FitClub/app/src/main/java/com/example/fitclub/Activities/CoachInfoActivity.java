package com.example.fitclub.Activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.fitclub.Models.Coach;
import com.example.fitclub.Models.Training1;
import com.example.fitclub.R;
import com.example.fitclub.Repository.classes.CoachRepository;
import com.example.fitclub.Retrofit2.RetrofitAPI;
import com.example.fitclub.ViewModels.CoachViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

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
        final ImageView mView = findViewById(R.id.item_coachPhotoId);

//        final Bitmap coachPhoto = mCoach.getCoachPhoto();
//        if (coachPhoto != null) {
//            mView.setImageBitmap(coachPhoto);
//        }


//        OkHttpClient client = new OkHttpClient();
//
//        Request request = new Request.Builder().get()
//                .url("http://localhost:56073/api/employees/getcoachPhoto/1")
//                .build();
//
//
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call request, IOException e) {
//                System.out.println("request failed: " + e.getMessage());
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                InputStream is =  response.body().byteStream();
//                Bitmap bmp = BitmapFactory.decodeStream(is);
//
//                Bitmap coachPhoto1 = bmp;// Read the data from the stream
//                mView.setImageBitmap(coachPhoto1);
//            }
//
//
//        });
        ResponseBody response = null;
        try {
            response = new GetPhoto().execute().get();
                   } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (response != null)
        {
            int n =1;
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

    private class GetPhoto extends AsyncTask<Void, Void, ResponseBody>
    {

        @Override
        protected ResponseBody doInBackground(Void... voids) {
            RetrofitAPI api = new RetrofitAPI(CoachInfoActivity.this);
             api.getPhoto("1");
             return null;

        }
    }

}
