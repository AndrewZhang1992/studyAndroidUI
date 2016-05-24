package com.example.zhanglingyun.anroidtest;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import butterknife.ButterKnife;
import butterknife.InjectView;
import model.User;

public class ListViewActivity extends AppCompatActivity {

    @InjectView(R.id.userList)
    ListView userList;

    private ArrayList<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        ButterKnife.inject(this);
        setTitle("学习ListView使用");


        getUsers();

        bindData();
    }

    private void getUsers()
    {
        users=new ArrayList<User>();

        users.add(new User("张三","21",true));

        users.add(new User("李婷","18",false));

        users.add(new User("王欣裕","20",false));

    }

    private void bindData()
    {

        UserAdapter userAdapter = new UserAdapter(this,users);
        userList.setAdapter(userAdapter);
    }


    public class UserAdapter extends BaseAdapter
    {
//
        private LayoutInflater layoutInflater;
        private ArrayList<User> users;

        public UserAdapter(Context context,ArrayList<User>users) {
            this.layoutInflater=LayoutInflater.from(context);
            this.users=users;
        }

        public final class UserView
        {
            public TextView nameText;
            public TextView ageText;
            public TextView sexText;
        }


        @Override
        public int getCount() {
            return this.users.size();
        }

        @Override
        public User getItem(int i) {
            return this.users.get(i);
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {



            UserView userView;
            if (view==null)
            {
                userView=new UserView();
                // 加载 自定义list_user.xml 文件
                view=this.layoutInflater.inflate(R.layout.list_user,null);

                userView.nameText=(TextView)view.findViewById(R.id.name);
                userView.ageText=(TextView)view.findViewById(R.id.age);
                userView.sexText=(TextView)view.findViewById(R.id.sex);
                view.setTag(userView);
            }else {
                userView=(UserView)view.getTag();
            }


            // model 赋值 view
            User user=getItem(i);

            userView.nameText.setText(user.getName());
            userView.ageText.setText(user.getAge());
            userView.ageText.setText(user.isSex()?"男":"女");


            return view;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }
    }

}
