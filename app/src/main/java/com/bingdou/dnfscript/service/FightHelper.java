package com.bingdou.dnfscript.service;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.GestureDescription;
import android.app.Activity;
import android.app.Service;
import android.graphics.Bitmap;
import android.graphics.Path;
import android.graphics.Rect;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;

import com.bingdou.dnfscript.tools.Logger;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.TextRecognizer;
import com.google.mlkit.vision.text.chinese.ChineseTextRecognizerOptions;

/**
 * 无障碍功能帮助类
 */
public class FightHelper {

    private static final String TAG = "AccessibilityHelper";

    private FightHelper() {
    }

    private static class Holder {
        private static FightHelper instance = new FightHelper();
    }

    public static FightHelper getInstance() {
        return Holder.instance;
    }

    public boolean judgeIsFight(Activity activity){

        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        // 获取屏幕宽高
        int w = view.getWidth();
        int h = view.getHeight();
        Logger.d("zhangbing111", "w = " + w + "; h =" + h);
        // 去掉状态栏
        Bitmap imageBitmap = Bitmap.createBitmap(view.getDrawingCache(), 0, 0, w, h);
        // 销毁缓存信息
        view.destroyDrawingCache();
        InputImage image = InputImage.fromBitmap(imageBitmap, 0);
        ChineseTextRecognizerOptions build = new ChineseTextRecognizerOptions.Builder().build();
        TextRecognizer recognizer = TextRecognition.getClient(build);
        recognizer.process(image)
                .addOnSuccessListener(result -> {
                    for (Text.TextBlock block : result.getTextBlocks()) {
                        String blockText = block.getText();
                        Logger.d("zhangbing", "blockText = " + blockText);
                        Rect blockFrame = block.getBoundingBox();
                        Logger.d("zhangbing", "blockFrame = " + blockFrame.toString());
                        int centerX1 = blockFrame.centerX();
                        int centerY1 = blockFrame.centerY();
                        Logger.d("zhangbing", "centerX1 = " + centerX1 + "; centerY1 = " + centerY1);
                    }
                    recognizer.close();
                })
                .addOnFailureListener(e -> {
                    // 处理错误
                    e.printStackTrace();
                    // 释放资源
                    recognizer.close();
                });

        return false;
    }

}
