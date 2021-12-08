package com.suryoatmojo.cobacodingdrawer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.suryoatmojo.cobacodingdrawer.ui.menu1.Menu1Fragment;

public class menu1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu1_fragment);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, Menu1Fragment.newInstance())
                    .commitNow();
        }
    }
}
