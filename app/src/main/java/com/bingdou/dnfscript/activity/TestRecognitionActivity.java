package com.bingdou.dnfscript.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bingdou.dnfscript.R;
import com.bingdou.dnfscript.service.FightHelper;
import com.bingdou.dnfscript.service.MyAccessibilityService;
import com.bingdou.dnfscript.tools.AccessibilityHelper;
import com.bingdou.dnfscript.tools.GestureHelper;
import com.bingdou.dnfscript.tools.HandleControl;
import com.bingdou.dnfscript.tools.Logger;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.TextRecognizer;
import com.google.mlkit.vision.text.chinese.ChineseTextRecognizerOptions;

public class TestRecognitionActivity extends BaseActivity {

    private static final int REQUEST_CAMERA_PERMISSION = 1;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private TextView tv_result;
    private ImageView image;
    private int mCenterX, mCenterY;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recognition);
        tv_result = findViewById(R.id.tv_result);
        image = findViewById(R.id.image);
        //权限检查
        requestCameraPermission();
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(view -> {
//            dispatchTakePictureIntent();//调本地相机
//                Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img_1);
//                // 在这里使用imageBitmap进行文字识别
//                recognizeTextFromBitmap(imageBitmap);
//            screenCaptureNoStatusBar();
            FightHelper.getInstance().judgeIsFight(this);
//            boolean b = GestureHelper.getInstance().clickByGesture(MyAccessibilityService.mService, mCenterX, mCenterY);
//            Logger.d("zhangbing", "GestureHelper b = " + b);
        });

//        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button btn1 = findViewById(R.id.btn1);
//        btn1.setOnClickListener(view -> {
////            boolean b = GestureHelper.getInstance().clickByGesture(MyAccessibilityService.mService, 20, 180);
////            Logger.d("zhangbing", "GestureHelper b = " + b);
//        });

        tv_result.setOnClickListener(view -> {
            float x = view.getX();
            float y = view.getY();
            Logger.d("zhangbing111", "x = " + x + "; y =" + y);
            Toast.makeText(TestRecognitionActivity.this, "haha2131", Toast.LENGTH_SHORT).show();
        });
    }

    private void screenCaptureNoStatusBar() {
        View view = getWindow().getDecorView();
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
//        recognizeTextFromBitmap(imageBitmap);
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    private void requestCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    REQUEST_CAMERA_PERMISSION);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            image.setImageBitmap(imageBitmap);
            // 在这里使用imageBitmap进行文字识别
            recognizeTextFromBitmap(imageBitmap);
        }
    }

    private void recognizeTextFromBitmap(Bitmap bitmap) {
        InputImage image = InputImage.fromBitmap(bitmap, 0);
        ChineseTextRecognizerOptions build = new ChineseTextRecognizerOptions.Builder().build();
        TextRecognizer recognizer = TextRecognition.getClient(build);
        recognizer.process(image)
                .addOnSuccessListener(new OnSuccessListener<Text>() {
                    @Override
                    public void onSuccess(com.google.mlkit.vision.text.Text result) {
                        String resultText = result.getText();
                        tv_result.setText(resultText);
                        for (Text.TextBlock block : result.getTextBlocks()) {
                            String blockText = block.getText();
                            Logger.d("zhangbing", "blockText = " + blockText);
                            Point[] blockCornerPoints = block.getCornerPoints();
//                            Logger.d("zhangbing", "blockCornerPoints = " + blockCornerPoints.toString());
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
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // 处理错误
                        e.printStackTrace();
                        // 释放资源
                        recognizer.close();
                    }
                });
    }
}

