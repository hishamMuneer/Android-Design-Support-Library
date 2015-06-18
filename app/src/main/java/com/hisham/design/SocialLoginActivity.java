package com.hisham.design;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentSender;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;


/**
 * Make sure you have Google Play Services: Tools > Android > SDK Manager.
 * Scroll to the bottom of the package list and select Extras > Google Play services.
 * Get Configuration File:
 * First get SHA-1 fingerprint on your client: https://developers.google.com/android/guides/client-auth
 * > keytool -exportcert -alias androiddebugkey -keystore C:\Users\NI-PC1\.android\debug.keystore -list -v
 * SHA1 = C2:70:D9:8A:21:96:D8:E9:64:3E:D6:08:1D:88:29:4A:59:BF:4B:A7
 * <p/>
 * Start Integrating:
 * Make Google Console Project first
 * https://developers.google.com/identity/sign-in/android/start-integrating
 * <p/>
 * REMEMBER TO ADD GOOGLE PLAY SERVICES IN build.gradle:
 * https://developers.google.com/android/guides/setup
 */
public class SocialLoginActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        View.OnClickListener {

    private static final String TAG = "SocialActivity";
    /* Request code used to invoke sign in user interactions. */
    private static final int RC_SIGN_IN = 0;
    /* Client used to interact with Google APIs. */
    private GoogleApiClient mGoogleApiClient;

    /**
     * True if the sign-in button was clicked.  When true, we know to resolve all
     * issues preventing sign-in without waiting.
     */
    private boolean mSignInClicked;

    /**
     * True if we are in the process of resolving a ConnectionResult
     */
    private boolean mIntentInProgress;
    private ProgressDialog progressDialog;
    private ListView listView;
    private ImageView image;
    private TextView txtDetails;
    private View btnSignIn;
    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_login);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API)
                .addScope(new Scope("profile"))
                .build();

        btnSignIn = findViewById(R.id.sign_in_button);
        btnSignIn.setOnClickListener(this);
        btnLogout = (Button) findViewById(R.id.logout);
        btnLogout.setOnClickListener(this);
        listView = (ListView) findViewById(R.id.listView);
        image = (ImageView) findViewById(R.id.imageGoogle);
        txtDetails = (TextView) findViewById(R.id.txtUserDetails);

        doIfLoggedOut();

    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mGoogleApiClient.disconnect();
    }

    //    Sign out the user
    //    You can add a sign out button to your app. Create a button in your app to act as your sign out button.
    //    Attach an onClickListener to the button and configure the onClick method to disconnect the GoogleApiClient:
    public void onClick(View view) {
        if (view.getId() == R.id.sign_in_button && !mGoogleApiClient.isConnecting()) {
            mSignInClicked = true;
            mGoogleApiClient.connect();
            progressDialog = ProgressDialog.show(this, "Please Wait...",
                    "Google+ Sign In.", true);
        }

        if (view.getId() == R.id.logout) {
            doIfLoggedOut();
            if (mGoogleApiClient.isConnected()) {
                mGoogleApiClient.clearDefaultAccountAndReconnect();
            }
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        if (!mIntentInProgress) {
            if (mSignInClicked && result.hasResolution()) {
                // The user has already clicked 'sign-in' so we attempt to resolve all
                // errors until the user is signed in, or they cancel.
                try {
                    result.startResolutionForResult(this, RC_SIGN_IN);
                    mIntentInProgress = true;
                } catch (IntentSender.SendIntentException e) {
                    // The intent was canceled before it was sent.  Return to the default
                    // state and attempt to connect to get an updated ConnectionResult.
                    mIntentInProgress = false;
                    mGoogleApiClient.connect();
                }
            }
        }
    }

    private void doIfLoggedIn(){
        btnSignIn.setVisibility(View.GONE);
        btnLogout.setVisibility(View.VISIBLE);
    }

    private void doIfLoggedOut(){
        btnSignIn.setVisibility(View.VISIBLE);
        btnLogout.setVisibility(View.GONE);
    }

    @Override
    public void onConnected(Bundle connectionHint) {
        mSignInClicked = false;
        String details;

        doIfLoggedIn();

        if (Plus.PeopleApi.getCurrentPerson(mGoogleApiClient) != null) {

            Person currentPerson = Plus.PeopleApi.getCurrentPerson(mGoogleApiClient);
            String personName = currentPerson.getDisplayName();

            //String personPhoto = currentPerson.getImage();
           // String personGooglePlusProfile = currentPerson.getUrl();
           // String imageUrl = currentPerson.getImage().getUrl();
            //String cover = currentPerson.getCover().getCoverPhoto().getUrl();
            String email = Plus.AccountApi.getAccountName(mGoogleApiClient);
            Toast.makeText(this, "User is connected: " + personName + " | Email: " + email, Toast.LENGTH_LONG).show();
            ArrayList<String> list = showAllMethodsWithValues(currentPerson);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
            listView.setAdapter(adapter);

            if(currentPerson.hasCover())
                new ImageLoaderTask().execute(currentPerson.getCover().getCoverPhoto().getUrl());

            details = currentPerson.getDisplayName() + " \n" + email + "\n";
            txtDetails.setText(details);
        }
    }


    /**
     * Returns all methods and their values using reflection.
     *
     * @param object - pass your object
     * @return arraylist of string with method value and maehod name in it.
     */
    private ArrayList<String> showAllMethodsWithValues(Object object) {
        ArrayList<String> list = new ArrayList<>();
        // Get the public methods associated with this class.
        Method[] methods = object.getClass().getMethods();// getDeclaredMethods();// getMethods();
        for (Method method : methods) {
            try {
                Object x = method.invoke(object);
                //if(x instanceof String)
                list.add(x.toString() + " --------- " + method.toString());
                Log.i(TAG, x.toString() + " -------- " + method.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    protected void onActivityResult(int requestCode, int responseCode, Intent intent) {
        progressDialog.dismiss();
        if (requestCode == RC_SIGN_IN) {
            if (responseCode != RESULT_OK) {
                mSignInClicked = false;
            }

            mIntentInProgress = false;

            if (!mGoogleApiClient.isConnected()) {
                mGoogleApiClient.reconnect();
            }
        }
    }

    @Override
    public void onConnectionSuspended(int cause) {
        mGoogleApiClient.connect();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_social_login, menu);
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

    /**
     * An Async Task to load image.
     */
    private class ImageLoaderTask extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                return BitmapFactory.decodeStream(url.openConnection().getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if(bitmap != null)
                image.setImageBitmap(bitmap);
        }
    }
}
