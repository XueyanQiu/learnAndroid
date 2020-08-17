package com.example.personalview;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CakeView cakeView = findViewById(R.id.cakeview_1);
        List<CakeBean> beanList = new ArrayList<>();
        beanList.add(new CakeBean("item1", 100*(float)Math.random(), Color.RED));
        beanList.add(new CakeBean("item2", 100*(float)Math.random(), Color.GRAY));
        beanList.add(new CakeBean("item3", 100*(float)Math.random(), Color.GREEN));
        beanList.add(new CakeBean("item4", 100*(float)Math.random(), Color.BLUE));
        beanList.add(new CakeBean("item5", 100*(float)Math.random(), Color.YELLOW));
        cakeView.setData(beanList);
        Log.d("main", "onCreate: ");

        TextView tv_item8 = findViewById(R.id.tv_item8);
        Log.d("main", "item8 width: "+ tv_item8.getWidth());
    }
}