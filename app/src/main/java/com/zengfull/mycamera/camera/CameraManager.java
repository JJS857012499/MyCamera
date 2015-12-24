package com.zengfull.mycamera.camera;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.zengfull.mycamera.MainActivity;
import com.zengfull.mycamera.camera.model.PhotoItem;
import com.zengfull.mycamera.camera.ui.CameraActivity;


/**
 * Created by asus on 2015/12/23.
 */
public class CameraManager {

    private static CameraManager mInstance;

    private CameraManager(){}

    public static CameraManager getInstance(){
        if(mInstance == null){
            synchronized(CameraManager.class){
                if(mInstance == null){
                    mInstance = new CameraManager();
                }
            }
        }
        return mInstance;
    }

    //打开照相界面
    public void openCamera(Context context) {
        Intent intent = new Intent(context, CameraActivity.class);
        context.startActivity(intent);
    }




    public void processPhotoItem(Activity activity, PhotoItem photoItem) {
        Uri uri = Uri.parse("file://" + photoItem.getUrl());

        //TODO 拍照后自己处理
        Intent intent = new Intent(activity, MainActivity.class);
        intent.setData(uri);
        activity.startActivity(intent);


    }
}
