package com.example.zhanglingyun.anroidtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button=(Button) this.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            public void  onClick(View v)
            {
                // 不穿值跳转
//                startActivity(new Intent(MainActivity.this,MyActivity.class));

                // 传参数跳转
                Intent intent = new Intent(MainActivity.this,MyActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("name","Andrew");
                bundle.putString("age","25");
                intent.putExtras(bundle);
                startActivityForResult(intent,1);
            }

        });

        Button pushBtn=(Button)this.findViewById(R.id.pushTableBtn);
        pushBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,TableLayoutActivity.class);
                startActivity(intent);
            }
        });


        Button checkBtn=(Button)this.findViewById(R.id.pushCheckBoxBtn);
        checkBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,CheckBoxActivity.class);
                startActivity(intent);
            }
        });

        this.findViewById(R.id.pushRadidGroup).setOnClickListener(this);

        // 下拉选择
        this.findViewById(R.id.spinnerBtn).setOnClickListener(this);

        // 搜索btn
        this.findViewById(R.id.fileBtn).setOnClickListener(this);

        // 时钟
        this.findViewById(R.id.timeClock).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.pushRadidGroup:
            {
                Log.v("zhang","跳转单选页面");
                Intent intent = new Intent(MainActivity.this,RadioGroupActivity.class);
                startActivityForResult(intent,100);
            }

                break;
            case R.id.spinnerBtn:
            {

                Log.v("zhang","跳转下拉选择菜单");
                Intent intent = new Intent(MainActivity.this,SpinnerActivity.class);
                startActivity(intent);
            }
                break;
            case R.id.fileBtn:
            {
                Log.v("zhang","跳转文件搜索");
                Intent intent = new Intent(MainActivity.this,SeachFileActivity.class);
                startActivity(intent);
            }
                break;
            case R.id.timeClock:
            {
                Log.v("zhang","跳转时钟");
                startActivity(new Intent(MainActivity.this,TimeClockActivity.class));
            }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode)
        {
            case 100:
                // 单选activity
            {
                String resultStr = data.getExtras().getString("choseSex");
                Log.v("Andrew", resultStr);
            }
                break;

            case 1:
                // 页面传值
            {
                String resultStr = data.getExtras().getString("resultStr");
                Log.v("Andrew", resultStr);
            }
                break;
        }


    }
}
