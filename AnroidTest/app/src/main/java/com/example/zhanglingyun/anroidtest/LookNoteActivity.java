package com.example.zhanglingyun.anroidtest;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import model.Note;
import util.AZDBHelper;

public class LookNoteActivity extends AppCompatActivity {

    @InjectView(R.id.titleLabel)
    TextView titleLabel;
    @InjectView(R.id.contentLabel)
    TextView contentLabel;
    @InjectView(R.id.updateBtn)
    Button updateBtn;
    @InjectView(R.id.deleteBtn)
    Button deleteBtn;

    private AZDBHelper dbHelper;

    private Note note;

    public AZDBHelper getDbHelper() {
        if (dbHelper == null)
        {
            dbHelper = new AZDBHelper(LookNoteActivity.this,"testDB",null,1);
        }
        return dbHelper;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look_note);
        ButterKnife.inject(this);

        setTitle("查看笔记");

        note = (Note) getIntent().getSerializableExtra("note");
        titleLabel.setText(note.getTitle().toString());
        contentLabel.setText(note.getContent());
    }

    @OnClick({R.id.updateBtn,R.id.deleteBtn})
    protected void OnClick(View v)
    {
        switch (v.getId())
        {
            case R.id.updateBtn:
            {
                ContentValues cv = new ContentValues();
                cv.put("title",titleLabel.getText().toString());
                cv.put("note",contentLabel.getText().toString());
                String[] ags= {Integer.toString(note.getId())};
                int result = this.getDbHelper().getWritableDatabase().update("tb_notes",cv,"id=?",ags);
                if (result>0)
                {
                    Toast.makeText(LookNoteActivity.this,"修改成功",Toast.LENGTH_SHORT).show();

                }
            }
                break;

            case R.id.deleteBtn:
            {
                String[] ags= {Integer.toString(note.getId())};
                int result =  this.getDbHelper().getWritableDatabase().delete("tb_notes","id=?",ags);

                if (result>0)
                {
                    titleLabel.setText("");
                    contentLabel.setText("无内容");
                    Toast.makeText(LookNoteActivity.this,"已删除",Toast.LENGTH_SHORT).show();
                }
            }
                break;
        }

    }

}
