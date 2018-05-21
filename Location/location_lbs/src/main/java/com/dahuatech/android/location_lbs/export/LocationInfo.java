package com.dahuatech.android.location_lbs.export;

import com.amap.api.location.AMapLocation;

/**
 * 功能说明：
 * 版权申明：浙江大华技术股份有限公司
 * 创建标记：Xiao_Yunlou 2017-12-05
 */
public class LocationInfo {
    /******************************************* GPS错误码 *******************************************************/
    /**
     * 定位成功
     */
    public static final int LOCATION_SUCCESS = 0;
    /**
     *错误码：参数错误
     */
    public static final int ERROR_CODE_INVALID_PARAMETER = 1;
    /**
     *定位错误码：定位失败，由于设备仅扫描到单个wifi，不能精准的计算出位置信息
     */
    public static final int ERROR_CODE_FAILURE_WIFI_INFO = 2;
    /**
     *定位错误码：获取到的请求参数为空，可能获取过程中出现异常
     */
    public static final int ERROR_CODE_FAILURE_LOCATION_PARAMETER = 3;
    /**
     * 定位错误码：网络连接异常
     */
    public static final int ERROR_CODE_FAILURE_CONNECTION = 4;
    /**
     * 定位错误码：解析XML出错
     */
    public static final int ERROR_CODE_FAILURE_PARSER = 5;
    /**
     * 定位错误码：定位结果错误
     */
    public static final int ERROR_CODE_FAILURE_LOCATION = 6;
    /**
     * 定位错误码：KEY错误
     */
    public static final int ERROR_CODE_FAILURE_AUTH = 7;
    /**
     * 定位错误码：其他错误
     */
    public static final int ERROR_CODE_UNKNOWN = 8;
    /**
     * 定位错误码：初始化异常
     */
    public static final int ERROR_CODE_FAILURE_INIT = 9;
    /**
     * 定位错误码：定位服务启动失败，请检查是否配置service并且manifest中service标签是否配置在application标签内
     */
    public static final int ERROR_CODE_SERVICE_FAIL = 10;
    /**
     * 定位错误码：错误的基站信息，请检查是否安装SIM卡
     */
    public static final int ERROR_CODE_FAILURE_CELL = 11;
    /**
     * 定位错误码：缺少定位权限,请检查是否配置定位权限,并在安全软件和设置中给应用打开定位权限
     */
    public static final int ERROR_CODE_FAILURE_LOCATION_PERMISSION = 12;
    /**
     * 定位错误码：网络定位失败，请检查设备是否插入sim卡、开启移动网络或开启了wifi模块
     */
    public static final int ERROR_CODE_FAILURE_NOWIFIANDAP = 13;
    /**
     * 定位错误码：GPS定位失败，可用卫星数不足
     */
    public static final int ERROR_CODE_FAILURE_NOENOUGHSATELLITES = 14;
    /**
     * 定位错误码：定位位置可能被模拟
     */
    public static final int ERROR_CODE_FAILURE_SIMULATION_LOCATION = 15;
    /**
     * 定位错误码：定位失败，飞行模式下关闭了WIFI开关，请关闭飞行模式或者打开WIFI开关
     */
    public static final int ERROR_CODE_AIRPLANEMODE_WIFIOFF = 18;
    /**
     * 定位错误码：定位失败，没有检查到SIM卡，并且关闭了WIFI开关，请打开WIFI开关或者插入SIM卡
     */
    public static final int ERROR_CODE_NOCGI_WIFIOFF = 19;
    /******************************************* GPS错误码 *******************************************************/
    /**
     * 错误码
     */
    private int errorCode;
    /**
     * 错误信息
     */
    private String errorInfo;
    /**
     * 错误描述
     */
    private String locationDetail;
    /**
     * 经    度
     */
    private double longitude;
    /**
     * 纬    度
     */
    private double latitude;
    /**
     * 精    度
     */
    private float accuracy;
    /**
     * 国    家
     */
    private String country;
    /**
     * 省
     */
    private String province;
    /**
     * 市
     */
    private String city;
    /**
     * 城市编码
     */
    private String cityCode;
    /**
     * 区
     */
    private String district;
    /**
     * 区域码
     */
    private String adCode;
    /**
     * 详细地址
     */
    private String address;

