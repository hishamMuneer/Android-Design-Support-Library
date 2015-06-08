package com.hisham.design;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TableLayout;


public class MyListActivity extends AppCompatActivity {

    // test

    ListView myListView;
    String objects[] = new String[]{
            "NavigationView",
            "Floating Label (EditText) Sample",
            "Floating Action Button And SnackBar Sample",
            "TabLayout Example",
            "Coordinator Layout"
    };
    Class[] classes = new Class[]{
            NavigationViewActivity.class,
            TextInputLayoutActivity.class,
            FloatingActionButtonActivity.class,
            TabLayoutActivity.class,
            CoordinatorLayoutActivity.class
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list);



        myListView = (ListView)findViewById(R.id.myListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_dropdown_item_1line, objects);
        myListView.setAdapter(adapter);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getApplicationContext(), classes[position]));
            }
        });

    }
}
