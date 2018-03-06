package com.nj.dota2info.acticity;


import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.Toast;

import com.nj.dota2info.R;
import com.nj.dota2info.adapter.CategoryAdapter;
import com.nj.dota2info.adapter.InfoRecycleAdapter;
import com.nj.dota2info.gson.Hero;
import com.nj.dota2info.util.DataUtil;
import com.nj.dota2info.util.HttpUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private List<Hero> mHeroList;
    private RecyclerView mRvInfo;
    private DrawerLayout mDrawerLayout;
    private GridView mGcCategory;
    private String[] mCategorys = new String[]{
            "力量", "敏捷", "智力",
            "近战", "远程", "辅助", "法师", "打野", "Gank", "肉盾", "隐身", "治疗", "召唤", "眩晕", "沉默", "减速", "爆发", "闪烁", "后期", "控制"
    };
    private List<String> mCategoryList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initdata();
//        TextView tvTitle = findViewById(R.id.tv_title);
//        tvTitle.setText("英雄");
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mRvInfo = findViewById(R.id.rv_info);
        mGcCategory = findViewById(R.id.gv_category);
        CategoryAdapter categoryAdapter = new CategoryAdapter(this, mCategoryList);
        mGcCategory.setAdapter(categoryAdapter);
        Toolbar tlTitle = findViewById(R.id.tl_title);
        tlTitle.setTitle("英雄");
        setSupportActionBar(tlTitle);
        //set和get的都是supportActionBar而不是getActionBar()
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_jiuguan);
        }

        requestHeroinfo();

    }

    private void initdata() {
        for (int i = 0; i < mCategorys.length; i++) {
            mCategoryList.add(mCategorys[i]);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
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
