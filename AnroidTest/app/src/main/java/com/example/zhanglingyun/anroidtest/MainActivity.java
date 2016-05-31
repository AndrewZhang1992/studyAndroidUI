package com.example.zhanglingyun.anroidtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @InjectView(R.id.textview)
    TextView textview;
    @InjectView(R.id.tixingTextView)
    TextView tixingTextView;
    @InjectView(R.id.button)
    Button button;
    @InjectView(R.id.pushTableBtn)
    Button pushTableBtn;
    @InjectView(R.id.pushCheckBoxBtn)
    Button pushCheckBoxBtn;
    @InjectView(R.id.pushRadidGroup)
    Button pushRadidGroup;
    @InjectView(R.id.spinnerBtn)
    Button spinnerBtn;
    @InjectView(R.id.fileBtn)
    Button fileBtn;
    @InjectView(R.id.timeClock)
    Button timeClock;
    @InjectView(R.id.line1)
    LinearLayout line1;
    @InjectView(R.id.menuBtn)
    Button menuBtn;
    @InjectView(R.id.listviewBtn)
    Button listviewBtn;
    @InjectView(R.id.noticationBtn)
    Button noticationBtn;
    @InjectView(R.id.userDBBtn)
    Button userDBBtn;
    @InjectView(R.id.line2)
    LinearLayout line2;
    @InjectView(R.id.contentProviderBtn)
    Button contentProviderBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        Button button = (Button) this.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // 不穿值跳转
//                startActivity(new Intent(MainActivity.this,MyActivity.class));

                // 传参数跳转
                Intent intent = new Intent(MainActivity.this, MyActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("name", "Andrew");
                bundle.putString("age", "25");
                intent.putExtras(bundle);
                startActivityForResult(intent, 1);
            }

        });

        Button pushBtn = (Button) this.findViewById(R.id.pushTableBtn);
        pushBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TableLayoutActivity.class);
                startActivity(intent);
            }
        });


        Button checkBtn = (Button) this.findViewById(R.id.pushCheckBoxBtn);
        checkBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CheckBoxActivity.class);
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

        // menu
        this.findViewById(R.id.menuBtn).setOnClickListener(this);

        // listView
        listviewBtn.setOnClickListener(this);

        noticationBtn.setOnClickListener(this);

        // 使用db 实现 笔记本
        userDBBtn.setOnClickListener(this);


        // ContentProvider
        contentProviderBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.pushRadidGroup: {
                Log.v("zhang", "跳转单选页面");
                Intent intent = new Intent(MainActivity.this, RadioGroupActivity.class);
                startActivityForResult(intent, 100);
            }

            break;
            case R.id.spinnerBtn: {

                Log.v("zhang", "跳转下拉选择菜单");
                Intent intent = new Intent(MainActivity.this, SpinnerActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.fileBtn: {
                Log.v("zhang", "跳转文件搜索");
                Intent intent = new Intent(MainActivity.this, SeachFileActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.timeClock: {
                Log.v("zhang", "跳转时钟");
                startActivity(new Intent(MainActivity.this, TimeClockActivity.class));
            }
            break;
            case R.id.menuBtn: {
                Log.v("zhang", "跳转Menu");
                startActivity(new Intent(MainActivity.this, MenuActivity.class));
            }
            break;
            case R.id.listviewBtn: {
                Log.v("zhang", "跳转Listview");
                startActivity(new Intent(MainActivity.this, ListViewActivity.class));
            }
            break;
            case R.id.noticationBtn: {
                Log.v("zhang", "跳转Notication");
                startActivity(new Intent(MainActivity.this, NoticationActivity.class));
            }
            break;
            case R.id.userDBBtn: {
                Log.v("zhang", "跳转db笔记本");
                startActivity(new Intent(MainActivity.this, UserDBActivity.class));
            }
            break;
            case R.id.contentProviderBtn:{
                Log.v("zhang","跳转ContentProvider详讲");
                startActivity(new Intent(MainActivity.this,ContentProviderActivity.class));
            }
            break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
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
