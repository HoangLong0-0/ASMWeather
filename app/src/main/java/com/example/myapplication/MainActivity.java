package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView tvIconPhrase,tvTemperature;
    RecyclerView recyclerView;
    List<Weather> listWeather;
    HomeAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvIconPhrase = findViewById(R.id.tvIconPhrase);
        tvTemperature = findViewById(R.id.tvTemperature);
        //B1 : get listdata
        getData();

        //B2:
        listWeather = new ArrayList<>();
        adapter = new HomeAdapter(this,listWeather);

        //B3:
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);

        //B4:
        recyclerView = findViewById(R.id.rvListTemperature);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


        getData();
    }
    private void getData(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIManager.SEVER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIManager service = retrofit.create(APIManager.class);
        service.apiGetData().enqueue(new Callback<List<Weather>>() {
            @Override
            public void onResponse(Call<List<Weather>> call, Response<List<Weather>> response) {
                Log.d("TEST","Onrespon" + response);
                if(response.body()!=null){
                    listWeather = response.body();
                    Weather item = listWeather.get(0);
                    String temperature = item.getTemperature().getValue() + " °C";
                    tvTemperature.setText(temperature);
                    tvIconPhrase.setText(item.getIconPhrase());
                }
            }

            @Override
            public void onFailure(Call<List<Weather>> call,Throwable t) {
                Log.d("TEST","Onfail" + t.getMessage());
            }
        });
    }
}