    /**
     *提供者
     */
    private String provider;

    /**
     * 速度(米/秒)
     */
    private float speed;

    /**
     * 角度
     */
    private float bearing;

    /**
     * 兴趣点
     */
    private String poiName;

    /**
     * 定位时间
     * (in milliseconds since January 1, 1970)
     */
    private long time;

    /******************************************* GPS状态码 *******************************************************/
    /**
     * GPS定位状态--正常
     */
    public static final int GPS_STATUS_OK = 0;
    /**
     * GPS定位状态--手机中没有GPS Provider，无法进行GPS定位
     */
    public static final int GPS_STATUS_NOGPSPROVIDER = 1;
    /**
     * GPS定位状态--GPS关闭
     * 建议开启GPS，提高定位质量
     * Android 4.4以下的手机是gps开关关闭-建议开启gps开关
     * Android 4.4以上的手机设置中关闭了定位（位置）服务-建议开启定位服务，并选择包含gps的定位模式
     */
    public static final int GPS_STATUS_OFF = 2;
    /**
     * GPS定位状态--选择的定位模式中不包含GPS定位
     * Android 4.4以上的手机设置中开启了定位（位置）服务，但是选择的模式为省电模式，不包含GPS定位
     * 建议选择包含gps定位的模式（例如：高精度、仅设备）
     */
    public static final int GPS_STATUS_MODE_SAVING = 3;
    /**
     * GPS定位状态--没有GPS定位权限
     * 如果没有GPS定位权限无法进行GPS定位, 建议在安全软件中授予GPS定位权限
     */
    public static final int GPS_STATUS_NOGPSPERMISSION = 4;
    /******************************************* GPS状态码 *******************************************************/
    /**
     * GPS状态码
     */
    private int gpsStatus;
    /**
     * WIFI开关
     */
    private boolean isWifiAble;
    /**
     * GPS星数
     */
    private int gpsSatellites;

    public LocationInfo(AMapLocation location) {
        longitude = location.getLongitude();
        latitude = location.getLatitude();
        accuracy = location.getAccuracy();
        country = location.getCountry();
        province = location.getProvince();
        city = location.getCity();
        cityCode = location.getCityCode();
        district = location.getDistrict();
        adCode = location.getAdCode();
        address = location.getAddress();
        errorCode = location.getErrorCode();
        errorInfo = location.getErrorInfo();
        locationDetail = location.getLocationDetail();
        isWifiAble = location.getLocationQualityReport().isWifiAble();
        gpsStatus = location.getLocationQualityReport().getGPSStatus();
        gpsSatellites = location.getLocationQualityReport().getGPSSatellites();
        provider = location.getProvider();
        speed = location.getSpeed();
        bearing = location.getBearing();
        poiName = location.getPoiName();
        time = location.getTime();
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public float getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(float accuracy) {
        this.accuracy = accuracy;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAdCode() {
        return adCode;
    }

    public void setAdCode(String adCode) {
        this.adCode = adCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public String getLocationDetail() {
        return locationDetail;
    }

    public void setLocationDetail(String locationDetail) {
        this.locationDetail = locationDetail;
    }

    public boolean isWifiAble() {
        return isWifiAble;
    }

    public void setWifiAble(boolean wifiAble) {
        isWifiAble = wifiAble;
    }

    public int getGpsStatus() {
        return gpsStatus;
    }

    public void setGpsStatus(int gpsStatus) {
        this.gpsStatus = gpsStatus;
    }

    public int getGpsSatellites() {
        return gpsSatellites;
    }

    public void setGpsSatellites(int gpsSatellites) {
        this.gpsSatellites = gpsSatellites;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getBearing() {
        return bearing;
    }

    public void setBearing(float bearing) {
        this.bearing = bearing;
    }

    public String getPoiName() {
        return poiName;
    }

    public void setPoiName(String poiName) {
        this.poiName = poiName;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
