package com.example.fitclub.Activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.fitclub.R;
import com.example.fitclub.common.Log;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.FitnessOptions;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.google.android.gms.fitness.request.OnDataPointListener;
import com.google.android.gms.fitness.result.DataReadResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


public class FitnessAPIActivity extends AppCompatActivity {

    private static final int REQUEST_OAUTH_REQUEST_CODE = 0x1001;
    public static final String TAG = "StepCounter";


    // [START mListener_variable_reference]
    // Нужно хранить ссылку на этого слушателя, так как он передается в «незарегистрированный»
    // метод, чтобы не дать всем датчикам отправлять данные этому слушателю.
    private OnDataPointListener mListener;
    // [END mListener_variable_reference]

    GoogleSignInClient mGoogleSignInClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitness_api);
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


        initializeLogging();

        comesIntoGoogleAccount();
//
//        BleScanCallback bleScanCallbacks = new BleScanCallback() {
//            @Override
//            public void onDeviceFound(BleDevice device) {
//                // A device that provides the requested data types is available
//                Log.i(TAG, "Data source found: " + device.getName());
//            }
//            @Override
//            public void onScanStopped() {
//                // The scan timed out or was interrupted
//            }
//        };
//
//        Task<Void> response = Fitness.getBleClient(this,
//                GoogleSignIn.getLastSignedInAccount(this))
//                .startBleScan(Arrays.asList(DataType.TYPE_STEP_COUNT_DELTA),
//                        1000, bleScanCallbacks);


        findFitnessDataSourcesWrapper();




