package com.example.zhanglingyun.anroidtest;

import android.support.annotation.BoolRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    public  static  final int ITEM0 = Menu.FIRST;
    public  static  final int ITEM1 = Menu.FIRST+1;

    TextView firstTextView, secondTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        bindUI();


    }


    private void bindUI() {
        firstTextView = (TextView) this.findViewById(R.id.firstTextView);
        secondTextView = (TextView) this.findViewById(R.id.secondTextView);

        firstTextView.setVisibility(View.INVISIBLE);
        secondTextView.setVisibility(View.INVISIBLE);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        super.onCreateOptionsMenu(menu);


        /**
         *   MenuItem.setIcon 可以设置menu的背景图片
         *
         */
        MenuItem firstItem =  menu.add(0,ITEM0,0,"FIRST");

        menu.add(0,ITEM1,0,"SECOND");

        menu.findItem(ITEM0);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         super.onOptionsItemSelected(item);

        switch (item.getItemId())
        {
            case ITEM0:
            {
                //
                this.setTitle("点击第一个menu");
                this.firstTextView.setVisibility(View.VISIBLE);
                this.secondTextView.setVisibility(View.INVISIBLE);
            }
            break;

            case ITEM1:
            {
                this.setTitle("点击第二个menu");
                this.firstTextView.setVisibility(View.INVISIBLE);
                this.secondTextView.setVisibility(View.VISIBLE);
            }
            break;
        }


         return true;
    }
}
