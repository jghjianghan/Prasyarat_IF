package com.suryoatmojo.cobacodingdrawer.ui.menu1;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.suryoatmojo.cobacodingdrawer.R;

public class Menu1Fragment extends Fragment {



    public static Menu1Fragment newInstance() {
        return new Menu1Fragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.menu1_fragment, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        return root;
    }


}
