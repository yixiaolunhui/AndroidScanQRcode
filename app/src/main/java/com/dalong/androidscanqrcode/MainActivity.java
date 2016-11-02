package com.dalong.androidscanqrcode;

import android.content.Context;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dalong.scanqrcodelib.core.IViewFinder;
import com.dalong.scanqrcodelib.view.ZXingScannerView;
import com.google.zxing.Result;

public class MainActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView mScannerView;
    //重置扫描时间
    private final int SCAN_TIME = 500;
    //是否播放声音
    private boolean isSound = false;
    //是否震动
    private boolean isVibrator = true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

    }

    private void init() {
        ViewGroup contentFrame = (ViewGroup) findViewById(R.id.content_frame);
        mScannerView = new ZXingScannerView(this) {
            @Override
            protected IViewFinder createViewFinderView(Context context) {
                return new CustomViewFinderView(context);
            }
        };
        contentFrame.addView(mScannerView);
        if (isSound) {
            SoundUtil.initSoundPool(this);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result rawResult) {
        Toast.makeText(this, ""+rawResult.getText(), Toast.LENGTH_SHORT).show();
        //播放声音
        if (isSound) SoundUtil.play(1, 0);

        //震动
        if (isVibrator) showVibrator();

        //重新启动扫描
        resumeCameraPreview();
    }


    /**
     * 重新启动扫描
     */
    public void resumeCameraPreview(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mScannerView.resumeCameraPreview(MainActivity.this);
            }
        }, SCAN_TIME);
    }

    /**
     * 震动效果
     */
    private void showVibrator() {
        Vibrator vibrator = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);
        long[] pattern = {30, 400};
        vibrator.vibrate(pattern, -1);
    }
}
