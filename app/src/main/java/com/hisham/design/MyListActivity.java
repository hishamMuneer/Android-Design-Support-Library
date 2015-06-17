package com.hisham.design;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TableLayout;


/**
 * How to implement DrawerArrowToggle from Android appcompat v7 21 library
 *
 * Steps:
 *
 * final ActionBar ab = getSupportActionBar();
 ab.setHomeAsUpIndicator(R.drawable.ic_menu);
 ab.setDisplayHomeAsUpEnabled(true);

 DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

 ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
 mDrawerLayout.setDrawerListener(actionBarDrawerToggle);
 actionBarDrawerToggle.syncState();
 *
 */
public class MyListActivity extends AppCompatActivity {

    // test

    ListView myListView;
    String objects[] = new String[]{
            "NavigationView",
            "Floating Label (EditText) Sample",
            "Floating Action Button And SnackBar Sample",
            "TabLayout Example",
            "Coordinator Layout",
            "Collapsing Toolbars",
            "Palette Changing Colors",
            "Google Login Example"
    };
    Class[] classes = new Class[]{
            NavigationViewActivity.class,
            TextInputLayoutActivity.class,
            FloatingActionButtonActivity.class,
            TabLayoutActivity.class,
            CoordinatorLayoutActivity.class,
            CollapsingToolbars.class,
            PaletteActivity.class,
            SocialLoginActivity.class
    };
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list);

        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        myListView = (ListView)findViewById(R.id.myListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_dropdown_item_1line, objects);
        myListView.setAdapter(adapter);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MyListActivity.this.startActivity(new Intent(getApplicationContext(), classes[position]));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    mDrawerLayout.openDrawer(GravityCompat.START);
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
