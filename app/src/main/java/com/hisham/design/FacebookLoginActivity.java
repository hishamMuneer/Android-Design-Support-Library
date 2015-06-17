package com.hisham.design;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

/**
 * Make sure you have facebook sdk : app > build.gradle > dependencies > compile 'com.facebook.android:facebook-android-sdk:4.1.0'
 * and just above dependencies : repositories {mavenCentral()}
 * Create app in facebook : https://developers.facebook.com/
 * click on MyApps and create add a new app for more https://developers.facebook.com/docs/android/getting-started
 *
 * Add facebook activity in your AndroidManifest.xml
    <activity android:name="com.facebook.FacebookActivity"
         android:configChanges=
         "keyboard|keyboardHidden|screenLayout|screenSize|orientation"
         android:theme="@android:style/Theme.Translucent.NoTitleBar"
         android:label="@string/app_name" />

 * Add Meta data in the application package  In the AndroidManifest.xml and add facebook_app_id in string file
 <meta-data android:name="com.facebook.sdk.ApplicationId" android:value="@string/facebook_app_id"/>
 *
 * for more
 * https://developers.facebook.com/docs/facebook-login/android/v2.3
 *
 */

public class FacebookLoginActivity extends AppCompatActivity {

    private LoginButton loginButton;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //initialize facebook sdk
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_facebook_login);

        //initialize callback manager
        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton)findViewById(R.id.login_button);

        //define permissions
        loginButton.setReadPermissions("user_friends");

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(getApplicationContext(), loginResult.getAccessToken().getUserId() + "", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
