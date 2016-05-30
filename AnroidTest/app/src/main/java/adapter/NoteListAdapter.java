package adapter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.zhanglingyun.anroidtest.R;

import java.util.ArrayList;

import model.Note;
import model.User;
import util.AZDBHelper;

/**
 * Created by zhanglingyun on 16/5/30.
 */
public class NoteListAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private ArrayList<Note> notes;

    public ArrayList<Note> getNotes() {
        return notes;
    }

    private AZDBHelper dbHelper;

   public  NoteListAdapter(Context context){
       layoutInflater=LayoutInflater.from(context);
       dbHelper = new AZDBHelper(context,"testDB",null,1);
       SQLiteDatabase db = dbHelper.getReadableDatabase();
       notes=new ArrayList<Note>();

       Cursor cursor = db.query("tb_notes",null,null,null,null,null,null);

       if (cursor.moveToFirst())
       {
           while (!cursor.isAfterLast())
           {
               Note note = new Note(cursor.getInt(cursor.getColumnIndex("id")),cursor.getString(cursor.getColumnIndex("title")),cursor.getString(cursor.getColumnIndex("note")));
               notes.add(note);
               cursor.moveToNext();
           }
       }
       
   }

    public class NoteView
    {
        private TextView titelTextView;
        private TextView contentTextView;
    }


    @Override
    public int getCount() {
        return notes.size();
    }

    @Override
    public Note getItem(int i) {
        return notes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        NoteView noteView;

        if (view==null)
        {
            noteView = new NoteView();

            view = this.layoutInflater.inflate(R.layout.list_note,null);

            noteView.titelTextView=(TextView)view.findViewById(R.id.noteTitle);
            noteView.contentTextView=(TextView)view.findViewById(R.id.noteContent);

            view.setTag(noteView);
        }else {
            noteView = (NoteView)view.getTag();
        }

        Note note = this.notes.get(i);
        noteView.titelTextView.setText(note.getTitle().toString());
        noteView.contentTextView.setText(note.getContent().toString());

        return view;
    }
}
