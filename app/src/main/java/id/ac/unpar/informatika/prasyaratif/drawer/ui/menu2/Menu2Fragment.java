package com.suryoatmojo.cobacodingdrawer.ui.menu2;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.suryoatmojo.cobacodingdrawer.R;

public class Menu2Fragment extends Fragment {

    EditText celcius,kelvin,farenheit,reamur; //Deklarasi variable
    Button btn_konversi; //deklarasi varaibel

    public static Menu2Fragment newInstance() {
        return new Menu2Fragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.menu2_fragment, container, false);
        kelvin = root.findViewById(R.id.edtxt_kelvin);
        btn_konversi = root.findViewById(R.id.btn_konvert);
        btn_konversi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kelvin.setText("Welcome to android");
            }
        });



        //final TextView textView = root.findViewById(R.id.text_gallery);
        return root;
    }

}
