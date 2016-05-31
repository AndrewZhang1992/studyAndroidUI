package com.example.zhanglingyun.testcontentprovider;

import android.content.ContentValues;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.findViewById(R.id.addNoteBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("zhang","添加note到tb_notes表中");
                ContentValues note = new ContentValues();
                note.put("title","来自另一个app创建");
                note.put("note","我是笔记哦,哈哈哈"+new Date());
                MainActivity.this.getContentResolver().insert(Uri.parse("content://com.example.zhanglingyun.main.provider.NoteContentProvider/tb_notes"),note);
            }
        });
    }
}
