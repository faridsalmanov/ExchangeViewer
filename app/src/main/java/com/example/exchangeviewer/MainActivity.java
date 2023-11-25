package com.example.exchangeviewer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.exchangeviewer.utilities.ApiDataReader;
import com.example.exchangeviewer.utilities.AsyncDataLoader;
import com.example.exchangeviewer.utilities.Constants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private ListView lvItems;
    private TextView tvStatus;
    private ArrayAdapter listAdapter;
    private Switch swUseAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.lvItems = findViewById(R.id.lv_items);
        this.tvStatus = findViewById(R.id.tv_status);
        this.swUseAsyncTask = findViewById(R.id.sw_use_async_task);

        this.listAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, new ArrayList<>());
        this.lvItems.setAdapter(this.listAdapter);
    }

    public void onBtnGetDataClick(View view) {
        this.tvStatus.setText(R.string.loading_data);
        if(this.swUseAsyncTask.isChecked()){
            getDataByAsyncTask();
            Toast.makeText(this, R.string.msg_using_async_task, Toast.LENGTH_LONG).show();
        }
    }

    public void getDataByAsyncTask(){
        new AsyncDataLoader() {
            @Override
            public void onPostExecute(String result) {
                tvStatus.setText(getString(R.string.data_loaded) + result);
            }
        }.execute(Constants.FLOATRATES_API_URL);

    }
}