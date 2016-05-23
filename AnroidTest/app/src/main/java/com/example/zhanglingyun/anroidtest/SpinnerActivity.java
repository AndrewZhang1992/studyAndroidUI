package com.example.zhanglingyun.anroidtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class SpinnerActivity extends AppCompatActivity {

    private  final  String[] countrys= {"中国","美国","德国","英国","法国"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        // 为spinner赋值

        // 第一种
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,countrys);

        Spinner spinner = (Spinner)this.findViewById(R.id.spinner);
//        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
//        spinner.setAdapter(adapter);

        // 第二种
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.countrys,android.R.layout.simple_spinner_item);

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter1);

    }
}
