package com.nj.dota2info.acticity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.nj.dota2info.R;
import com.nj.dota2info.view.PropertyView;

public class HeroContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_content);

        PropertyView propertyDps = findViewById(R.id.property_dps);
    }
}
