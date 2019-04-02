package com.example.fitclub.Activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fitclub.Models.Employee;
import com.example.fitclub.Models.Training;
import com.example.fitclub.R;
import com.example.fitclub.ViewModels.CoachViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class CoachInfoActivity extends AppCompatActivity {


    private CoachViewModel mCoachInfoViewModel;
    private Training mTraining;


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

        mCoachInfoViewModel.initializeCoachInfo(this).observe(this,  new Observer<Employee>() {
            @Override
            public void onChanged(Employee employee) {
                SetCoachData(employee);
            }
        });



        Bundle args = getIntent().getExtras();

        if (args != null) {
            //получим информацию о тренировке
            Bundle arguments = (Bundle) (args).get("ItemSelected_training_coach");

            if (arguments.getSerializable("selected_training_coach") instanceof Training) {
                mTraining = (Training) arguments.getSerializable("selected_training_coach");
                mCoachInfoViewModel.getCoach(mTraining);
            }
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
    }


    void SetCoachData(Employee mEmployee) {
        TextView nameAndFam = findViewById(R.id.item_coach_name_and_fam_id);

        //имя - фамилия
        nameAndFam.setText(mEmployee.getCoachFamily() + " " + mEmployee.getCoachName());

        //описание тренера
        TextView mCoachDesc = findViewById(R.id.item_coach_descriptionId);

        mCoachDesc.setText(mEmployee.getCoachDesc());

        //фото
        ImageView mView = findViewById(R.id.item_coachPhotoId);

        mCoachInfoViewModel.setImage(mEmployee.getId(),mView);
//        final Bitmap coachPhoto = mEmployee.getCoachPhoto();
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
//        ResponseBody response = null;
//        try {
//            response = new GetPhoto().execute().get();
//                   } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        if (response != null)
//        {
//            int n =1;
//        }
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

//    private class GetPhoto extends AsyncTask<Void, Void, ResponseBody>
//    {
//        @Override
//        protected ResponseBody doInBackground(Void... voids) {
//            RetrofitAPI api = new RetrofitAPI(CoachInfoActivity.this);
//             api.setPhoto("1");
//             return null;
//
//        }
//    }

}
