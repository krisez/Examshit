package com.redrock.my.smusic;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

/**
 * Created by ASUS on 2016/5/15.
 */
public class SetTool extends AppCompatActivity {
    private CheckBox checkBox1;
    private CheckBox checkBox2;
    private CheckBox checkBox3;
    private CheckBox checkBox4;

/**
 *我这没什么效果啊，我很蛋疼啊~！
 */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);

        SharedPreferences.Editor editor = getSharedPreferences("data",
                MODE_PRIVATE).edit();
        checkBox1 = (CheckBox) findViewById(R.id.start_1);
        checkBox2 = (CheckBox) findViewById(R.id.start_2);
        checkBox3 = (CheckBox) findViewById(R.id.start_3);
        checkBox4 = (CheckBox) findViewById(R.id.start_4);

        if(checkBox1.isChecked()){
            resetCheck();
            editor.putString("selcte","1");
            editor.commit();
        }
        if(checkBox2.isChecked()){
            resetCheck();
            editor.putString("selcte","2");
            editor.commit();
        }
        if(checkBox3.isChecked()){
            resetCheck();
            editor.putString("selcte","3");
            editor.commit();
        }
        if(checkBox4.isChecked()){
            resetCheck();
            editor.putString("selcte","4");
            editor.commit();
        }

    }
    void resetCheck(){
        checkBox1.setChecked(false);
        checkBox2.setChecked(false);
        checkBox3.setChecked(false);
        checkBox4.setChecked(false);
    }

}
