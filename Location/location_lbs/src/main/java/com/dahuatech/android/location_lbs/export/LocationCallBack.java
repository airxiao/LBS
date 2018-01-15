package com.dahuatech.android.location_lbs.export;

/**
 * 功能说明：
 * 版权申明：浙江大华技术股份有限公司
 * 创建标记：Xiao_Yunlou 2017-12-05
 */

public interface LocationCallBack {

    /**
     * 位置回调结果
     * @param locationInfo
     */
    void locationResult(LocationInfo locationInfo);
}
