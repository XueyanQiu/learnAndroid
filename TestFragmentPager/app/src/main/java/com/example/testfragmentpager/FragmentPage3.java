package com.example.testfragmentpager;

import android.os.Bundle;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;


public class FragmentPage3 extends Fragment {

   private Integer counter = 0;

    public FragmentPage3() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_page3, container, false);
        final Button btn_1 = view.findViewById(R.id.btn_page3_btn1);
        NestedScrollView scrollView = view.findViewById(R.id.scrollView_page3);
        scrollView.scrollTo(0,0);
        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter++;
                btn_1.setText(counter.toString());
            }
        });

        return view;
    }
}