//        FitnessOptions fitnessOptions =
//                FitnessOptions.builder()
//                        .addDataType(DataType.TYPE_STEP_COUNT_CUMULATIVE)
//                        .addDataType(DataType.TYPE_STEP_COUNT_DELTA)
//                        .build();
//
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestEmail()
//                .build();
//        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
//
//        if (!GoogleSignIn.hasPermissions(GoogleSignIn.getLastSignedInAccount(this), fitnessOptions)) {
//            GoogleSignIn.requestPermissions(
//                    this,
//                    REQUEST_OAUTH_REQUEST_CODE,
//                    GoogleSignIn.getLastSignedInAccount(this),
//                    fitnessOptions);
//        } else {
//            subscribe();
//        }


    }



    private void comesIntoGoogleAccount() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    //ищем источники данных
    private void findFitnessDataSourcesWrapper() {

        if (hasOAuthPermission()) {
            findFitnessDataSources();
        } else {
            requestOAuthPermission();
        }
    }

    /** Launches the Google SignIn activity to request OAuth permission for the user. */
    private void requestOAuthPermission() {
        FitnessOptions fitnessOptions = getFitnessSignInOptions();
        GoogleSignIn.requestPermissions(
                this,
                REQUEST_OAUTH_REQUEST_CODE,
                GoogleSignIn.getLastSignedInAccount(this),
                fitnessOptions);
    }


    //метод проверяет разрешения OAuth google
    private boolean hasOAuthPermission() {
        FitnessOptions fitnessOptions = getFitnessSignInOptions();
        return GoogleSignIn.hasPermissions(GoogleSignIn.getLastSignedInAccount(this), fitnessOptions);
    }

    /** Gets the {@link FitnessOptions} in order to check or request OAuth permission for the user. */
    //полный список данных доступен тут:
    //https://developers.google.com/android/reference/com/google/android/gms/fitness/data/DataType
    private FitnessOptions getFitnessSignInOptions() {
        return FitnessOptions.builder()
                .addDataType(DataType.TYPE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_WRITE) //кол-во шагов, выполненных с момента последнего чтения
                .addDataType(DataType.AGGREGATE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_WRITE) //общее кол-во шагов за промежуток времени
                .addDataType(DataType.TYPE_STEP_COUNT_CUMULATIVE,FitnessOptions.ACCESS_WRITE) //кол-во шагов пользователя
                .addDataType(DataType.TYPE_CALORIES_EXPENDED,FitnessOptions.ACCESS_WRITE)
                .addDataType(DataType.AGGREGATE_DISTANCE_DELTA,FitnessOptions.ACCESS_WRITE)
                .build();
    }


    /** Finds available data sources and attempts to register on a specific {@link DataType}. */
    private void findFitnessDataSources() {

        Task<DataSet> _responseSteps =   Fitness.getHistoryClient(this, GoogleSignIn.getLastSignedInAccount(this))
                .readDailyTotal(DataType.TYPE_STEP_COUNT_DELTA);

        _responseSteps.addOnSuccessListener(new OnSuccessListener<DataSet>() {
            @Override
            public void onSuccess(DataSet dataSet) {
                long total = dataSet.isEmpty() ? 0 : dataSet.getDataPoints().get(0).getValue(Field.FIELD_STEPS).asInt();
                TextView text = (TextView)findViewById(R.id.item_steps_countId);
                text.setText(String.valueOf(total));
            }
        });

        _responseSteps.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "There was a problem getting the step count.", e);
            }
        });



        Task<DataSet> responseCalories =   Fitness.getHistoryClient(this, GoogleSignIn.getLastSignedInAccount(this))
                .readDailyTotal(DataType.TYPE_CALORIES_EXPENDED);

        responseCalories.addOnSuccessListener(new OnSuccessListener<DataSet>() {
            @Override
            public void onSuccess(DataSet dataSet) {
                TextView text = (TextView)findViewById(R.id.item_cal_countId);
                long total = dataSet.isEmpty() ? 0 : (long) dataSet.getDataPoints().get(0).getValue(Field.FIELD_CALORIES).asFloat();

                text.setText(String.valueOf(total));
            }
        });

        responseCalories.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "There was a problem getting the step count.", e);
            }
        });


        Task<DataSet> responseDistance =   Fitness.getHistoryClient(this, GoogleSignIn.getLastSignedInAccount(this))
                .readDailyTotal(DataType.AGGREGATE_DISTANCE_DELTA);

        responseDistance.addOnSuccessListener(new OnSuccessListener<DataSet>() {
            @Override
            public void onSuccess(DataSet dataSet) {
                TextView text = (TextView)findViewById(R.id.item_distance_countId);
                long total = dataSet.isEmpty() ? 0 : (long) dataSet.getDataPoints().get(0).getValue(Field.FIELD_DISTANCE).asFloat();
                DecimalFormat df = new DecimalFormat("#,####");

                text.setText(String.valueOf( df.format(total/1000)));
            }
        });

        responseDistance.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "There was a problem getting the step count.", e);
            }
        });






        // [START find_data_sources]
        // Note: Fitness.SensorsApi.findDataSources() requires the ACCESS_FINE_LOCATION permission.
//        Fitness.getSensorsClient(this, GoogleSignIn.getLastSignedInAccount(this))
//                .findDataSources(
//                        new DataSourcesRequest.Builder()
//                                .setDataTypes(DataType.TYPE_STEP_COUNT_CUMULATIVE)
//                                .setDataSourceTypes(DataSource.TYPE_RAW) //необработанные данные с датчика или внешнего источника
//                                .build())
//                .addOnSuccessListener(
//                        new OnSuccessListener<List<DataSource>>() {
//                            @Override
//                            public void onSuccess(List<DataSource> dataSources) {
//                                for (DataSource dataSource : dataSources) {
//
//                                    Log.i(TAG, "Data source found: " + dataSource.toString());
//                                    Log.i(TAG, "Data Source type: " + dataSource.getDataType().getName());
//                                    Log.i(TAG, "Device type: " + dataSource.getDevice());
//                                    // Let's register a listener to receive Activity data!
//                                    if (dataSource.getDataType().equals(DataType.TYPE_STEP_COUNT_CUMULATIVE)
//                                            && mListener == null) {
//                                        Log.i(TAG, "Data source for TYPE_STEP_COUNT_CUMULATIVE found!  Registering.");
//                                        registerFitnessDataListener(dataSource, DataType.TYPE_STEP_COUNT_CUMULATIVE);
//                                    }
//                                }
//                            }
//                        })
//                .addOnFailureListener(
//                        new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                Log.e(TAG, "failed", e);
//                            }
//                        });
        // [END find_data_sources]

