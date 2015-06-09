package com.hisham.design;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


/**
 *
 * <h21<a href="http://android-developers.blogspot.in/2015/05/android-design-support-library.html">Reference link for Blog reading and images</a></h1>
 * <b>How to start:</b>
 * <li>Create a new project, blank activity</li>
 * <li>Open build.gradle module app file and add:</li>
 * <pre>
 * compile 'com.android.support:support-v4:22.0.0'
 * compile 'com.android.support:appcompat-v7:22.0.0'
 * compile 'com.android.support:design:22.2.0'
 * <li>Extend your activity to AppCompatActivity</li>
 *
 * If you face error
 * Set theme in manifest application tag: android:theme="@style/Theme.AppCompat.Light"
 *
 * <h1>1. NavigationView</h1>
 * Add the following code in xml file:
 * <android.support.v4.widget.DrawerLayout
 android:id="@+id/drawerLayout"
 xmlns:android="http://schemas.android.com/apk/res/android"
 xmlns:app="http://schemas.android.com/apk/res-auto"
 android:layout_width="match_parent"
 android:layout_height="match_parent"
 android:fitsSystemWindows="true">

 <!-- your content layout -->

 <android.support.design.widget.NavigationView
 android:id="@+id/navigationView"
 android:layout_width="wrap_content"
 android:layout_height="match_parent"
 android:layout_gravity="start"
 app:headerLayout="@layout/drawer_header"
 app:menu="@menu/menu_main"/>
 </android.support.v4.widget.DrawerLayout>


 * You can also make optional header inside the Navigation View and you can also populate the options inside Navigation View via Options menu
 *
 * Make a single checkable group inside menu:
 *
 *     <group android:checkableBehavior="single">
 <item
 android:id="@+id/navigation_item_1"
 android:checked="true"
 android:icon="@mipmap/ic_launcher"
 android:title="@string/item_1" />
 <item
 android:id="@+id/navigation_item_2"
 android:icon="@mipmap/ic_launcher"
 android:title="@string/item_2" />
 </group>

 *
 * You'll get callbacks on selected items by setting a OnNavigationItemSelectedListener using setNavigationItemSelectedListener().
 * This provides you with the MenuItem that was clicked, allowing you to handle selection events, changed the checked status,
 * load new content, programmatically close the drawer, or any other actions you may want.
 *
 * Close is called on drawerLayout
 * You can also hide the menu by not implementing onCreateOptionsMenu, NavigationView will work as it working.
 *
 *
 * Files included in this example:
 * layout/activity_mail.xml
 * layout/drawer_header.xml
 * menu/menu_main.xml
 * minmpa/ic_launcher
 *
 * </pre>
 */
public class NavigationViewActivity extends AppCompatActivity {

    public static String TAG = NavigationViewActivity.class.getSimpleName();
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        navigationView = (NavigationView) findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(navigationItemSelectedListener);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    NavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(MenuItem menuItem) {

            switch (menuItem.getItemId()){
                case R.id.navigation_item_1:
                case R.id.navigation_item_2:
                case R.id.navigation_item_3:
                case R.id.navigation_item_4:
                case R.id.navigation_item_5:
                case R.id.navigation_item_6:
                case R.id.navigation_item_7:
                case R.id.navigation_item_8:
                    menuItem.setChecked(true);
                    break;
            }

            Log.e(TAG, menuItem.getItemId() + " : " + menuItem.getTitle());
            Toast.makeText(getApplicationContext(), menuItem.getItemId() + " : " + menuItem.getTitle(), Toast.LENGTH_SHORT).show();
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        }
    };

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
