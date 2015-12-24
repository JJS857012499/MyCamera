package com.zengfull.mycamera.camera.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by asus on 2015/12/23.
 */
public class PhotoItem implements Parcelable {

    private String url;
    private long data;

    public PhotoItem(String url, long data) {
        this.url = url;
        this.data = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
        dest.writeLong(this.data);
    }

    public PhotoItem() {
    }

    protected PhotoItem(Parcel in) {
        this.url = in.readString();
        this.data = in.readLong();
    }

    public static final Creator<PhotoItem> CREATOR = new Creator<PhotoItem>() {
        public PhotoItem createFromParcel(Parcel source) {
            return new PhotoItem(source);
        }

        public PhotoItem[] newArray(int size) {
            return new PhotoItem[size];
        }
    };


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getData() {
        return data;
    }

    public void setData(long data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "PhotoItem{" +
                "url='" + url + '\'' +
                ", data=" + data +
                '}';
    }
}
