package com.example.zhanglingyun.anroidtest;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import util.AZDBHelper;

public class NewNoteActivity extends AppCompatActivity {

    @InjectView(R.id.titleLabel)
    TextView titleLabel;
    @InjectView(R.id.inputTitle)
    EditText inputTitle;
    @InjectView(R.id.contentLabel)
    TextView contentLabel;
    @InjectView(R.id.inputContent)
    EditText inputContent;
    @InjectView(R.id.saveBtn)
    Button saveBtn;

    AZDBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);
        ButterKnife.inject(this);

        setTitle("新建笔记");

        dbHelper = new AZDBHelper(NewNoteActivity.this,"testDB",null,1);



    }
    @OnClick(R.id.saveBtn)
    protected void Onclick(View v)
    {
        // 保存
       SQLiteDatabase db = this.dbHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put("note",inputContent.getText().toString());
        cv.put("title",inputTitle.getText().toString());

        long result = db.insert("tb_notes",null,cv);
        if (result>0)
        {
            Toast.makeText(NewNoteActivity.this,"保存成功",Toast.LENGTH_SHORT).show();

            this.finish();

        }
    }
}
