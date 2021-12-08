package com.suryoatmojo.cobacodingdrawer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.suryoatmojo.cobacodingdrawer.ui.share.ShareFragment;

public class share extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share_fragment);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, ShareFragment.newInstance())
                    .commitNow();
        }
    }
}
