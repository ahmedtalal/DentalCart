package com.example.dentalcart.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dentalcart.R;
import com.example.dentalcart.Repositories.FirebaseOperations;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashscreenActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        TimerTask timerTask =  new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(SplashscreenActivity.this , RegisterActivity.class));
                finish();
            }
        };

        new Timer().schedule(timerTask , 1300);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
