package com.dahuatech.android.location_lbs.export;

/**
 * 功能说明：
 * 版权申明：浙江大华技术股份有限公司
 * 创建标记：Xiao_Yunlou 2017-12-05
 */
public enum LocMode {

    /**
     * 省电模式（网络定位，包括wifi和基站）
     */
    Battery_Saving,
    /**
     * 设备模式（GPS定位，不支持室内定位）
     */
    Device_Sensors,
    /**
     * 高精度模式（网络定位+GPS定位，优先返回最高精度定位结果）
     */
    Hight_Accuracy

}
