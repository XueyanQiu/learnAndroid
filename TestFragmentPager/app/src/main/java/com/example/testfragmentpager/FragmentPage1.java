package com.example.testfragmentpager;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class FragmentPage1 extends Fragment {



    public FragmentPage1() {
        // Required empty public constructor
    }





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_page1,container,false);
        TextView tv_title =  view.findViewById(R.id.tv_page1_title);
        tv_title.setText("This is title");
        ImageView iv_img = view.findViewById(R.id.iv_page1_img);
        iv_img.setImageResource(R.drawable.ic_launcher_foreground);
        TextView tv_content = view.findViewById(R.id.tv_page1_content);
        tv_content.setText("This is content.\t This is content!\n This is content.\t This is content!\nThis is content.\t This is content!\nThis is content.\t This is content!\nThis is content.\t This is content!\nThis is content.\t This is content!\n");
        return view;
    }
}