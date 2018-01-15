package com.dahuatech.android.location_lbs;

import android.content.Context;

import com.dahuatech.android.location_lbs.export.CoorType;
import com.dahuatech.android.location_lbs.export.LocMode;
import com.dahuatech.android.location_lbs.export.LocationCallBack;

/**
 * 功能说明：
 * 版权申明：浙江大华技术股份有限公司
 * 创建标记：Xiao_Yunlou 2017-11-23
 */
public interface ILocation {

    /**
     * 初始化定位服务，启动应用的时候调用，调用一次即可
     * @param context
     */
    void initLocation(Context context, CoorType coorType);

    /**
     * 反初始化定位服务，结束应用的时候调用
     */
    void unInitLocation();

    /**
     * 获取一次经纬度信息,无需注册，直接调用
     * @param callBack
     */
    void getLocationOnce(LocationCallBack callBack);

    /**
     * 判断GPS是否可用
     * @return
     */
    boolean isGpsEnabled();

    /**
     * 设置获取位置信息的模式
     * @param mode
     */
    void setLocationMode(LocMode mode);

    /**
     * 注册位置监听
     * @param callback
     * @param isOnceCallBack
     */
    void registerLocationListener(LocationCallBack callback, boolean isOnceCallBack);

    /**
     * 反注册位置监听
     * @param callback
     */
    void unRegisterLocationListener(LocationCallBack callback);

    /**
     * 停止监听
     */
    void stop();

    /**
     * 持续获取位置信息
     * @param scanTime
     */
    void startContinues(int scanTime);

}
