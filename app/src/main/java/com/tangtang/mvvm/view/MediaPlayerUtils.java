package com.tangtang.mvvm.view;

import android.media.MediaPlayer;

import java.io.IOException;

/**
 * Author:
 * Version    V1.0
 * Date:      2020/8/12
 * Description:商品拿起音效播放帮助类
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2020/8/12                  1.0                    1.0
 * Why & What is modified:
 */
public class MediaPlayerUtils {

  public static volatile MediaPlayerUtils INSTANCE = null;
  private MediaPlayer mMediaPlayer;

  private MediaPlayerUtils(){
    initMedia();
  }

  public static MediaPlayerUtils init() {
    if (INSTANCE == null) {
      synchronized (MediaPlayerUtils.class) {
        if (INSTANCE == null) {
          INSTANCE = new Builder().build();
        }
      }
    }
    return INSTANCE;
  }

  private static class Builder {
    private Builder() {

    }

    private MediaPlayerUtils build() {
      return new MediaPlayerUtils();
    }
  }

  public static MediaPlayerUtils getInstance() {
    if (INSTANCE == null) {
      throw new IllegalArgumentException("MediaPlayerUtils getInstance方法必须在 init 方法之后调用!!!!");
    }
    return INSTANCE;
  }

  private void initMedia(){
    mMediaPlayer = new MediaPlayer();
    //设置左右声道音量
    mMediaPlayer.setVolume(0.7f, 0.7f);
    mMediaPlayer.setLooping(false);
    mMediaPlayer.setOnPreparedListener(mp -> {
      mp.start();
    });
  }

  /**
   * 播放
   * @param path 音频路径
   */
  public void play(String path){
    try {
      if (mMediaPlayer.isPlaying()){
        mMediaPlayer.stop();
      }
      mMediaPlayer.reset();
      mMediaPlayer.setDataSource(path);
      mMediaPlayer.prepareAsync();
//      mMediaPlayer.start();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void stop(){
    if (mMediaPlayer != null && mMediaPlayer.isPlaying()){
      mMediaPlayer.stop();
      mMediaPlayer.reset();
    }
  }

  /**
   * 释放资源
   */
  public void release(){
    if (mMediaPlayer != null){
      mMediaPlayer.stop();
      mMediaPlayer.reset();
      mMediaPlayer.release();
      mMediaPlayer = null;
    }
  }
}
