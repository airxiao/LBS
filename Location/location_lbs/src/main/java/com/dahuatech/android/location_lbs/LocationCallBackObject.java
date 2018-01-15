package com.dahuatech.android.location_lbs;

import com.dahuatech.android.location_lbs.export.LocationCallBack;

/**
 * 功能说明：
 * 版权申明：浙江大华技术股份有限公司
 * 创建标记：Xiao_Yunlou 2017-12-05
 */
public class LocationCallBackObject {

    private LocationCallBack mLocationCallBack;
    private boolean isOnceCallBack = false;

    public LocationCallBackObject(LocationCallBack mLocationCallBack) {
        this.mLocationCallBack = mLocationCallBack;
    }

    public boolean isOnceCallBack() {
        return isOnceCallBack;
    }

    public void setOnceCallBack(boolean onceCallBack) {
        isOnceCallBack = onceCallBack;
    }
}
