package com.example.zhanglingyun.anroidtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class RadioGroupActivity extends AppCompatActivity {

    private  String sexStr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_group);


        RadioGroup sexGroup=(RadioGroup)RadioGroupActivity.this.findViewById(R.id.sexRadioGroup);

        sexGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton = (RadioButton)RadioGroupActivity.this.findViewById(radioGroup.getCheckedRadioButtonId());
                sexStr=radioButton.getText().toString();
            }
        });

        Button sureBtn = (Button)this.findViewById(R.id.sureBtn);

        sureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();

                intent.putExtra("choseSex",sexStr);

                RadioGroupActivity.this.setResult(100,intent);
                RadioGroupActivity.this.finish();
            }
        });
    }
}
