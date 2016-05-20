package com.example.zhanglingyun.anroidtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);


        String name = this.getIntent().getExtras().getString("name");
        String age = this.getIntent().getExtras().getString("age");
        Log.v("m","age="+age+"name="+name);

        Button backBtn=(Button)this.findViewById(R.id.backBtn);

        backBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                Intent intet=new Intent();

                intet.putExtra("resultStr","HAHA I AM ANDREW");

                MyActivity.this.setResult(1,intet);

                MyActivity.this.finish();
            }
        });


    }

}
