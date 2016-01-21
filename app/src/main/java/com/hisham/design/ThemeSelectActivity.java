package com.hisham.design;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.hisham.design.Adapter.MyRecyclerAdapter;
import com.hisham.design.model.RecyclerViewModel;
import java.util.ArrayList;
import java.util.List;



/**
 * Created by faisal on 12/7/2015.
 *
 *<div class="jd-descr">

 <h2 style="margin-bottom: 0px;">Class Overview</h2><hr>
 <p itemprop="articleBody">This activity is using for changing at run time. </p>


 </div>
 */
public class ThemeSelectActivity extends BaseActivity {

    private List<RecyclerViewModel> feedsList;
    private RecyclerView mRecyclerView;
    private MyRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_select);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration itemDecoration=new DividerItemDecoration(ThemeSelectActivity.this,layoutManager.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);

        feedsList=new ArrayList<RecyclerViewModel>();

        feedsList.add(new RecyclerViewModel("Theme One",R.drawable.themeone));
        feedsList.add(new RecyclerViewModel("Theme Two",R.drawable.themetwo));
        feedsList.add(new RecyclerViewModel("Theme Three",R.drawable.themethree));
        feedsList.add(new RecyclerViewModel("Theme Four",R.drawable.themefour));
        feedsList.add(new RecyclerViewModel("Theme Five",R.drawable.themefve));

        adapter = new MyRecyclerAdapter(ThemeSelectActivity.this, feedsList);
        mRecyclerView.setAdapter(adapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(ThemeSelectActivity.this,MyListActivity.class));
        overridePendingTransition(0, 0);
        finish();
    }
}
