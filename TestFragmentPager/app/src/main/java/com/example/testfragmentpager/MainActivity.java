package com.example.testfragmentpager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {

    private ArrayList<String> main_tabs = new ArrayList<>();
    private ArrayList<Fragment> fragmentList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ViewPager viewPager;
    private MainTabAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //View viewPage1 = LayoutInflater.from(this).inflate(R.layout.fragment_page1, null);
        fragmentList.add(new FragmentPage1());
        fragmentList.add(new FragmentPage2());
        fragmentList.add(new FragmentPage3());
        fragmentList.add(new FragmentPage4());
        viewPager = findViewById(R.id.main_viewPager);
        MyFragmentPagerAdapter pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),fragmentList);
        viewPager.setAdapter(pagerAdapter);


        addTabs();
        recyclerView = findViewById(R.id.main_recyclerView_tabs);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MainTabAdapter(main_tabs,this);
        recyclerView.setAdapter(adapter);

        adapter.setBg(0);
        adapter.setSendDataToFragment(new MainTabAdapter.SendDataToFragment() {
            @Override
            public void sendData(int position) {
                viewPager.setCurrentItem(position);
            }
        });




        // ViewPager 的滑动监听方法
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) { }

            @Override
            public void onPageSelected(int i) {
                recyclerView.smoothScrollToPosition(i);
                // 改变 RecyclerView 的选中条目
                adapter.setBg(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) { }
        });
    }


    private void addTabs(){
        main_tabs.add("Artical");
        main_tabs.add("Recycler Stream");
        main_tabs.add("Buttons");
        main_tabs.add("Image");
    }
}