package com.example.zhanglingyun.anroidtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import java.util.ArrayList;
import java.util.List;

public class CheckBoxActivity extends AppCompatActivity implements View.OnClickListener{


    private Button allChoseBtn,cancelBtn;

    private ArrayList<CheckBox> checkBoxList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_box);

        allChoseBtn=(Button)this.findViewById(R.id.allChoseBtn);
        allChoseBtn.setOnClickListener(this);
        cancelBtn=(Button)this.findViewById(R.id.cancelBtn);
        cancelBtn.setOnClickListener(this);

        checkBoxList=getAllCheckBox();


    }

    private ArrayList<CheckBox> getAllCheckBox()
    {
        ArrayList<CheckBox> checkBoxList = new ArrayList<CheckBox>();

        checkBoxList.add((CheckBox)this.findViewById(R.id.checkbox1));
        checkBoxList.add((CheckBox)this.findViewById(R.id.checkbox2));
        checkBoxList.add((CheckBox)this.findViewById(R.id.checkbox3));
        checkBoxList.add((CheckBox)this.findViewById(R.id.checkbox4));

        return checkBoxList;
    }

    // 全部选中
    private void setAllChose()
    {
        for (CheckBox checkBox :
                checkBoxList)
        {
            checkBox.setChecked(true);
        }
    }

    // 取消选中
    private void setAllCancel()
    {
        for (CheckBox checkBox :
                checkBoxList)
        {
            checkBox.setChecked(false);
        }
    }


    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.allChoseBtn:
            {
                Log.v("oneTag","click all chose");
                this.setAllChose();
            }
                 break;
            case R.id.cancelBtn:
            {
                Log.v("twoTag","click cancel");
                this.setAllCancel();
            }
                break;
        }
    }
}
