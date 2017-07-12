package com.example.administrator.yunifang.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.yunifang.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class A extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = View.inflate(getActivity(), R.layout.fragment_a ,null);
        return inflate;
    }

}
