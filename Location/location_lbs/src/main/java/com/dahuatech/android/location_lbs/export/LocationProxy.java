package com.dahuatech.android.location_lbs.export;

import android.content.Context;

import com.dahuatech.android.location_lbs.ILocation;
import com.dahuatech.android.location_lbs.LocationImpl;

/**
 * 功能说明：
 * 版权申明：浙江大华技术股份有限公司
 * 创建标记：Xiao_Yunlou 2017-11-23
 */
public class LocationProxy {

    private ILocation iLocation;

    public static LocationProxy get() {
        return Instance.instance;
    }

    private static class Instance {
        static LocationProxy instance = new LocationProxy();
    }

    private LocationProxy() {
        iLocation = LocationImpl.get();
    }

    /**
     * 初始化，调用一次即可
     * @param context
     * @param coorType
     */
    public void initLocation(Context context, CoorType coorType) {
        iLocation.initLocation(context, coorType);
    }

    /**
     * 反初始化
     */
    public void unInitLocation() {
        iLocation.unInitLocation();
    }

    /**
     * 持续获取位置信息
     * @param scanTime
     * @param callBack
     */
    public void startContinuesLoc(int scanTime, LocationCallBack callBack) {
        iLocation.startContinues(scanTime);
        iLocation.registerLocationListener(callBack, false);
    }

    /**
     * 停止持续获取位置信息
     * @param callBack
     */
    public void stopContinuesLoc(LocationCallBack callBack) {
        iLocation.stop();
        iLocation.unRegisterLocationListener(callBack);
    }

    /**
     * 只获取一次位置信息
     * @param callBack
     */
    public void startLocationOnce(LocationCallBack callBack) {
        iLocation.getLocationOnce(callBack);
    }

    /**
     * 设置定位模式
     * @param mode
     */
    public void setLocationMode(LocMode mode) {
        iLocation.setLocationMode(mode);
    }

    /**
     * 判断GPS是否可用
     * @return
     */
    public boolean isGpsEnabled() {
        return iLocation.isGpsEnabled();
    }

}
