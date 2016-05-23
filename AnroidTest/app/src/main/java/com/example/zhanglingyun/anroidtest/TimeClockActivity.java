package com.example.zhanglingyun.anroidtest;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.AnalogClock;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.SimpleFormatter;

public class TimeClockActivity extends AppCompatActivity {

    public AnalogClock mClock;
    private Calendar calendar;

    private MyHander hander;
    private TextView timeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_clock);

        this.bindUI();

        // 创建handler
        hander = new MyHander();

        // 异步线程
        MyThredRunnalbe myThredRunnalbe = new MyThredRunnalbe("ascy_thread");
        Thread thread = new Thread(myThredRunnalbe);

        // 开始thred
        thread.start();
    }


    // bind ui
    private void bindUI()
    {
        mClock=(AnalogClock) this.findViewById(R.id.clock);
        timeTextView=(TextView) this.findViewById(R.id.timeTextView);

    }


    public class MyHander extends Handler
    {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what==100)
            {
                Bundle bundle = msg.getData();
//                Log.v("zhang","hour--- "+bundle.get("hour").toString());
//                Log.v("zhang","minus--- "+bundle.get("minus").toString());
                timeTextView.setText(bundle.get("time").toString());
            }

        }
    }


    public class MyThredRunnalbe implements Runnable
    {
        private String name;

        public MyThredRunnalbe(String name)
        {
            this.name=name;
        }


        @Override
        public void run() {

            while (true)
            {
                // 获取系统时间
//                long time = System.currentTimeMillis();
//                final Calendar calendar= Calendar.getInstance();
//                calendar.setTimeInMillis(time);
                Date date = new Date();
//                        calendar.getTime();
                SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss");
                String dateStr=formatter.format(date);
//                Log.v("zhang","time--- "+time+"");
//                Log.v("zhang","minus--- "+minute);

                // 休眠1s
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Bundle bundle = new Bundle();
                bundle.putString("time",dateStr);
             ;

                Message message = new Message();
                message.setData(bundle);
                message.what=100;
                TimeClockActivity.this.hander.sendMessage(message);

            }

        }
    }




}
