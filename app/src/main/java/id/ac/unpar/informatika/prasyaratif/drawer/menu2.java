package com.suryoatmojo.cobacodingdrawer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.suryoatmojo.cobacodingdrawer.ui.menu2.Menu2Fragment;

public class menu2 extends AppCompatActivity {
    EditText celcius,kelvin,farenheit,reamur; //Deklarasi variable
    Button btn_konversi; //deklarasi varaibel
    private Object View;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu2_fragment);



       if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, Menu2Fragment.newInstance())
                    .commitNow();
        }
    }




    //public View konversisuhu(View view) {
    //    kelvin = findViewById(R.id.edtxt_kelvin);
    //    btn_konversi = (Button) view.findViewById(R.id.btn_konvert);
    //    btn_konversi.setOnClickListener(new View.OnClickListener() {
    //        @Override
    //        public void onClick(View view) {
    //            kelvin.setText("Welcome to android");
    //        }
    //    });
    //    return view;

    //}
}
