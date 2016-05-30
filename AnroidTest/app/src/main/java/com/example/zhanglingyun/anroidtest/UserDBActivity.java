package com.example.zhanglingyun.anroidtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import adapter.NoteListAdapter;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.OnItemClick;
import model.Note;
import util.AZDBHelper;

public class UserDBActivity extends AppCompatActivity {

    @InjectView(R.id.titleLabel)
    TextView titleLabel;
    @InjectView(R.id.noteList)
    ListView noteList;
    @InjectView(R.id.addNoteBtn)
    Button addNoteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_db);
        ButterKnife.inject(this);

        // 初始化数据库
        AZDBHelper dbHelper = new AZDBHelper(UserDBActivity.this,"testDB",null,1);


        noteList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                NoteListAdapter noteListAdapter = (NoteListAdapter)noteList.getAdapter();
                ArrayList<Note> notes = noteListAdapter.getNotes();

                Note note = notes.get(i);

                Intent intent = new Intent(UserDBActivity.this,LookNoteActivity.class);
                intent.putExtra("note",note);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        NoteListAdapter noteListAdapter = new NoteListAdapter(UserDBActivity.this);
        noteList.setAdapter(noteListAdapter);

    }




    @OnClick(R.id.addNoteBtn)
    protected void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.addNoteBtn:
            {
                Log.v("zhang","新建笔记");
                startActivity(new Intent(UserDBActivity.this,NewNoteActivity.class));
            }
                break;
        }
    }




}
