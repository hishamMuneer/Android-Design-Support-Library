package com.hisham.design;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.hisham.design.provider.StudentsProvider;


/**
 * A content provider can be used to store data and that data can be accessed by other applications using a content resolver. <br>
 * Related Files: <br>
 * <li>com.hisham.design.provider.StudentProvider</li>
 * <li>res/layout/activity_content_provider.xml</li>
 * <li>AndroidManifest.xml</li>
 * <p/>
 * Data from content provider can be accessed from any application using the following code
 * <pre class="prettyprint">
 * String URL = "content://com.example.provider.College/students";
 * Uri students = Uri.parse(URL);
 * Cursor c = managedQuery(students, null, null, null, "name");
 * if (c.moveToFirst()) {
 * do{
 * Toast.makeText(this,
 * c.getString(c.getColumnIndex(StudentsProvider._ID)) +
 * ", " +  c.getString(c.getColumnIndex( StudentsProvider.NAME)) +
 * ", " + c.getString(c.getColumnIndex( StudentsProvider.GRADE)),
 * Toast.LENGTH_SHORT).show();
 * } while (c.moveToNext());
 * }
 * </pre>
 */
public class ContentProviderActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);
    }


    public void onClickAddName(View view) {
        // Add a new student record
        ContentValues values = new ContentValues();

        values.put(StudentsProvider.NAME,
                ((EditText) findViewById(R.id.editText2)).getText().toString());

        values.put(StudentsProvider.GRADE,
                ((EditText) findViewById(R.id.editText3)).getText().toString());

        Uri uri = getContentResolver().insert(
                StudentsProvider.CONTENT_URI, values);

        Toast.makeText(getBaseContext(),
                uri.toString(), Toast.LENGTH_LONG).show();
    }

    public void onClickRetrieveStudents(View view) {

        // Retrieve student records
        String URL = "content://com.example.provider.College/students";

        Uri students = Uri.parse(URL);
        Cursor c = managedQuery(students, null, null, null, "name");

        if (c.moveToFirst()) {
            do {
                Toast.makeText(this,
                        c.getString(c.getColumnIndex(StudentsProvider._ID)) +
                                ", " + c.getString(c.getColumnIndex(StudentsProvider.NAME)) +
                                ", " + c.getString(c.getColumnIndex(StudentsProvider.GRADE)),
                        Toast.LENGTH_SHORT).show();
            } while (c.moveToNext());
        }
    }

}
