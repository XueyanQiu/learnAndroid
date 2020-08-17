package com.example.testfragmentpager;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class FragmentPage2 extends Fragment {

    private ArrayList<Page2Item> items = new ArrayList<>();

    private void addItemList(){
        for(int i= 0; i<3; i++){

            StringBuilder content = new StringBuilder("This is the content\t");
            int rnd = (int)(Math.random()*100);
            for(int j = 0; j<rnd; j++){
                content.append("Content repeat time: "+j+".\t");
            }
            int imageId = R.drawable.ic_launcher_foreground;
            Page2Item item = new Page2Item(imageId,content.toString());
            items.add(item);
        }
    }
    public FragmentPage2() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        addItemList();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // addItemList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_page2, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.fragment2_recyclerview);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        //addItemList();
        Page2ItemAdapter adapter = new Page2ItemAdapter(items);
        recyclerView.setAdapter(adapter);

        return view;
    }


    /*** Adapter class */
    class Page2ItemAdapter extends RecyclerView.Adapter<Page2ItemAdapter.ViewHolder>{

        private List<Page2Item> itemList;

        public Page2ItemAdapter(ArrayList<Page2Item> itemList){
            this.itemList = itemList;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_page2_item_layout,parent,false);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Page2Item item = itemList.get(position);
            holder.iv_img.setImageResource(item.getImgId());
            holder.tv_name.setText(item.getName());
        }

        @Override
        public int getItemCount() {
            return itemList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{
            ImageView iv_img;
            TextView tv_name;
            public ViewHolder(View view){
                super(view);
                iv_img = view.findViewById(R.id.iv_page2_recycler_item_img);
                tv_name = view.findViewById(R.id.tv_page2_recycler_item_name);
            }
        }
    }
}