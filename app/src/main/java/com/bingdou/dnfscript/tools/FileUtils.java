package com.bingdou.dnfscript.tools;

import android.text.TextUtils;

import java.io.File;

public class FileUtils {
    private static final String TAG = "FileUtils";

    /**
     * 判断SDK是否存在
     *
     * @return
     */
    public static boolean existSDCard() {
        if (android.os.Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED)) {
            return true;
        } else
            return false;
    }

    /**
     * 删除文件夹下的子文件
     *
     * @param file
     */
    public static void deleteFile(File file) {
        //判断文件不为null或文件目录存在
        if (file == null || !file.exists()) {
            Logger.d(TAG, "文件删除失败,请检查文件路径是否正确");
            return;
        }
        //取得这个目录下的所有子文件对象
        File[] files = file.listFiles();
        //遍历该目录下的文件对象
        for (File f : files) {
            //打印文件名
            String name = file.getName();
            Logger.d(TAG, "删除 " + name + " 成功！");
            //判断子目录是否存在子目录,如果是文件则删除
            if (f.isDirectory()) {
                deleteFile(f);
            } else {
                f.delete();
            }
        }
    }

    public static void deleteFile(String apkFilePath) {
        if (TextUtils.isEmpty(apkFilePath)) {
            Logger.d(TAG, "文件不能为空");
            return;
        }
        File file = new File(apkFilePath);
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                Logger.d(TAG, "删除 " + apkFilePath + " 成功！");
            } else {
                Logger.d(TAG, "删除 " + apkFilePath + "  失败！");
            }
        } else {
            Logger.d(TAG, "删除 " + apkFilePath + "  不存在！");
        }
    }


}
