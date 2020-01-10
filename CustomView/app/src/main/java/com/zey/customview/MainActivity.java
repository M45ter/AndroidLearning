package com.zey.customview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zey.customview.view.OneActivity;
import com.zey.customview.viewgroup.ThreeActivity;
import com.zey.customview.viewgroupspecial.FourActivity;
import com.zey.customview.viewspecial.TwoActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_1).setOnClickListener(v -> {
            startActivity(new Intent(this, OneActivity.class));
        });

        findViewById(R.id.btn_2).setOnClickListener(v -> {
            startActivity(new Intent(this, TwoActivity.class));
        });

        findViewById(R.id.btn_3).setOnClickListener(v -> {
            startActivity(new Intent(this, ThreeActivity.class));
        });

        findViewById(R.id.btn_4).setOnClickListener(v -> {
            startActivity(new Intent(this, FourActivity.class));
        });
    }
}
