package com.hisham.design;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;

/**
 * Make sure you have facebook sdk : app > build.gradle > dependencies > compile 'com.facebook.android:facebook-android-sdk:4.1.0'
 * and just above dependencies : repositories {mavenCentral()}
 * Create app in facebook : https://developers.facebook.com/
 * click on MyApps and create add a new app for more https://developers.facebook.com/docs/android/getting-started
 * <p/>
 * Add facebook activity in your AndroidManifest.xml
 * <activity android:name="com.facebook.FacebookActivity"
 * android:configChanges=
 * "keyboard|keyboardHidden|screenLayout|screenSize|orientation"
 * android:theme="@android:style/Theme.Translucent.NoTitleBar"
 * android:label="@string/app_name" />
 * <p/>
 * Add Meta data in the application package  In the AndroidManifest.xml and add facebook_app_id in string file
 * <meta-data android:name="com.facebook.sdk.ApplicationId" android:value="@string/facebook_app_id"/>
 * <p/>
 * for more
 * https://developers.facebook.com/docs/facebook-login/android/v2.3
 *
 * TO generate hash key: make sure you have openssl installed.
 * C:\Users\NI-PC1>keytool -exportcert -alias androiddebugkey -keystore C:\Users\NI
 -PC1\.android\debug.keystore | D:\facebook\bin\openssl sha1 -binary | D:\faceboo
 k\bin\openssl base64
 Enter keystore password:  android
 */

public class FacebookLoginActivity extends AppCompatActivity {

    private CallbackManager callbackManager;
    private LoginResult loginResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //initialize facebook sdk
        FacebookSdk.sdkInitialize(getApplicationContext());
        //initialize callback manager
        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_facebook_login);

        try {
            PackageInfo info = getPackageManager().getPackageInfo("com.hisham.design",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.i("Digest: ", Base64.encodeToString(md.digest(), 0));
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("Test", e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            Log.e("Test", e.getMessage());
        }

        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);

        //define permissions
        loginButton.setReadPermissions(Collections.singletonList("public_profile, email, user_birthday, user_friends"));

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult mLoginResult) {
                Log.d("Access Token", mLoginResult.getAccessToken().toString());
                Log.d("User Id", mLoginResult.getAccessToken().getUserId());
                loginResult = mLoginResult;
                fetchUserInfo();
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(),"",Toast.LENGTH_LONG).show();
                Log.e("facebookcancel","cancel");
            }

            @Override
            public void onError(FacebookException exception) {
                Toast.makeText(getApplicationContext(),"",Toast.LENGTH_LONG).show();
                Log.e("facebookerror",exception+"");
            }
        });
    }

    private void fetchUserInfo() {
        GraphRequest request = GraphRequest.newMeRequest
                (loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.v("FacebookLoginActivity", response.toString());
                        try {
                            String id = object.getString("id");
                            String name = object.getString("name");
                            String email = object.getString("email");
                            String gender = object.getString("gender");
                            String birthday = object.getString("birthday");
                            JSONObject imgJson = object.getJSONObject("picture");
                            JSONObject imgJson1 = imgJson.getJSONObject("data");
                            String imgUrl = imgJson1.getString("url");

                            Log.d("id", id);
                            Log.d("name", name);
                            Log.d("email", email);
                            Log.d("gender", gender);
                            Log.d("birthday", birthday);

                            if (id == null) {
                                id = "";
                            }
                            if (name == null) {
                                name = "";
                            }
                            if (email == null) {
                                email = "";
                            }
                            if (gender == null) {
                                gender = "";
                            }
                            if (birthday == null) {
                                birthday = "";
                            }
                            ((TextView) findViewById(R.id.tvId)).setText(id);
                            ((TextView) findViewById(R.id.tvEmail)).setText(email);
                            ((TextView) findViewById(R.id.tvName)).setText(name);
                            ((TextView) findViewById(R.id.tvGender)).setText(gender);
                            ((TextView) findViewById(R.id.tvBday)).setText(birthday);
                            if (imgUrl != null) {
                                new ImageLoaderTask().execute(imgUrl);
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,email,gender,picture.width(300),birthday");
        request.setParameters(parameters);
        request.executeAsync();
    }


    // Track App Installs and App Opens
    // App Events let you measure installs on your mobile app ads, create high value audiences for targeting,
    // and view analytics including user demographics. To log an app activation event, add the following code to the onResume()
    // method of your app's default activity class:
    @Override
    protected void onResume() {
        super.onResume();

        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(this);
    }

    // To accurately track the time people spend in your app, you should also log a deactivate event in the onPause() method of
    // each activity where you added the activateApp() method above:
    @Override
    protected void onPause() {
        super.onPause();

        // Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(this);
    }
//    Now, when people install or engage with your app, you'll see this data reflected in your app's Insights dashboard.

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
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
            if (bitmap != null)
                ((ImageView) findViewById(R.id.ivProfile)).setImageBitmap(bitmap);
        }
    }
}
