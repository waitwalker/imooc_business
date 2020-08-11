package com.example.imooc_business;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import net.lucode.hackware.magicindicator.MagicIndicator;

public class HomeActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        transferredParameter();
        initData();
        initView();

    }

    private void initData() {}

    private void initView() {}

    private void transferredParameter() {
        Intent intent = getIntent();
        if (intent != null) {
            String string = intent.getStringExtra("name");
            Log.d("1","正向传过来的参数:" + string);
        }
    }

    private void back() {
        Intent intent = new Intent();
        intent.putExtra("value", "Home");
        setResult(1234,intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        back();
        super.onBackPressed();
    }
}