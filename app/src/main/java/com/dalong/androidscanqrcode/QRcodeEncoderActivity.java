package com.dalong.androidscanqrcode;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dalong.androidscanqrcode.util.UriUtils;
import com.dalong.scanqrcodelib.util.QRCodeDecoder;

/**
 * 图库选择识别
 */
public class QRcodeEncoderActivity extends AppCompatActivity {

    private final  int INTENT_GET_PHOTO=100;
    private TextView qrcodeTv;
    private ImageView qrcodeImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode_encoder);
        qrcodeTv=(TextView)findViewById(R.id.qrcode_result);
        qrcodeImg=(ImageView)findViewById(R.id.qrcode_img);
    }


    /**
     * 相册选择图片识别
     * @param view
     */
    public void doTakePhoto(View view){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, INTENT_GET_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){


            case INTENT_GET_PHOTO://相册选择回来的回调
                if(resultCode == Activity.RESULT_OK){
                    if(null != data){
                        Uri mImageCaptureUri = data.getData();
                        String imgagePath= UriUtils.getImageAbsolutePath(this,mImageCaptureUri);
                        if(TextUtils.isEmpty(imgagePath))return;
                        String result=QRCodeDecoder.syncDecodeQRCode(imgagePath);
                        qrcodeTv.setText("识别内容为：\n"+result);

                        Bitmap bitmap=BitmapFactory.decodeFile(imgagePath);
                        qrcodeImg.setImageBitmap(bitmap);
                    }
                }
                break;
        }
    }
}
