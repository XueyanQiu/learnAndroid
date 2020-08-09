package com.example.testfragmentpager;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainTabAdapter extends RecyclerView.Adapter<MainTabAdapter.ViewHolder> {

    private ArrayList<String> tabs;
    private Context mcontext;
    private List<Boolean> isClicked;

    public MainTabAdapter(ArrayList<String> tabs, Context context){
        this.tabs = tabs;
        mcontext = context;
        isClicked = new ArrayList<>();
        for(int i = 0; i<tabs.size(); i++){
            isClicked.add(false);
        }

    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_tab;
        public ViewHolder(View view){
            super(view);
            tv_tab = view.findViewById(R.id.tv_main_recycler_item);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_main_tab_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        if (isClicked.get(position)){
            holder.tv_tab.setTextColor(mcontext.getResources().getColor(R.color.colorWhite));
            holder.tv_tab.setBackgroundColor(mcontext.getResources().getColor(R.color.colorPrimaryDark));
        }else {
            holder.tv_tab.setTextColor(mcontext.getResources().getColor(R.color.colorBlack));
            holder.tv_tab.setBackgroundColor(mcontext.getResources().getColor(R.color.colorGrey));
        }

        String str = tabs.get(position);
        holder.tv_tab.setText(str);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mSendDataToFragment!=null){
                    mSendDataToFragment.sendData(position);
                    for (int j = 0; j < isClicked.size(); j++) {
                        isClicked.set(j,false);
                    }
                    isClicked.set(position,true);
                    notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return tabs.size();
    }

    SendDataToFragment mSendDataToFragment;

    public void setSendDataToFragment(SendDataToFragment sendDataToFragment) {
        mSendDataToFragment = sendDataToFragment;
    }

    public interface SendDataToFragment{
        void sendData(int position);
    }

    public void setBg(int i) {
        for (int j = 0; j < isClicked.size(); j++) {
            isClicked.set(j,false);
        }
        isClicked.set(i,true);
        notifyDataSetChanged();
    }
}
