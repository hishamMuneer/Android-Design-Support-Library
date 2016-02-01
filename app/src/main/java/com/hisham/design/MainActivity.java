package com.hisham.design;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * How to implement DrawerArrowToggle from Android appcompat - v7 - 21 - library. <br />
 * <h3>Steps: </h3>
 *
 * <pre class="prettyprint">
 *

 final ActionBar ab = getSupportActionBar();
 ab.setHomeAsUpIndicator(R.drawable.ic_menu);
 ab.setDisplayHomeAsUpEnabled(true);

 DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

 ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
 mDrawerLayout.setDrawerListener(actionBarDrawerToggle);
 actionBarDrawerToggle.syncState();

 // Override the onOptionsItemSelected so that on selecting the burger icon, navigation drawer should open.
 // Burger icon should be present in the drawable folder. ex: ic_menu.png

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
 </pre>

 <p>Code for xml layout: </p>

 <pre class="prettyprint">
 &lt;android.support.v4.widget.DrawerLayout
 xmlns:android="http://schemas.android.com/apk/res/android"
 xmlns:app="http://schemas.android.com/apk/res-auto"
 android:id="@+id/drawer_layout"
 android:layout_height="match_parent"
 android:layout_width="match_parent"
 android:fitsSystemWindows="true"&gt;
 &lt;!--Whatever you want to display in the middle goes here--&gt;
 &lt;ListView
 android:id="@+id/myListView"
 android:layout_width="match_parent"
 android:layout_height="match_parent"&gt;
 &lt;/ListView>
 &lt;!-- ENDS - Whatever you want to display in the middle goes here--&gt;
 &lt;android.support.design.widget.NavigationView
 android:id="@+id/nav_view"
 android:layout_height="match_parent"
 android:layout_width="wrap_content"
 android:layout_gravity="start"
 android:fitsSystemWindows="false"
 app:menu="@menu/menu_home"/&gt;

 &lt;/android.support.v4.widget.DrawerLayout&gt;
</pre>
 *
 */
public class MainActivity extends BaseActivity {

    // test

    private final String[] objects = new String[]{
            "NavigationView",
            "Floating Label (EditText) Sample",
            "Floating Action Button And SnackBar Sample",
            "TabLayout Example",
            "Coordinator Layout",
            "Collapsing Toolbars",
            "Palette Changing Colors",
            "Google Login Example",
            "Facebook Login Example",
            "Data Binding Example",
            "Theme Change Example",
            "Content Provider Example",
            "Maps",
            "Permissions Sample"
    };
    private final Class[] classes = new Class[]{
            NavigationViewActivity.class,
            TextInputLayoutActivity.class,
            FloatingActionButtonActivity.class,
            TabLayoutActivity.class,
            CoordinatorLayoutActivity.class,
            CollapsingToolbarsActivity.class,
            PaletteActivity.class,
            GoogleLoginActivity.class,
            FacebookLoginActivity.class,
            DataBindingActivity.class,
            ThemeSelectActivity.class,
            ContentProviderActivity.class,
            MapsActivity.class,
            AndroidPermissionsSampleActivity.class
    };
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list);

        final ActionBar ab = getSupportActionBar();
        if(ab!=null) {
            ab.setHomeAsUpIndicator(R.drawable.ic_menu);
            ab.setDisplayHomeAsUpEnabled(true);
        }

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        ListView myListView = (ListView) findViewById(R.id.myListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_dropdown_item_1line, objects);
        myListView.setAdapter(adapter);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MainActivity.this.startActivity(new Intent(getApplicationContext(), classes[position]));
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
