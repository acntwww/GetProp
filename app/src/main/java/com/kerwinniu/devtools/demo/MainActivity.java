package com.kerwinniu.devtools.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.kerwinniu.devtools.SystemProperties;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mTvValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnReadProp).setOnClickListener(this);
        mTvValue = findViewById(R.id.tvResult);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnReadProp) {
            doSomething();
        }
    }

    private void doSomething() {
        String condition = SystemProperties.read("haha");
        if ("success".equalsIgnoreCase(condition)) {
            onSuccess();
        } else {
            onFailed();
        }
        showResult(condition);
    }

    private void onSuccess() {
        showResult("success!!");
    }

    private void onFailed() {
        showResult("failed!!");
    }

    private void showResult(String val) {
        String text = "Result: " + val;
        mTvValue.setText(text);
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
