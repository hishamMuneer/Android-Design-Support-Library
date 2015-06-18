package com.hisham.design;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * <h1>Floating Labels in TextInputLayout</h1>
 *
 * Wrap your edittext inside an TextInputLayout to show hint to the user while he is writing
 *  <android.support.design.widget.TextInputLayout
 android:id="@+id/textInputLayout"
 android:layout_width="match_parent"
 android:layout_height="wrap_content">

 <EditText
 android:id="@+id/editText"
 android:layout_width="match_parent"
 android:layout_height="wrap_content"
 android:layout_alignParentEnd="true"
 android:layout_centerVertical="true"
 android:hint="@string/hint_float_label" />
 </android.support.design.widget.TextInputLayout>
 */
public class TextInputLayoutActivity extends AppCompatActivity {

    private TextInputLayout textInputLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floating_labels);
        textInputLayout = (TextInputLayout)findViewById(R.id.textInputLayout);
        textInputLayout.setErrorEnabled(true);

    }

    public void pressMe(View v){
        textInputLayout.setError("Test Error");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_floating_labels, menu);
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
