package com.twopai.diamondupanddownanimdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class DiamondUpAndDownAnimActivity extends AppCompatActivity {

    private DiamondAnimLayout diamondAnimLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diamondanimlayout);
        diamondAnimLayout = (DiamondAnimLayout) findViewById(R.id.diamondAnimLayout);

    }

    public void stopAnim(View view) {
        diamondAnimLayout.stopAnim(this);
    }

    public void startAnim(View view) {
        diamondAnimLayout.startAnim();
    }
}
