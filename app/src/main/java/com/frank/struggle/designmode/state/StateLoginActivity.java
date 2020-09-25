package com.frank.struggle.designmode.state;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.frank.struggle.R;

/**
 * @author maowenqiang
 * @desc
 */
public class StateLoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state_login);
        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
                finish();
            }
        });
    }

    private void login() {
        // 登录处理 省略，成功后修改状态
        LoginContext.getLoginContext().setState(new LoginedState());
        Toast.makeText(getApplicationContext(), "登录成功!", Toast.LENGTH_SHORT).show();
    }
}
