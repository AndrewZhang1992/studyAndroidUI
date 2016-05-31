package provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.util.Date;

import util.AZDBHelper;

/**
 * Created by zhanglingyun on 16/5/30.
 */
public class NoteContentProvider extends ContentProvider {


    private UriMatcher _uriMatcher;
    private AZDBHelper _azdbHelper;

    /**
     * 懒加载
     * @return
     */
    public AZDBHelper getAzdbHelper() {
        if (_azdbHelper == null)
        {
            _azdbHelper = new AZDBHelper(this.getContext(),"testDB",null,1);
        }
        return _azdbHelper;
    }
    public UriMatcher getUriMatcher() {
        if (_uriMatcher == null){
            _uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
            _uriMatcher.addURI("com.example.zhanglingyun.main.provider.NoteContentProvider","tb_notes",1);
        }
        return _uriMatcher;
    }


    @Override
    public boolean onCreate() {

        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {

        // 插入
        int code =  this.getUriMatcher().match(uri);

        if (code==1)
        {
            // 操作tb_notes 表
            this.addOneNoteToTable(contentValues);
        }

        return uri;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {

        int code =  this.getUriMatcher().match(uri);

        if (code==1)
        {
            this.deleteLastNoteFormTable();
        }

        return 1;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }

    /**
     * 添加一条记录
     */
    private void addOneNoteToTable(ContentValues note)
    {
        long result =  this.getAzdbHelper().getWritableDatabase().insert("tb_notes",null,note);
        if (result>0)
        {
            Toast.makeText(this.getContext(),"添加成功",Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 删除最后一条note
     */
    private void deleteLastNoteFormTable()
    {

        String sql = "delete from tb_notes where id like (select id from tb_notes order by id desc limit 1)";

        try {
            this.getAzdbHelper().getWritableDatabase().execSQL(sql);
            Toast.makeText(this.getContext(),"删除最后一条成功",Toast.LENGTH_SHORT).show();
        }catch (SQLException e){
            Log.v("zhang",e.toString());
            e.printStackTrace();
        }
    }
}
