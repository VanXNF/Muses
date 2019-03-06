package com.victorxu.muses.util;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;

import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.UCropActivity;

import java.io.File;

import androidx.fragment.app.Fragment;

public class CropUtil {

    public static final int CROP_CODE = 0x0010;
    public static int MAX_WIDTH = 1080;
    public static int MAX_HEIGHT = 1080;

    public static void startUCrop(Activity activity, Fragment fragment, String sourceFilePath, int requestCode, int maxWidth, int maxHeight) {
        Uri sourceUri = Uri.fromFile(new File(sourceFilePath));
        File outDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Muses/temp");
        if (!outDir.exists()) {
            outDir.mkdirs();
        }

        File outFile = new File(outDir, "Temp.jpg");

        Uri destinationUri = Uri.fromFile(outFile);
        //初始化，第一个参数：需要裁剪的图片；第二个参数：裁剪后图片
        UCrop uCrop = UCrop.of(sourceUri, destinationUri);
        //初始化UCrop配置
        UCrop.Options options = new UCrop.Options();
        //压缩
        options.setCompressionFormat(Bitmap.CompressFormat.JPEG);
        options.setCompressionQuality(90);
        //设置裁剪图片可操作的手势
        options.setAllowedGestures(UCropActivity.SCALE, UCropActivity.ROTATE, UCropActivity.ALL);
        //是否能调整裁剪框
        options.setFreeStyleCropEnabled(true);
        //UCrop配置
        uCrop.withOptions(options);
        //最大宽高
        uCrop.withMaxResultSize(maxWidth, maxHeight);
        //跳转裁剪页面
        uCrop.start(activity, fragment, requestCode);
    }
}
