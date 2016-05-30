package util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import junit.framework.Assert;

import java.util.HashMap;

/**
 * Created by zhanglingyun on 16/5/30.
 */
public class AZDBHelper extends SQLiteOpenHelper {


    /**
     *
     * 初始化一个 db ,如果改db不存在,则创建一个名为 name 的 db
     *
     * @param context 操作上下文
     * @param name  数据库名称
     * @param factory
     * @param version 版本号,当发现版本号变化时,会走 onUpgrade 升级的方法,进行数据迁移
     *
     */
    public AZDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // 创建数据表,只会在 数据库第一次创建时调用.

        // 建表操作
        HashMap<String,String> recondMap = new HashMap<String,String>();

        recondMap.put("id","integer");
        recondMap.put("title","text");
        recondMap.put("note","text");

        String createTabelSql = this.getCreateTableSQL("tb_notes",recondMap,"id");

        sqLiteDatabase.execSQL(createTabelSql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        // 数据迁移

    }

    /**
     *  获取建表 sql
     *
     * @param dbName
     * @param keyVaules
     * @param primaryKey 主键
     * @return
     */
    public String getCreateTableSQL(String dbName, HashMap<String,String>keyVaules,String primaryKey)
    {
        Assert.assertNotNull("DB名称不能为空",dbName);
        Assert.assertNotNull("表结构不能为空",keyVaules);

        String sql="create table if not exists "+dbName+"(";

        for (String key :
                keyVaules.keySet()) {

            String recondStr= key+" "+keyVaules.get(key).toString()+",";
            sql+=recondStr+" ";
        }

        if (primaryKey!=null && primaryKey.length()>0)
        {
            sql+="primary key("+primaryKey+")";
        }

        sql+=")";

        return sql;
    }

}
