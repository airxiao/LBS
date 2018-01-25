# Android 免 key 定位组件

之前在项目中遇到了定位需求，便在高德地图定位 SDK 的基础上封装了一层运用在项目中。后来部门提出了组件化的思想，因此又整理完善了，于是便有了这套[定位组件](https://github.com/airxiao/LBS)。

### 特点

1. 免key使用。无需去官网申请key，直接依赖后即可使用
2. 支持多种坐标系。只需设置好坐标系，就可以返回该坐标系下对应的经纬度
3. 使用简便。支持一次性定位，持续定位，无需考虑之间切换逻辑
4. 定位信息全面，包含了经纬度、街道信息、错误码、以及当前的GPS状态等详细信息

### 说明

这套组件是在高德地图基础上进行封装的，其最大的特点就是免申请 key 便可使用。之所以有这样的需求是因为我司的 Android 开发划分为好几个组，每个组支撑不同的行业线，因此组内资源要做到尽可能的共享，提高开发效率。因此，这也是该组件要想达到共享必须做到免 key 申请的原因（github地址：https://github.com/airxiao/LBS）。

关于如何破解高德地图定位 SDK 免 key 使用我不再叙述，网上也可以搜到相关资料也有，我也是参考了各路大神的方案才破解成功的，顺便也感谢下各位大神。

**参考资料：**

> [Android 破解高德地图 sdk 使用 map 免 key](https://juejin.im/entry/5805dc4f570c35006b7af21d)
>
> [破解高德SDK实现免key](http://caiyao.name/2016/09/10/%E7%A0%B4%E8%A7%A3%E9%AB%98%E5%BE%B7SDK%E5%AE%9E%E7%8E%B0%E5%85%8Dkey/)

### 使用说明

使用该组件只需要在应用中依赖 Demo 中的 location_lbs 模块即可 ，之所以说该组件使用简便是因为对于开发者来说，该组件总共只有 7 个接口对外提供。

1. 定位初始化：app内调用一次即可
   @param  context
   @param  coorType要设置坐标系，包括火星坐标系，大地坐标系，百度坐标系（详情见文档）
   initLocation(Context context, CoorType coorType)
2. 初始化
   unInitLocation()
3. 持续获取位置信息
   @param scanTime  设置定位间隔时间
   @param callback  定位信息回调
   startContinuesLoc(int scanTime, LocationCallBack callBack)
4. 停止持续获取位置信息
   @param callback 定位信息回调（与startContinuesLoc中的回调相同实例）
   stopContinuesLoc(LocationCallBack callBack)
5. 只获取一次位置信息
   @param callback定位信息回调
   startLocationOnce(LocationCallBack callBack)
6. 设置定位模式
   @param mode 有省电模式、设备模式、高精度模式可供选择（详情见文档）
   setLocationMode(LocMode mode)
7. 判断GPS是否可用
   isGpsEnabled()

其中，在 LocationCallBack 的回调接口中不要忘记对错误码进行判断，如下：

```java
  	@Override
    public void locationResult(LocationInfo locationInfo) {
        if (locationInfo.getErrorCode() == LocationInfo.LOCATION_SUCCESS) {
           
        }
	}
```

### 其他

[更多使用详情大家可以参考demo 和文档说明，地址：https://github.com/airxiao/LBS](https://github.com/airxiao/LBS)。

如果你认为该组件对你有所帮助的，麻烦您帮忙点个 star，小弟在此谢过了。