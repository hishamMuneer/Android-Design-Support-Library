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
public class TextInputLayoutActivity extends BaseActivity {

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
}
