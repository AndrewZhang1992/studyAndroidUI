package com.example.zhanglingyun.anroidtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class SeachFileActivity extends AppCompatActivity implements View.OnClickListener {


    private Button satrtSearhBtn;
    private EditText searchInputText;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seach_file);

        // 绑定UI
        this.bindUI();



    }


    private  void  bindUI()
    {
        satrtSearhBtn=(Button)this.findViewById(R.id.seach_file_startBtn);
        satrtSearhBtn.setOnClickListener(this);

        searchInputText=(EditText)this.findViewById(R.id.seach_file_inputText);

        resultText=(TextView)this.findViewById(R.id.seach_file_resulePathText);
    }



    @Override
    public void onClick(View view) {

        if (searchInputText.getText().toString().equals(""))
        {
            Toast.makeText(this,"目标文件不能为空!",Toast.LENGTH_SHORT).show();

        }else
        {
            String fileName=searchInputText.getText().toString();
            File[] filePaths=new File("/").listFiles();

            String reslutStr="";

            for (File file :
                    filePaths)
            {
                if (file.getName().contains(fileName))
                {
                    reslutStr=reslutStr+file+"\n";
                }
            }

            if (reslutStr.equals(""))
            {
                reslutStr=reslutStr+"找不到文件";
            }

            resultText.setText(reslutStr);
        }

    }
}
