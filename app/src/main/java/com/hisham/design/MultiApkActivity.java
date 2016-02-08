package com.hisham.design;

import android.os.Bundle;
import android.view.View;

/**
 *For building your project with different package name, icons,string.xml file and with different colors.xml files
 * you have to do following steps..........
 *
 * 1) open build.gradle(Module:app) and add  productFlavors {} in that file where you can define more then one package
 *    and their version like you can see in this project file i have define you package
 *    ---> com.first.faisal.multiapkdemo.paid
 *    ---> com.second.faisal.multiapkdemo.free
 *    and build gradle file.  and after this in the window of android studio at the left bottom a option Build Variants from
 *    there you can select a package on which you want you build apk.
 *
 * 2) After this open project in Project structure instead of Android structure by clicking on 1:Project above 7: structure
 *
 * 3) open directory YourProject -----> app----> src --->
 *  now add here required folder. like i have added two productFlavors paid and free so add two directory free and paid.
 *
 * 4) now you can have define separate file like java files, layout file, colors files, style, drawable etc
 *
 * 5) for more follow my project structure. https://github.com/faisalkhan1690/MultiApkDemo.git
 *
 *
 *
 */
public class MultiApkActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_apk);


    }

    public void startActivity(View view){

        /**
         * comment this line of code if you are using free version because in the free version android studio will not compile paid
         * classes so i will start showing error
         * **/

//        startActivity(new Intent(MultiApkActivity.this,PaidActivity.class));
    }
}
