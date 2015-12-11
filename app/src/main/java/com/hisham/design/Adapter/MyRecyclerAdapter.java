package com.hisham.design.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hisham.design.Constent.AppConstent;
import com.hisham.design.R;
import com.hisham.design.ThemeSelectActivity;
import com.hisham.design.model.RecyclerViewModel;

import java.util.List;

/**
 * Created by faisal on 12/7/2015.
 *
  *<div class="jd-descr">

 <h2 style="margin-bottom: 0px;">Class Overview</h2><hr>
 <p itemprop="articleBody">This is adapter class using by RecyclerView. In the class constructor we are passing context and custom list for more details you can refer <code><a href="http://developer.android.com/reference/android/support/v7/widget/RecyclerView.Adapter.html">RecyclerView.Adapter</a></code> </p>


 </div>
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.CustomViewHolder> {
    private List<RecyclerViewModel> recyclerViewModelList;
    private Context mContext;

    public MyRecyclerAdapter(Context context, List<RecyclerViewModel> recyclerViewModelList) {
        this.recyclerViewModelList = recyclerViewModelList;
        this.mContext = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_row, null);

        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, final int i) {
        RecyclerViewModel recyclerViewModel = recyclerViewModelList.get(i);
        customViewHolder.textView.setText(recyclerViewModel.getTitle());
        customViewHolder.imageView.setImageResource(recyclerViewModel.getThumbnail());

        customViewHolder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callAppropriateCode(i);
            }
        });
        customViewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callAppropriateCode(i);
            }
        });

    }

    private void callAppropriateCode(int value) {
        Toast.makeText(mContext, value+"", Toast.LENGTH_LONG).show();
        SharedPreferences sharedPreferences=mContext.getSharedPreferences(AppConstent.APP_THEME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        switch(value){
            case AppConstent.THEME_ONE:
                editor.putInt(AppConstent.APP_THEME_SELECTION, AppConstent.THEME_ONE);
                break;
            case AppConstent.THEME_TWO:
                editor.putInt(AppConstent.APP_THEME_SELECTION, AppConstent.THEME_TWO);
                break;
            case AppConstent.THEME_THREE:
                editor.putInt(AppConstent.APP_THEME_SELECTION, AppConstent.THEME_THREE);
                break;
            case AppConstent.THEME_FOUR:
                editor.putInt(AppConstent.APP_THEME_SELECTION, AppConstent.THEME_FOUR);
                break;
            case AppConstent.THEME_FIVE:
                editor.putInt(AppConstent.APP_THEME_SELECTION, AppConstent.THEME_FIVE);
                break;
        }

        editor.commit();
        Intent intent=new Intent(mContext, ThemeSelectActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        mContext.startActivity(intent);
        ((ThemeSelectActivity)mContext).overridePendingTransition(0, 0);
        ((ThemeSelectActivity)mContext).finish();
    }

    @Override
    public int getItemCount() {
        return (null != recyclerViewModelList ? recyclerViewModelList.size() : 0);
    }



    class CustomViewHolder extends RecyclerView.ViewHolder {
        protected ImageView imageView;
        protected TextView textView;

        public CustomViewHolder(View view) {
            super(view);
            this.imageView = (ImageView) view.findViewById(R.id.thumbnail);
            this.textView = (TextView) view.findViewById(R.id.title);
        }
    }


    
}
