package com.dahuatech.android.location_lbs;

import android.content.Context;
import android.location.LocationManager;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.dahuatech.android.location_lbs.export.CoorType;
import com.dahuatech.android.location_lbs.export.LocMode;
import com.dahuatech.android.location_lbs.export.LocationCallBack;
import com.dahuatech.android.location_lbs.export.LocationInfo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 功能说明：
 * 版权申明：浙江大华技术股份有限公司
 * 创建标记：Xiao_Yunlou 2017-11-25
 */
public class LocationImpl implements ILocation {

    private static final int LBS_INTERVAL_TIME = 2000;
    private static final int LBS_OVER_TIME = 30000;

    private CoorType mCoorType;
    private boolean isContinuesLoc = false;
    private Map<LocationCallBack, LocationCallBackObject> locCallBackMap;
    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;
    private LocationManager mLocationManager;

    public static ILocation get() {
        return Instance.instace;
    }

    private static class Instance {
        static LocationImpl instace = new LocationImpl();
    }

    private LocationImpl() {

    }

    @Override
    public void initLocation(Context context, CoorType coorType) {
        mCoorType = coorType;
        mLocationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        locCallBackMap = new HashMap<>();
        //初始化client
        locationClient = new AMapLocationClient(context);
        locationOption = getDefaultOption();
        //设置定位参数
        locationClient.setLocationOption(locationOption);
        // 设置定位监听
        locationClient.setLocationListener(locationListener);
    }

    @Override
    public void unInitLocation() {
        locationClient.setLocationListener(null);
        locCallBackMap = null;
        if (null != locationClient) {
            locationClient.onDestroy();
            locationClient = null;
            locationOption = null;
        }
    }

    @Override
    public void getLocationOnce(LocationCallBack callBack) {
        if(!isContinuesLoc){
            locationClient.startLocation();
        }

        registerLocationListener(callBack, true);
    }

    @Override
    public boolean isGpsEnabled() {
        return mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    @Override
    public void setLocationMode(LocMode mode) {
        if (mode == LocMode.Hight_Accuracy) {
            locationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        } else if (mode == LocMode.Device_Sensors) { //当打开GPS后，
            locationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Device_Sensors);
        } else if (mode == LocMode.Battery_Saving) {
            locationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
        }

        locationClient.setLocationOption(locationOption);
    }

    @Override
    public void registerLocationListener(LocationCallBack callback, boolean isOnceCallBack) {
        if (!locCallBackMap.containsKey(callback)) {
            LocationCallBackObject mLocationCallBackObject = new LocationCallBackObject(callback);
            mLocationCallBackObject.setOnceCallBack(isOnceCallBack);
            locCallBackMap.put(callback, mLocationCallBackObject);
        }
    }

    @Override
    public void unRegisterLocationListener(LocationCallBack callback) {
        if (locCallBackMap.containsKey(callback)) {
            locCallBackMap.remove(callback);
        }
    }

    @Override
    public void stop() {
        if(null != locationClient && locationClient.isStarted()){
            // 停止定位
            locationClient.stopLocation();
            isContinuesLoc = false;
        }
    }

    @Override
    public void startContinues(int scanTime) {
        if(!isContinuesLoc){
            locationOption.setInterval(Long.valueOf(scanTime));
            locationClient.setLocationOption(locationOption);
            // 启动定位
            locationClient.startLocation();
            isContinuesLoc = true;
        }
    }

    private AMapLocationClientOption getDefaultOption(){
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setGpsFirst(false);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setHttpTimeOut(LBS_OVER_TIME);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setInterval(LBS_INTERVAL_TIME);//可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        mOption.setOnceLocation(false);//可选，设置是否单次定位。默认是false
        mOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        mOption.setSensorEnable(false);//可选，设置是否开启设备传感器，当设置为true时，网络定位可以返回海拔、角度和速度。
        mOption.setWifiScan(true); //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        mOption.setLocationCacheEnable(true); //可选，设置是否使用缓存定位，默认为true
        return mOption;
    }

    AMapLocationListener locationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation location) {
            if (null == location) {
                return;
            }

            LocationInfo locationInfo = new LocationInfo(location);
            if (mCoorType == CoorType.GCJ02) { //国家(高德默认使用火星坐标系，无需转化)

            } else if (mCoorType == CoorType.WGS84) { //国际
                LatLng latLng = new LatLng(locationInfo.getLatitude(), locationInfo.getLongitude());
                latLng = LocationConverter.gcj02ToWgs84(latLng);
                locationInfo.setLatitude(latLng.latitude);
                locationInfo.setLongitude(latLng.longitude);
            } else if (mCoorType == CoorType.BD09) { //百度
              LatLng latLng = new LatLng(locationInfo.getLatitude(), locationInfo.getLongitude());
                latLng = LocationConverter.gcj02ToBd09(latLng);
                locationInfo.setLatitude(latLng.latitude);
                locationInfo.setLongitude(latLng.longitude);
            }

            if(!locCallBackMap.isEmpty()) {
                Iterator<Map.Entry<LocationCallBack, LocationCallBackObject>> it = locCallBackMap.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<LocationCallBack, LocationCallBackObject> entry = it.next();
                    entry.getKey().locationResult(locationInfo);
                    if (entry.getValue().isOnceCallBack()) {
                        if(!isContinuesLoc){
                            stop();
                        }

                        it.remove();
                    }
                }
            }
        }
    };

}