//        //сформируем запрос для поиска источника
//    DataSourcesRequest data_source_request = new DataSourcesRequest.Builder().setDataTypes(DataType.TYPE_STEP_COUNT_DELTA).
//            setDataSourceTypes(DataSource.TYPE_RAW).build();//DataSource.TYPE_RAW - необработанные данные с датчика или внешнего источника
//    //получим наши данные в соответствии с запросом выше
//    Task<List<DataSource>> data_source_result = Fitness.getSensorsClient(this,GoogleSignIn.getLastSignedInAccount(this)).findDataSources(data_source_request);
//    //повесим обработчик в случае успешного получения данных
//        data_source_result.addOnSuccessListener(new OnSuccessListener<List<DataSource>>() {
//        @Override
//        public void onSuccess(List<DataSource> dataSources) {
//            for (DataSource dataSource : dataSources)
//            {
//                Log.i(TAG, "Data source found: " + dataSource.toString());
//                Log.i(TAG, "Data Source type: " + dataSource.getDataType().getName());
//
//                if (dataSource.getDataType().equals(DataType.TYPE_STEP_COUNT_DELTA) && mListener == null)
//                {
//                    Log.i(TAG, "Data source for TYPE_STEP_COUNT_DELTA found!  Registering.");
//                    registerFitnessDataListener(dataSource, DataType.TYPE_STEP_COUNT_DELTA);
//                }
//            }
//        }
//    });
//    //повесим обработчик в случае неудачного получения данных
//        data_source_result.addOnFailureListener(new OnFailureListener() {
//        @Override
//        public void onFailure(@NonNull Exception e) {
//            Log.e(TAG, "failed", e);
//        }
//    });
}


//    /**
//     *  Регистрация слушателя для Sensors API для {@link DataSource} and {@link
//     * DataType} combo.
//     */
//    private void registerFitnessDataListener(DataSource dataSource, DataType dataType) {
//        // [START register_data_listener]
//        mListener = new OnDataPointListener() {
//            @Override
//            public void onDataPoint(DataPoint dataPoint) {
//                for (Field field : dataPoint.getDataType().getFields())
//                {
//                    Value val = dataPoint.getValue(field);
//                    Log.i(TAG, "Detected DataPoint field: " + field.getName());
//                    Log.i(TAG, "Detected DataPoint value: " + val);
//                }
//            }
//        };
//
//        SensorRequest sensorRequest = new SensorRequest.Builder()
//                .setDataSource(dataSource)// Опционально, но рекомендуемо для custom data sets.
//                .setDataType(dataType) //обязательно!
//                .setSamplingRate(10, TimeUnit.SECONDS)
//                .build();
//
//        Task<Void> data_source_result =  Fitness.getSensorsClient(this, GoogleSignIn.getLastSignedInAccount(this))
//                .add(sensorRequest,mListener);
//
//        //регистрация слушателя
//        data_source_result.addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                if (task.isSuccessful()) {
//                    Log.i(TAG, "Listener registered!");
//                } else {
//                    Log.e(TAG, "Listener not registered.", task.getException());
//                }
//            }
//        });
//    }


