package com.hisham.design;

import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * A floating action button is a round button denoting a primary action on your interface.
 * The Design library's FloatingActionButton gives you a single consistent implementation, by default colored using the colorAccent from your theme.
 * In addition to the normal size floating action button,
 * it also supports the mini size (fabSize="mini") when visual continuity with other elements is critical.
 * As FloatingActionButton extends ImageView, you'll use android:src or any of the methods such as setImageDrawable() to
 * control the icon shown within the FloatingActionButton.
 *
 * Code:
 *
 *   <android.support.design.widget.FloatingActionButton
 android:id="@+id/floatingButton"
 android:layout_width="wrap_content"
 android:layout_height="wrap_content"
 android:layout_alignParentBottom="true"
 android:layout_alignParentEnd="true"
 android:src="@mipmap/ic_action">

 </android.support.design.widget.FloatingActionButton>
 */
public class FloatingActionButtonActivity extends AppCompatActivity {

    private final String[] objects = new String[]{
            "Text here",
            "Text here",
            "Text here",
            "Text here",
            "Text here",
            "Text here",
            "Text here",
            "Text here",
            "Text here",
            "Text here",
            "Text here",
            "Text here",
            "Text here",
            "Text here",
            "Text here",
            "Text here",
            "Text here",
            "Text here",
            "Text here",
            "Text here",
            "Text here",
            "Text here",
            "Text here",
            "Text here",
            "Text here",
            "Text here",
            "Text here",
            "Text here",
            "Text here",
//            "Elevation Sample",
//            "Sliding Example",
//            "Transition"
    };

    private View parentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floating_action_button);

        ListView myListView = (ListView) findViewById(R.id.myListViewBg);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, objects);
        myListView.setAdapter(adapter);

        FloatingActionButton floatingButton = (FloatingActionButton) findViewById(R.id.floatingButton);
        // Elevation can only be used if api level is 21 or greater.
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            floatingButton.setElevation(85f);

        parentLayout = findViewById(R.id.parentLayout);

        floatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Snackbar snackbar = Snackbar.make(parentLayout, "You just clicked", Snackbar.LENGTH_LONG);
//                snackbar.setDuration(10);
                snackbar.setAction("Dismiss", new View.OnClickListener() {
                    @Override
                    public void onClick(View v1) {
                        snackbar.dismiss();
                    }
                });
                snackbar.show();
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_floating_action_button, menu);
        return true;
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
