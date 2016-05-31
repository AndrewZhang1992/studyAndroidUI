package com.example.zhanglingyun.anroidtest;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.SQLException;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Date;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import util.AZDBHelper;

public class ContentProviderActivity extends AppCompatActivity {

    @InjectView(R.id.addNoteBtn)
    Button addNoteBtn;
    @InjectView(R.id.deleteLastBtn)
    Button deleteLastBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);
        ButterKnife.inject(this);
        this.setTitle("ContentProvider详讲");


        /**
         *
         *       <笔记>
         *
         *    ContentProvider :通过程序间的数据共享,它规范统一了数据接口.实际上 和 ios 中 openURL 是大致相同.
         *    规范 uri == > "content://指定访问的contentprovider/访问的db表名(访问的xml)/条件"
         *    例如: content://com.example.zhanglingyun.main.provider.NoteContentProvider/tb_notes/1
         *    数据表 tb_notes ,参数 为 id = 1
         *
         *
         */

        /***
         *
         *  1:
         *      创建 class 继承自 ContentProvider ,实现内部的方法.
         *      其中 有一个 onCreate() .一个应用程序中的 ContentProvider,当外部第一次调用时,开始创建,在创建之后,走onCreate(). (实际就是懒加载)
         *
         *
         *  2:
         *      在AndroidManifest.xml使用对该ContentProvider进行配置
         *


         <provider
                 android:authorities="com.example.zhanglingyun.main.provider.NoteContentProvider"
                 android:name="provider.NoteContentProvider"
                 android:exported="true" // 允许外部访问,默认只能在内部访问.
            >


         </provider>
         *
         *
         *  3:
         *     既然统一使用 uri 来进行通信,那么就需要对 uri 进行解析,类时路由. andorid 提供了 2个类,
         *     a: 当我们需要判断 uri 是哪一个的时候,需要 使用 UriMatcher ,
         *      例如: 操作数据表有 2个表,
         *
         *      content://com.example.zhanglingyun.main.provider.NoteContentProvider/tb_notes
         *
         *      content://com.example.zhanglingyun.main.provider.NoteContentProvider/tb_users
         *
         *      那么需要判断 是 操作那个一个表
         *
         *
         *
         */

        /*

        //常量UriMatcher.NO_MATCH表示不匹配任何路径的返回码
        UriMatcher  sMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        //如果match()方法匹配content://com.ljq.provider.personprovider/person路径，返回匹配码为1
        sMatcher.addURI("com.ljq.provider.personprovider", "person", 1);//添加需要匹配uri，如果匹配就会返回匹配码
        //如果match()方法匹配content://com.ljq.provider.personprovider/person/230路径，返回匹配码为2
        sMatcher.addURI("com.ljq.provider.personprovider", "person/#", 2);//#号为通配符
        switch (sMatcher.match(Uri.parse("content://com.ljq.provider.personprovider/person/10"))) {
            case 1
                break;
            case 2
                break;
            default://不匹配
                break;
        }

        */

        /**
         *
         *   b: ContentUris类  用于操作Uri路径后面的ID部分.
         *
         *      其中 2个方法 较常使用:
         *
         *      withAppendedId(uri, id)用于为路径加上ID部分
         *
         *      Uri uri = Uri.parse("content://com.ljq.provider.personprovider/person")
         Uri resultUri = ContentUris.withAppendedId(uri, 10);
         //生成后的Uri为：content://com.ljq.provider.personprovider/person/10

         *
         *      parseId(uri)方法用于从路径中获取ID部分
         *
         *      Uri uri = Uri.parse("content://com.ljq.provider.personprovider/person/10")
         long personid = ContentUris.parseId(uri);//获取的结果为:10
         *
         *
         */


        /***
         *
         *
         *      ContentProvider类主要方法的作用：

         public String getType(Uri uri)：该方法用于返回当前Url所代表数据的MIME类型。
         如果操作的数据属于集合类型，那么MIME类型字符串应该以vnd.android.cursor.dir/开头，
         例如：要得到所有person记录的Uri为content://com.ljq.provider.personprovider/person，那么返回的MIME类型字符串应该为："vnd.android.cursor.dir/person"。
         如果要操作的数据属于非集合类型数据，那么MIME类型字符串应该以vnd.android.cursor.item/开头，
         例如：得到id为10的person记录，Uri为content://com.ljq.provider.personprovider/person/10，那么返回的MIME类型字符串为："vnd.android.cursor.item/person"。
         *
         */


        /**
         *
         *      当外部应用需要对ContentProvider中的数据进行添加、删除、修改和查询操作时，可以使用ContentResolver 类来完成，
         *      要获取ContentResolver 对象，可以使用Activity提供的getContentResolver()方法。
         *
         */


        /*

            七、监听ContentProvider中数据的变化
如果ContentProvider的访问者需要知道ContentProvider中的数据发生变化，可以在ContentProvider发生数据变化时调用getContentResolver().notifyChange(uri, null)来通知注册在此URI上的访问者，例子如下：
public class PersonContentProvider extends ContentProvider {
   public Uri insert(Uri uri, ContentValues values) {
      db.insert("person", "personid", values);
      getContext().getContentResolver().notifyChange(uri, null);
   }
}
如果ContentProvider的访问者需要得到数据变化通知，必须使用ContentObserver对数据（数据采用uri描述）进行监听，当监听到数据变化通知时，系统就会调用ContentObserver的onChange()方法：
getContentResolver().registerContentObserver(Uri.parse("content://com.ljq.providers.personprovider/person"),
       true, new PersonObserver(new Handler()));
public class PersonObserver extends ContentObserver{
   public PersonObserver(Handler handler) {
      super(handler);
   }
   public void onChange(boolean selfChange) {
      //此处可以进行相应的业务处理
   }
}

八、权限设置与线程同步

1.      可以在代码中通过setReadPermission()和setWritePermission()两个方法来设置ContentProvider的操作权限，也可以在配置文件中通过android:readPermission和android:writePermission属性来控制。
2.      因为ContentProvider可能被不同的进程和线程调用，所以里面的方法必须是线程安全的。

         */


    }

    @OnClick({R.id.addNoteBtn,R.id.deleteLastBtn})
    protected void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.addNoteBtn:
            {
                Log.v("zhang","添加note到tb_notes表中");
                ContentValues note = new ContentValues();
                note.put("title","哈哈哈");
                note.put("note","我是笔记哦,哈哈哈"+new Date());
                this.getContentResolver().insert(Uri.parse("content://com.example.zhanglingyun.main.provider.NoteContentProvider/tb_notes"),note);
            }
                break;

            case R.id.deleteLastBtn:
            {
                Log.v("zhang","删除最后一条note");
                this.getContentResolver().delete(Uri.parse("content://com.example.zhanglingyun.main.provider.NoteContentProvider/tb_notes"),null,null);

            }
                break;
        }
    }


}
