package com.dalong.androidscanqrcode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    /**
     * 扫一扫二维码识别
     * @param view
     */
    public void doScanClick(View view){
        startActivity(new Intent(this,ScanActivity.class));
    }

    /**
     * 图库选择识别
     * @param view
     */
    public void doQRcodeDecoder(View view){
        startActivity(new Intent(this,QRcodeEncoderActivity.class));
    }

    /**
     * 生成二维码
     * @param view
     */
    public void doQRcodeEncoder(View view){
        startActivity(new Intent(this,QRcodeDecoderActivity.class));
    }
}
