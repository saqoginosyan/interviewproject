package com.example.hp.sargisapp.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.hp.sargisapp.R;
import com.example.hp.sargisapp.client.RetrofitClient;
import com.example.hp.sargisapp.client.RetrofitInterface;
import com.example.hp.sargisapp.adapter.UserAdapter;
import com.example.hp.sargisapp.api.ApiResponse;
import com.example.hp.sargisapp.api.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView userList = findViewById(R.id.user_recycler);

        final UserAdapter adapter = new UserAdapter(getBaseContext());
        userList.setLayoutManager(new LinearLayoutManager(this));
        userList.setAdapter(adapter);

        RetrofitInterface client = RetrofitClient.getClient().create(RetrofitInterface.class);

        client.fetchUsers(3).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(@NonNull Call<ApiResponse> call, @NonNull Response<ApiResponse> response) {
                List<Result> list = response.body().getResults();
                adapter.setData(list);
            }

            @Override
            public void onFailure(@NonNull Call<ApiResponse> call, @NonNull Throwable t) {
                Log.e("Main", t.toString());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
