package com.example.zhanglingyun.anroidtest;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class NoticationActivity extends AppCompatActivity {

    @InjectView(R.id.noticationBtn)
    Button noticationBtn;

    private Notification noti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notication);
        ButterKnife.inject(this);



    }

    @OnClick(R.id.noticationBtn)
    public void onClik(View view)
    {
        switch (view.getId())
        {
            case R.id.noticationBtn:
            {

                // 获取 NotificationManager (单例)
                NotificationManager notificationManager = (NotificationManager)this.getSystemService(NOTIFICATION_SERVICE);

                // 创建 NotificationCompat.Builder,通过它我们可以得到一个已经包装好的 notification 对象
                NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

                Bitmap bitmap = ((BitmapDrawable)getResources().getDrawable(R.drawable.bg12)).getBitmap();


                // 即将执行的意图
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.meituan.com"));

                PendingIntent pendingIntent = PendingIntent.getActivity(this,10,intent,PendingIntent.FLAG_UPDATE_CURRENT);



                builder.setLargeIcon(bitmap)
                        .setSmallIcon(R.drawable.samllicon)
                        .setTicker("哈喽:我是notifictaion哦")
                        .setContentText("快来啊,618全场7折,保证不会毒死你")
                        .setContentTitle("美团")
                        .setContentIntent(pendingIntent);


                noti=builder.getNotification();

                notificationManager.notify(10,noti);
            }
            break;
        }
    }



}
