package com.tpout.baselib.util;

import android.content.Context;
import android.media.MediaScannerConnection;
import android.net.Uri;

public class MediaScanner implements MediaScannerConnection.MediaScannerConnectionClient {

    private MediaScannerConnection mediaScanConn = null;

    public MediaScanner(Context context) {
        mediaScanConn = new MediaScannerConnection(context, this);
    }

    /**
     * 文件路径集合
     **/
    private String[] filePaths;
    private int fileNum;
    /**
     * 文件MimeType集合
     **/
    private String[] mimeTypes;

    /**
     * 扫描文件
     */
    public void scanFiles(String[] filePaths, String[] mimeTypes) {
        this.filePaths = filePaths;
        this.mimeTypes = mimeTypes;
        fileNum = this.filePaths.length;
        mediaScanConn.connect();//连接扫描服务
    }

    @Override
    public void onMediaScannerConnected() {
        for (int i = 0; i < fileNum; i++) {
            mediaScanConn.scanFile(filePaths[i], mimeTypes[i]);//服务回调执行扫描
        }
        filePaths = null;
        mimeTypes = null;
    }

    private int scanTimes = 0;

    /**
     * 扫描一个文件完成
     */
    @Override
    public void onScanCompleted(String path, Uri uri) {
        scanTimes++;
        if (scanTimes == fileNum) {//如果扫描完了全部文件
            mediaScanConn.disconnect();//断开扫描服务
            scanTimes = 0;//复位计数
        }
    }
}