//    /** Records step data by requesting a subscription to background step data. */
//    public void subscribe() {
//        // To create a subscription, invoke the Recording API. As soon as the subscription is
//        // active, fitness data will start recording.
//        Fitness.getRecordingClient(this, GoogleSignIn.getLastSignedInAccount(this))
//                .subscribe(DataType.TYPE_STEP_COUNT_CUMULATIVE)
//                .addOnCompleteListener(
//                        new OnCompleteListener<Void>() {
//                            @Override
//                            public void onComplete(@NonNull Task<Void> task) {
//                                if (task.isSuccessful()) {
//                                    Log.i(TAG, "Successfully subscribed!");
//                                } else {
//                                    Log.w(TAG, "There was a problem subscribing.", task.getException());
//                                }
//                            }
//                        });
//    }

    /**
     * Reads the current daily step total, computed from midnight of the current day on the device's
     * current timezone.
     */
    private void readData() {
        Fitness.getHistoryClient(this, GoogleSignIn.getLastSignedInAccount(this))
                .readDailyTotal(DataType.TYPE_STEP_COUNT_DELTA)
                .addOnSuccessListener(
                        new OnSuccessListener<DataSet>() {
                            @Override
                            public void onSuccess(DataSet dataSet) {
                                long total =
                                        dataSet.isEmpty()
                                                ? 0
                                                : dataSet.getDataPoints().get(0).getValue(Field.FIELD_STEPS).asInt();
                                Log.i(TAG, "Total steps: " + total);
                            }
                        })
                .addOnFailureListener(
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "There was a problem getting the step count.", e);
                            }
                        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the main; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.fitapi_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_read_data) {
            readData();
            return true;
        }
        if (id == R.id.action_exit)
        {
            Fitness.getConfigClient(this, GoogleSignIn.getLastSignedInAccount(this)).disableFit();
            return true;
        }
        if (id == R.id.action_exit_google) //see https://developers.google.com/identity/sign-in/android/disconnect
        {
            mGoogleSignInClient.signOut()
                    .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            // ...
                        }
                    });
            return true;
        }
        if (id == android.R.id.home)
        {
            this.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    /** Initializes a custom log class that outputs both to in-app targets and logcat. */
    private void initializeLogging() {
//        // Wraps Android's native log framework.
//        LogWrapper logWrapper = new LogWrapper();
//        // Using Log, front-end to the logging chain, emulates android.util.log method signatures.
//        Log.setLogNode(logWrapper);
//        // Filter strips out everything except the message text.
//        MessageOnlyLogFilter msgFilter = new MessageOnlyLogFilter();
//        logWrapper.setNext(msgFilter);
//        // On screen logging via a customized TextView.
//        LogView logView = (LogView) findViewById(R.id.sample_logview);
//
//        // Fixing this lint error adds logic without benefit.
//        // noinspection AndroidLintDeprecation
//      //  logView.setTextAppearance(R.style.Log);
//
//        logView.setBackgroundColor(Color.WHITE);
//        msgFilter.setNext(logView);
//        Log.i(TAG, "Ready");
    }


    @Override
    protected void onResume() {
        super.onResume();

        // This ensures that if the user denies the permissions then uses Settings to re-enable
        // them, the app will start working.
       // findFitnessDataSourcesWrapper();
    }


    //построение графика
    public void OnButtonClick(View view) {

        int id = view.getId();

        switch (id){

            case R.id.item_card_stepsId: readHistoryData(); break;
            case R.id.item_cal_countId: break;
            case R.id.item_distance_countId: break;
        }


    }

    private static  DataReadRequest queryFitnessData() {
        Calendar cal = Calendar.getInstance();

        Date now = new Date();
        cal.setTime(now);
//        cal.add(Calendar.DAY_OF_WEEK, -1);
        long endTime = cal.getTimeInMillis();
        cal.add(Calendar.DAY_OF_WEEK, -1);
        long startTime = cal.getTimeInMillis();


        DataReadRequest readRequest =
                new DataReadRequest.Builder()
                        // The data request can specify multiple data types to return, effectively
                        // combining multiple data queries into one call.
                        // In this example, it's very unlikely that the request is for several hundred
                        // datapoints each consisting of a few steps and a timestamp.  The more likely
                        // scenario is wanting to see how many steps were walked per day, for 7 days.
                        .aggregate(DataType.TYPE_STEP_COUNT_DELTA, DataType.AGGREGATE_STEP_COUNT_DELTA)
                        // Analogous to a "Group By" in SQL, defines how data should be aggregated.
                        // bucketByTime allows for a time span, whereas bucketBySession would allow
                        // bucketing by "sessions", which would need to be defined in code.
                        .bucketByTime(1, TimeUnit.DAYS)
                        .setTimeRange(startTime, endTime, TimeUnit.MILLISECONDS)
                        .build();

    return readRequest;

    }

    private Task<DataReadResponse> readHistoryData() {
        // Begin by creating the query.
        DataReadRequest readRequest = queryFitnessData();

        // Invoke the History API to fetch the data with the query
        return Fitness.getHistoryClient(this, GoogleSignIn.getLastSignedInAccount(this))
                .readData(readRequest)
                .addOnSuccessListener(
                        new OnSuccessListener<DataReadResponse>() {
                            @Override
                            public void onSuccess(DataReadResponse dataReadResponse) {
                                // For the sake of the sample, we'll print the data so we can see what we just
                                // added. In general, logging fitness information should be avoided for privacy
                                // reasons.
                                printData(dataReadResponse);

                            }
                        })
                .addOnFailureListener(
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.e(TAG, "There was a problem reading the data.", e);
                            }
                        });
    }



    public void printData(DataReadResponse dataReadResult) {
//        GraphView graph = (GraphView) findViewById(R.id.graph); //http://www.android-graphview.org/bar-chart/ (Рисовалка графиков)
//
//        BarGraphSeries<com.jjoe64.graphview.series.DataPoint> series = new BarGraphSeries<>();
//
//        // [START parse_read_data_result]
//        // If the DataReadRequest object specified aggregated data, dataReadResult will be returned
//        // as buckets containing DataSets, instead of just DataSets.
//        if (dataReadResult.getBuckets().size() > 0) {
//            Log.i(
//                    TAG, "Number of returned buckets of DataSets is: " + dataReadResult.getBuckets().size());
//            for (Bucket bucket : dataReadResult.getBuckets()) {
//
//                List<DataSet> dataSets = bucket.getDataSets();
//                //наборы точек (data points), принадлежащих определенному источнику данных (датчику)
//                for (DataSet dataSet : dataSets) {
//
//                    String name = dataSet.getDataType().getName();
//                    List<com.jjoe64.graphview.series.DataPointInterface> points = new ArrayList<>();
//
//                    //Data Points — отметки фитнес-замеров, содержащие привязку данных ко времени замера.
//                    for (DataPoint dp : dataSet.getDataPoints())
//                    {
//
//                        String msg = "dataPoint: "
//                                + "type: " + dp.getDataType().getName() + "\n" + ", range: [" +  DateFormat.getDateInstance() .format(dp.getStartTime(TimeUnit.MILLISECONDS)) + "-" +  DateFormat.getDateInstance() .format(dp.getEndTime(TimeUnit.MILLISECONDS)) + "]\n"
//                                + ", fields: [";
//
//
//                        for (Field field : dp.getDataType().getFields())
//                        {
//                            msg += field.getName() + "=" + dp.getValue(field) + " ";
//                            msg += "]";
//
//
//                            DataSource ds = dp.getOriginalDataSource();
//                            String strName = ds.getName();
//                            int val = dp.getValue(field).asInt();
//
////                            com.jjoe64.graphview.series.DataPointInterface point = new com.jjoe64.graphview.series.DataPoint(dp.getStartTime(TimeUnit.MINUTES),val);
////                            points.add(point);
////                            series.appendData((com.jjoe64.graphview.series.DataPoint) point,true,1);
//                            int n =1;
//                        }
//
//
//                    }
//
//
//
//                }
//            }
//        } else if (dataReadResult.getDataSets().size() > 0) {
//            Log.i(TAG, "Number of returned DataSets is: " + dataReadResult.getDataSets().size());
//            for (DataSet dataSet : dataReadResult.getDataSets()) {
////                dumpDataSet(dataSet);
//            }
//        }
//        graph.addSeries(series);

        // [END parse_read_data_result]
    }
}
