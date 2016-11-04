package com.dalong.androidscanqrcode;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.dalong.androidscanqrcode.util.color.ColorPickerDialog;
import com.dalong.scanqrcodelib.util.QRCodeEncoder;

/**
 * 生成二维码
 */
public class QRcodeDecoderActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mQrcodeET;
    private Button mQrcodeBt;
    private ImageView mQrcodeImg;
    private CheckBox mQrcodeCb;
    private Button mColorBt;
    private int QECODE_DEFAULT_COLOR=Color.BLACK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode_decoder);
        initView();
    }

    private void initView() {
        mQrcodeET=(EditText)findViewById(R.id.qrcode_ed);
        mQrcodeBt=(Button)findViewById(R.id.qrcode_bt);
        mColorBt=(Button)findViewById(R.id.color_bt);
        mQrcodeImg = (ImageView) findViewById(R.id.qrcode_img);
        mQrcodeCb = (CheckBox) findViewById(R.id.qrcode_cb);
        mQrcodeBt.setOnClickListener(this);
        mColorBt.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.qrcode_bt:
                String qrcodeString=mQrcodeET.getText().toString().toString();
                if(TextUtils.isEmpty(qrcodeString)){
                    Toast.makeText(this, "请输入数据", Toast.LENGTH_SHORT).show();
                    return;
                }

                Bitmap qrcode;
                if(!mQrcodeCb.isChecked()){
                    qrcode=  QRCodeEncoder.syncEncodeQRCode(qrcodeString,500,QECODE_DEFAULT_COLOR);
                }else{
                    Bitmap logo=BitmapFactory.decodeResource(getResources(),R.mipmap.logo);
                    qrcode= QRCodeEncoder.syncEncodeQRCode(qrcodeString,500, QECODE_DEFAULT_COLOR,logo);
                }
                mQrcodeImg.setImageBitmap(qrcode);
                break;
            case  R.id.color_bt:
                ColorPickerDialog colorPickerDialog = new ColorPickerDialog(this, QECODE_DEFAULT_COLOR, new ColorPickerDialog.OnColorSelectedListener() {
                    @Override
                    public void onColorSelected(int newColor) {
                        colorChanged(newColor);
                    }
                });
                colorPickerDialog.show();
                break;
        }
    }

    private void colorChanged(int newColor) {
        QECODE_DEFAULT_COLOR=newColor;
    }
}
