package com.example.imooc_business;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;

import com.example.imooc_business.view.home.HomeActivity;

import org.greenrobot.eventbus.EventBus;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // EventBus注册监听
        EventBus.getDefault().register(this);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                Intent intent = new Intent(this, HomeActivity.class);
                intent.putExtra("name","zhangsan");
                startActivityForResult(intent,123);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            String string = data.getStringExtra("value");
            Log.d("1","反向传递的参数:" + string);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // EventBus 取消监听
        EventBus.getDefault().unregister(this);
    }
}