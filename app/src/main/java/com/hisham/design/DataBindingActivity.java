package com.hisham.design;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.hisham.design.databinding.ActivityDataBindingBinding;
import com.hisham.design.model.User;

/**
 * <h2>FOR COMPLETE DETAILS CHECK : http://developer.android.com/tools/data-binding/guide.html</h2>
 * <h3>By using data binding we remove the findViewById calls and data is set dynamically without </h3>
 * <p>To get started with Data Binding, download the library from the Support repository in the Android SDK manager. <br>
 To configure your app to use data binding, add the dataBinding element to your build.gradle file in the app module. <br>

 Add following in build.gradle project file <br>
 <h4>classpath 'com.android.tools.build:gradle:1.3.1'<br>
 // TODO: when the final verison of dataBinder is release, change this to use a version number.<br>
 classpath 'com.android.databinding:dataBinder:1.+'</h4>

 Add following in build.gradle app file <br>
 <h4>apply plugin: 'com.android.databinding'</h4>
 </p>

 <p>

 Check the layout - activity_data_binding.xml
 <h4>By default, a Binding class will be generated based on the name of the layout file, converting it to Pascal case
 and suffixing “Binding” to it. The above layout file was activity_data_binding.xml so the generate class was ActivityDataBindingBinding.</h4>
 </p>


 */
public class DataBindingActivity extends AppCompatActivity {

    private ActivityDataBindingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_binding);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding);
        binding.setUser(new User("Hisham", "Muneer", true));
    }
}
