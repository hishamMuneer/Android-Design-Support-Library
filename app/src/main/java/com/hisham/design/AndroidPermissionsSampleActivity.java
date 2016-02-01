package com.hisham.design;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


/**
 * ** You need to declare the permissions in AndroidManifest.xml file like in older versions.
 * This new Runtime Permission will work like described only when we set the application's targetSdkVersion to 23 which mean it is declared that application has already been tested on API Level 23. And this feature will work only on Android 6.0 Marshmallow. The same app will run with same old behavior on pre-Marshmallow device.
 *
 *  Dangerous permissions are grouped into categories that make it easier for the user to understand what they are allowing the application to do. If the user accepts one permission in a group/category they accept the entire group. The opposite is true as well, if the user denies one permission in a group, the entire group in denied.
 *  Dangerous Permissions: http://developer.android.com/guide/topics/security/permissions.html
 *  Normal Permissions: http://developer.android.com/guide/topics/security/normal-permissions.html
 */
public class AndroidPermissionsSampleActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback {

    public static final String TAG = AndroidPermissionsSampleActivity.class.getSimpleName();

    /**
     * Id to identify a Location permission request.
     */
    private static final int REQUEST_LOCATION = 0;
    private View mLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_permissions_sample);
        mLayout = findViewById(R.id.coordinatorLayout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Snackbar.make(view, "Final Action :)", Snackbar.LENGTH_LONG).show();
                            }
                        }).show();
            }
        });


        Button btnAccessLocation = (Button) findViewById(R.id.btnAccessLocation);
        btnAccessLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUserLocation();
            }
        });

    }

    /**
     * Called when the 'Access Location' button is clicked.
     * Callback is defined in resource layout definition.
     */
    public void getUserLocation() {
        Log.i(TAG, "Show User Location. Checking permission.");
        // BEGIN_INCLUDE(Location_permission)
        // Check if the Location permission is already available.
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Location permission has not been granted.

            requestLocationPermission();

        } else {

            // Location permissions is already available, show the Location preview.
            Log.i(TAG, "Location permission has already been granted. Displaying Location preview.");


            displayLocation();

        }
        // END_INCLUDE(Location_permission)

    }

    private void displayLocation() {
        // create class object
        GPSTracker gps = new GPSTracker(AndroidPermissionsSampleActivity.this);

        // check if GPS enabled
        if(gps.canGetLocation()){

            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();

            // \n is for new line
            Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
        }else{
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }
    }

    /**
     * Requests the Location permission.
     * If the permission has been denied previously, a SnackBar will prompt the user to grant the
     * permission, otherwise it is requested directly.
     */
    private void requestLocationPermission() {
        Log.i(TAG, "Location permission has NOT been granted. Requesting permission.");

        // BEGIN_INCLUDE(Location_permission_request)
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            // Provide an additional rationale to the user if the permission was not granted
            // and the user would benefit from additional context for the use of the permission.
            // For example if the user has previously denied the permission.
            Log.i(TAG, "Displaying Location permission rationale to provide additional context.");
            Snackbar.make(mLayout, "Location permission is needed to show the Location preview.",
                    Snackbar.LENGTH_INDEFINITE)
                    .setAction("Ok", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ActivityCompat.requestPermissions(AndroidPermissionsSampleActivity.this,
                                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                    REQUEST_LOCATION);
                        }
                    })
                    .show();
        } else {

            // Location permission has not been granted yet. Request it directly.
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION);
        }
        // END_INCLUDE(Location_permission_request)
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == REQUEST_LOCATION) {
            // BEGIN_INCLUDE(permission_result)
            // Received permission result for Location permission.
            Log.i(TAG, "Received response for Location permission request.");

            // Check if the only required permission has been granted
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Location permission has been granted, preview can be displayed
                Log.i(TAG, "Location permission has now been granted. Showing preview.");
                Snackbar.make(mLayout, "Location Permission has been granted. Preview can now be opened.",
                        Snackbar.LENGTH_SHORT).show();
                displayLocation();
            } else {
                Log.i(TAG, "Location permission was NOT granted.");
                Snackbar.make(mLayout, "Permissions were not granted.",
                        Snackbar.LENGTH_SHORT).show();
            }
            // END_INCLUDE(permission_result)

        }  else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}