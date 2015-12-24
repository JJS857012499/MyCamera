/*
 * Copyright (C) 2014 pengjianbo(pengjianbosoft@gmail.com), Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.zengfull.mycamera.camera2;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.DisplayMetrics;
import android.widget.Toast;

import com.zengfull.mycamera.camera.model.PhotoItem;

import java.io.File;
import java.util.Date;

public abstract class PhotoBaseActivity extends Activity {

    static final int TAKE_REQUEST_CODE = 1001;
    //protected static Map<String, PhotoInfo> mSelectPhotoMap = new HashMap<>();
    protected static String mPhotoTargetFolder;

    private Uri mTakePhotoUri;

    protected int mScreenWidth = 720;
    protected int mScreenHeight = 1280;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        mScreenWidth = dm.widthPixels;
        mScreenHeight = dm.heightPixels;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        System.gc();
        super.onDestroy();
    }

    public void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 拍照
     */
    protected void takePhotoAction() {

        if (!existSDCard()) {
            toast("没有SD卡不能拍照呢~");
            return;
        }

        File takePhotoFolder = null;
        if (TextUtils.isEmpty(mPhotoTargetFolder)) {
            takePhotoFolder = new File(Environment.getExternalStorageDirectory(), "/DCIM/Camera");
        } else {
            takePhotoFolder = new File(mPhotoTargetFolder);
        }

        File toFile = new File(takePhotoFolder, "IMG" + DateFormat.format("yyyyMMddHHmmss",new Date()) + ".jpg");
        if(!takePhotoFolder.exists()){
            takePhotoFolder.mkdirs();
        }
//        if (suc) {
            mTakePhotoUri = Uri.fromFile(toFile);
            Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mTakePhotoUri);
            startActivityForResult(captureIntent, TAKE_REQUEST_CODE);
//        } else {
//            Logger.e("create file failure");
//        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ( requestCode == TAKE_REQUEST_CODE ) {
            if (resultCode == RESULT_OK && mTakePhotoUri != null) {
                final String path = mTakePhotoUri.getPath();
                final PhotoItem photoItem = new PhotoItem(path, System.currentTimeMillis());
                updateGallery(path);
                takeResult(photoItem);
            } else {
                toast("拍照失败");
            }
        }
    }



    /**
     * 更新相册
     */
    private void updateGallery(String filePath) {
//        mMediaScanner.scanFile(filePath, "image/jpeg");
    }


    protected abstract void takeResult(PhotoItem info);


    /**
     * 判断SDCard是否可用
     */
    public static boolean existSDCard() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }
}
