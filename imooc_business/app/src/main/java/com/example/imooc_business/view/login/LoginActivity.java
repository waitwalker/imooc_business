package com.example.imooc_business.view.login;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.imooc_business.R;
import com.example.imooc_business.api.RequestCenter;
import com.example.imooc_business.manager.UserManager;
import com.example.imooc_business.model.login.LoginEvent;
import com.example.imooc_business.model.user.User;
import com.example.lib_common_ui.base.BaseActivity;
import com.example.lib_network.listener.DisposeDataListener;

import org.greenrobot.eventbus.EventBus;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViewById(R.id.login_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestCenter.login(new DisposeDataListener() {
                    @Override
                    public void onSuccess(Object responseObj) {
                        Log.d("1","登录成功");
                        User user = (User) responseObj;
                        UserManager.getInstance().saveUser(user);
                        // event bus 发射一个事件
                        EventBus.getDefault().post(new LoginEvent());
                        finish();
                    }

                    @Override
                    public void onFailure(Object responseObj) {
                        Log.d("1","登录失败");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(LoginActivity.this,"登录失败",Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });
            }
        });
    }
}