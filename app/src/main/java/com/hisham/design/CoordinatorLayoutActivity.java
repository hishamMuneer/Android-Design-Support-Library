package com.hisham.design;

import android.os.Build;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * CoordinatorLayout, motion, and scrolling
 Distinctive visuals are only one part of material design: motion is also an important part of making a great material designed app.
 While there are a lot of parts of motion in material design including touch ripples and meaningful transitions,
 the Design library introduces CoordinatorLayout, a layout which provides an additional level of control over touch events
 between child views, something which many of the components in the Design library take advantage of.

 CoordinatorLayout and floating action buttons
 A great example of this is when you add a FloatingActionButton as a child of your CoordinatorLayout and then pass that
 CoordinatorLayout to your Snackbar.make() call - instead of the snackbar displaying over the floating action button,
 the FloatingActionButton takes advantage of additional callbacks provided by CoordinatorLayout to automatically move
 upward as the snackbar animates in and returns to its lastPosition when the snackbar animates out on Android 3.0
 and higher devices - no extra code required.

 SnackBar
 * <pre class="prettyprint">
 * Snackbar.make(coordinatorLayout, "You removed : " + lastItem, Snackbar.LENGTH_LONG)
 * .setAction("Undo", new View.OnClickListener() {
 *
     * public void onClick(View v) {
     *      list.add(lastPosition, lastItem);
     * adapter.notifyDataSetChanged();
     * }
 * }).show();
 * </pre>
 */
public class CoordinatorLayoutActivity extends BaseActivity {

// --Commented out by Inspection START (6/18/2015 12:46 PM):
//    String objects[] = new String[]{
//            "Text here",
//            "Text here",
//            "Text here",
//            "Text here",
//            "Text here",
//            "Text here",
//            "Text here",
//            "Text here",
//            "Text here",
//            "Text here",
//            "Text here",
//            "Text here",
//            "Text here",
//            "Text here",
//            "Text here",
//            "Text here",
//            "Text here",
//            "Text here",
//            "Text here",
//            "Text here",
//            "Text here",
//            "Text here",
//            "Text here",
//            "Text here",
//            "Text here",
//            "Text here",
//            "Text here",
//            "Text here",
//            "Text here",
////            "Elevation Sample",
////            "Sliding Example",
////            "Transition"
//    };
// --Commented out by Inspection STOP (6/18/2015 12:46 PM)

    //    private View parentLayoutCo;
    private CoordinatorLayout coordinatorLayout;
    private final ArrayList<String> list = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private String lastItem;
    private int lastPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_layout);

        for(int i =0; i<25; i++){
            list.add("Text Item: " + i);
        }

        ListView myListViewBgCo = (ListView) findViewById(R.id.myListViewBg);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, list);
        myListViewBgCo.setAdapter(adapter);

        myListViewBgCo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lastItem = list.get(position);
                lastPosition = position;
                list.remove(position);
                adapter.notifyDataSetChanged();

                Snackbar.make(coordinatorLayout, "You removed : " + lastItem, Snackbar.LENGTH_LONG)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                list.add(lastPosition, lastItem);
                                adapter.notifyDataSetChanged();
                            }
                        }).show();
            }
        });

        coordinatorLayout = (CoordinatorLayout)findViewById(R.id.coordinatorLayout);

        FloatingActionButton floatingButtonCo = (FloatingActionButton) findViewById(R.id.floatingButton);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            floatingButtonCo.setElevation(3);

//        parentLayoutCo = findViewById(R.id.parentLayout);

        floatingButtonCo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(coordinatorLayout, "You clicked on floating button.", Snackbar.LENGTH_LONG).show();
            }
        });
    }
}
