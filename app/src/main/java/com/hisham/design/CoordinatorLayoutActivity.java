package com.hisham.design;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class CoordinatorLayoutActivity extends AppCompatActivity {

    String objects[] = new String[]{
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

    ListView myListViewBgCo;
    private FloatingActionButton floatingButtonCo;
    private View parentLayoutCo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floating_action_button);

        myListViewBgCo = (ListView) findViewById(R.id.myListViewBgCo);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, objects);
        myListViewBgCo.setAdapter(adapter);

        floatingButtonCo = (FloatingActionButton)findViewById(R.id.floatingButtonCo);
        floatingButtonCo.setElevation(85f);

        parentLayoutCo = findViewById(R.id.parentLayoutCo);

        floatingButtonCo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Snackbar snackbar = Snackbar.make(parentLayoutCo, "You just clicked", Snackbar.LENGTH_LONG);
//                snackbar.setDuration(10);
                snackbar.setAction("Dismiss", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
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
