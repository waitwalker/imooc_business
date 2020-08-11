package com.example.imooc_business.view.discory;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.imooc_business.R;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DiscoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DiscoryFragment extends Fragment {

    Context mContext;
    public static DiscoryFragment newInstance() {
        DiscoryFragment fragment = new DiscoryFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_discory, container, false);
    }
}