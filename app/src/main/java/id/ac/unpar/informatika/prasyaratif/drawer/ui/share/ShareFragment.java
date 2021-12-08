package com.suryoatmojo.cobacodingdrawer.ui.share;

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

public class ShareFragment extends Fragment {



    public static ShareFragment newInstance() {
        return new ShareFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.share_fragment, container, false);
        final TextView textView = root.findViewById(R.id.text_share);
        return root;
    }

}
