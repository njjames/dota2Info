package com.nj.dota2info.acticity;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.nj.dota2info.R;
import com.nj.dota2info.adapter.InfoRecycleAdapter;
import com.nj.dota2info.gson.Hero;
import com.nj.dota2info.util.DataUtil;
import com.nj.dota2info.util.HttpUtil;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private List<Hero> mHeroList;
    private RecyclerView mRvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        TextView tvTitle = findViewById(R.id.tv_title);
//        tvTitle.setText("英雄");
        mRvInfo = findViewById(R.id.rv_info);
        Toolbar tlTitle = findViewById(R.id.tl_title);
        tlTitle.setTitle("英雄");
        setSupportActionBar(tlTitle);

        requestHeroinfo();

    }

    private void requestHeroinfo() {
        HttpUtil.sendOkHttpRequest("http://db.dota2.uuu9.com/API/GetHeroList.ashx", new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String heroListString = response.body().string();
                if (!TextUtils.isEmpty(heroListString)) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mHeroList = DataUtil.handleHeroListResponse(heroListString);
                            showHeroInfo();
                        }
                    });
                }else {
                    Toast.makeText(MainActivity.this, "加载失败...", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "加载失败...", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void showHeroInfo() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        mRvInfo.setLayoutManager(gridLayoutManager);
        InfoRecycleAdapter infoRecycleAdapter = new InfoRecycleAdapter(mHeroList);
        mRvInfo.setAdapter(infoRecycleAdapter);
    }
}
