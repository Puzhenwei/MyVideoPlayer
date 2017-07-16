package com.ming.universalvideoviewsample;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 将assets文件夹下的数据库写入SD卡中
 *
 * @author Dave
 */
public class ReadAssetsToSdcard {
    private final String TAG = "ReadAssetsToSdcard";
    private Context context;
    private String fileName = "lol.mp4";
    static String filePath = android.os.Environment.getExternalStorageDirectory() + File.separator + "lol";
    // static String filePath = "/sdcard/lol/lol.mp4";

    public ReadAssetsToSdcard(Context context) {
        this.context = context;
        if (!isExist()) {
            write();
        }
    }

    private void write() {
        InputStream inputStream;
        try {
            inputStream = context.getResources().getAssets().open(fileName);
            File file = new File(filePath);
            if (!file.exists()) {
                file.mkdirs();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(filePath + File.separator + fileName);
            byte[] buffer = new byte[512];
            int count = 0;
            while ((count = inputStream.read(buffer)) > 0) {
                fileOutputStream.write(buffer, 0, count);
            }
            fileOutputStream.flush();
            fileOutputStream.close();
            inputStream.close();
            Log.i(TAG, "success");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断是否存在该文件
     *
     * @return
     */
    private boolean isExist() {
        File file = new File(filePath + File.separator + fileName);
        return file.exists();
    }
}