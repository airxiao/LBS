package com.dahuatech.android.location_lbs;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by 27166 on 2017/8/3.
 */
public class LatLng {
    /**大地、地球坐标系（国际标准坐标系，各种硬件上报的都是此坐标系）*/
    public final static int GPS_WGS84 = 1;
    /**百度坐标系*/
    public final static int GPS_BD09 = 2;
    /**火星坐标系*/
    public final static int GPS_GCJ02 = 3;
    /**默认不传坐标系类型，则不进行转换*/
    public final static int GPS_NULL = 4;
    public final double latitude;
    public final double longitude;
    private @GPSType int gpsType;

    @IntDef({GPS_WGS84, GPS_BD09, GPS_GCJ02, GPS_NULL})
    @Retention(RetentionPolicy.SOURCE)
    public @interface GPSType{}

//    public LatLng() {
//        this.latitude = 0;
//        this.longitude = 0;
//        this.gpsType = MapLBSConfig.getInstance().getGPSType();
//    }

    public LatLng(@GPSType int gpsType) {
        this.latitude = 0;
        this.longitude = 0;
        this.gpsType = gpsType;
    }

    public LatLng(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.gpsType = GPS_NULL;
    }

    public LatLng(double latitude, double longitude, @GPSType int gpsType) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.gpsType = gpsType;
    }

    public @GPSType int getGpsType() {
        return gpsType;
    }

    public void setGpsType(@GPSType int gpsType) {
        this.gpsType = gpsType;
    }
}
