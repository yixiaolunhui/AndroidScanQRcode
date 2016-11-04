package com.dalong.androidscanqrcode.util;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import com.dalong.androidscanqrcode.R;

import java.util.HashMap;
import java.util.Map;

/**
 * 声音工具类
 * Created by zhouweilong on 2016/11/2.
 */

public class SoundUtil {

    public static SoundPool sp ;
    public static Map<Integer, Integer> suondMap;
    public static Context context;

    /**
     * 初始化声音池
     * @param context
     */
    public static void initSoundPool(Context context){
        SoundUtil.context = context;
        sp = new SoundPool(1, AudioManager.STREAM_MUSIC, 1);
        suondMap = new HashMap<Integer, Integer>();
        suondMap.put(1, sp.load(context, R.raw.beep, 1));

    }

    /**
     * 播放声音池声音
     * @param sound
     * @param number
     */
    public static  void play(int sound, int number){
        AudioManager am = (AudioManager) SoundUtil.context.getSystemService(SoundUtil.context.AUDIO_SERVICE);
        //返回当前AlarmManager最大音量
        float audioMaxVolume = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

        //返回当前AudioManager对象的音量值
        float audioCurrentVolume = am.getStreamVolume(AudioManager.STREAM_MUSIC);
        float volumnRatio = audioCurrentVolume/audioMaxVolume;
        sp.play(
                suondMap.get(sound), //播放的音乐Id
                audioCurrentVolume, //左声道音量
                audioCurrentVolume, //右声道音量
                1, //优先级，0为最低
                number, //循环次数，0无不循环，-1无永远循环
                1);//回放速度，值在0.5-2.0之间，1为正常速度
    }
}
