package com.bingdou.dnfscript.tools;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.provider.Settings;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.TextRecognizer;
import com.google.mlkit.vision.text.chinese.ChineseTextRecognizerOptions;

import java.util.List;

public class RecognitionUtils {
    public static final String TAG = "APPUtils";

    private void screenCaptureNoStatusBar(Activity activity) {
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
        recognizeTextFromBitmap(imageBitmap);
    }

    private void recognizeTextFromBitmap(Bitmap bitmap) {
        InputImage image = InputImage.fromBitmap(bitmap, 0);
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
//                            for (Text.Line line : block.getLines()) {
//                                String lineText = line.getText();
//                                Logger.d("zhangbing", "lineText = " + lineText);
//                                Point[] lineCornerPoints = line.getCornerPoints();
//                                Rect lineFrame = line.getBoundingBox();
//                                // 使用Rect的方法直接获取中心坐标
//                                int centerX = lineFrame.centerX();
//                                int centerY = lineFrame.centerY();
//                                if (lineText.equals("拍照")) {
//                                    mCenterX = centerX;
//                                    mCenterY = centerY;
//                                    Logger.d("zhangbing", "centerX = " + centerX + "; centerY = " + centerY);
////                                    HandleControl.getInstance().sendPointerSync1(centerX, centerY);
//                                    boolean b = GestureHelper.getInstance().clickByGesture(MyAccessibilityService.mService, centerX, centerY);
//                                    Logger.d("zhangbing", "GestureHelper b = " + b);
//                                }
////                                Logger.d("zhangbing", "lineFrame = " + lineFrame.toString());
//                                for (Text.Element element : line.getElements()) {
//                                    String elementText = element.getText();
////                                    Logger.d("zhangbing", "elementText = " + elementText);
//                                    Point[] elementCornerPoints = element.getCornerPoints();
//                                    Rect elementFrame = element.getBoundingBox();
//                                    for (Text.Symbol symbol : element.getSymbols()) {
//                                        String symbolText = symbol.getText();
////                                        Logger.d("zhangbing", "symbolText = " + symbolText);
//                                        Point[] symbolCornerPoints = symbol.getCornerPoints();
//                                        for (int i = 0; i < symbolCornerPoints.length; i++) {
////                                            Logger.d("zhangbing", "symbolCornerPoints = " + symbolCornerPoints[i].toString());
//                                        }
//                                        Rect symbolFrame = symbol.getBoundingBox();
//                                    }
//                                }
//                            }
                    }
                    recognizer.close();
                })
                .addOnFailureListener(e -> {
                    // 处理错误
                    e.printStackTrace();
                    // 释放资源
                    recognizer.close();
                });
    }

}
