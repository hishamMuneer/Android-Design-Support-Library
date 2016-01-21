package com.hisham.design;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hisham.design.constant.AppConstant;

/**
 * Created by faisal on 12/9/2015.
 *
 *<div class="jd-descr">

 <h2 style="margin-bottom: 0px;">Class Overview</h2><hr>
 <p itemprop="articleBody">Base Activity is parent of all activity in this project.
 we are using this as a parent because if we change theme of this project at run time it will reflect in whole project.
 In this onCreate we are checking current theme option saved in SharedPreferences as per that we are setting theme.</p>


 </div>
 *
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        SharedPreferences sharedPreferences=getSharedPreferences(AppConstant.APP_THEME, Context.MODE_PRIVATE);
        switch(sharedPreferences.getInt(AppConstant.APP_THEME_SELECTION, AppConstant.THEME_DEFAULT)) {
            case AppConstant.THEME_ONE:
                setTheme(R.style.AppThemeOne);
                break;
            case AppConstant.THEME_TWO:
                setTheme(R.style.AppThemeTwo);
                break;
            case AppConstant.THEME_THREE:
                setTheme(R.style.AppThemeThree);
                break;
            case AppConstant.THEME_FOUR:
                setTheme(R.style.AppThemeFour);
                break;
            case AppConstant.THEME_FIVE:
                setTheme(R.style.AppThemeFive);
                break;
            default:
                setTheme(R.style.AppTheme);
        }
        super.onCreate(savedInstanceState);
    }
}
