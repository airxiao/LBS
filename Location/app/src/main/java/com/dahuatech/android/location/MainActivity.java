package com.dahuatech.android.location;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import com.dahuatech.android.location_lbs.export.CoorType;
import com.dahuatech.android.location_lbs.export.LocMode;
import com.dahuatech.android.location_lbs.export.LocationCallBack;
import com.dahuatech.android.location_lbs.export.LocationInfo;
import com.dahuatech.android.location_lbs.export.LocationProxy;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, LocationCallBack {

//    private MapView mMapview;
    private Button btn_point;
    private boolean isStartLocation = true;
//    private AMap aMap;
//    private CameraUpdate mCameraUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initOtherView();

//        initAMap(savedInstanceState);

//        initOnceGDLBS();

        initContinueGDLBS();

    }

//    private void initAMap(Bundle savedInstanceState) {
//        mMapview = (MapView) findViewById(R.id.amap);
//        mMapview.onCreate(savedInstanceState);
//
//        if (aMap == null) {
//            aMap = mMapview.getMap();
//        }
//    }

    private void initOnceGDLBS() {
        LocationProxy.get().initLocation(this, CoorType.GCJ02);
        LocationProxy.get().startLocationOnce(this);
    }

    private void initContinueGDLBS() {
        LocationProxy.get().initLocation(this, CoorType.GCJ02);
        LocationProxy.get().setLocationMode(LocMode.Device_Sensors);
        LocationProxy.get().startContinuesLoc(5 * 1000, this);
    }

//    private void showMarker(LatLng latLng) {
//        mCameraUpdate = CameraUpdateFactory.newCameraPosition(new CameraPosition(latLng, 18, 0, 0));
//        aMap.moveCamera(mCameraUpdate);
////        aMap.addMarker(new MarkerOptions().position(latLng));
//    }

    private void initOtherView() {
        btn_point = (Button) findViewById(R.id.btn_point);
        btn_point.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_point:
                if (isStartLocation) {
                    LocationProxy.get().stopContinuesLoc(this);
                    btn_point.setText("开始定位");
                } else {
                    LocationProxy.get().startContinuesLoc(5 * 1000, this);
                    btn_point.setText("暂停定位");
                }
                isStartLocation = !isStartLocation;
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
//        mMapview.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        mMapview.onResume();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
//        mMapview.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocationProxy.get().stopContinuesLoc(this);
        LocationProxy.get().unInitLocation();
//        mMapview.onDestroy();
    }

    @Override
    public void locationResult(LocationInfo locationInfo) {

    }

//    @Override
//    public void getLocationSuccess(AMapLocation location, GDLocationHelper.LocationQuality locationquality) {
//        Log.d("xyl-高德", location.getAddress() + "**" + location.getLongitude() + "**" + location.getLatitude());
//
//        latLng = new LatLng(location.getLatitude(), location.getLongitude());
//        latLngList.add(latLng);
//        showMarker(latLng);
//        aMap.addPolyline(new PolylineOptions().addAll(latLngList).width(1).color(Color.BLACK));
//        String msg = location.getLongitude() + " ** " + location.getLatitude() + " ** " + locationquality.getGpsStatus();
//        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
//        btn_point.setText(msg);
//    }

//    @Override
//    public void getLocationFailed(AMapLocation location, GDLocationHelper.LocationQuality locationquality) {
//        String msg = "错误码:" + location.getErrorCode() + ",错误信息:" +
//                location.getErrorInfo() + ",错误描述:" + location.getLocationDetail() + " ** " + locationquality.getGpsStatus();
//        Log.d("xyl", msg);
//        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
//        btn_point.setText(msg);
//    }

//    @Override
//    public void onNewLocation(BDLocation location) {
//        Log.d("xyl-百度", location.getAddrStr() + "**" + location.getLongitude() + "**" + location.getLatitude()
//         + " ** " + location.getLocType());
//        CoordinateConverter converter = new CoordinateConverter(MainActivity.this);
//        converter.from(CoordinateConverter.CoordType.BAIDU);
//
//        DPoint sourceLatlng = new DPoint(location.getLatitude(), location.getLongitude());
//        DPoint desLatlng = null;
//        try {
//            converter.coord(sourceLatlng);
//            desLatlng = converter.convert();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        Log.d("xyl-百度TO高德", "**" + desLatlng.getLongitude() + "**" + desLatlng.getLatitude());
//    }

//    @Override
//    public void errorLocationMsg(String msg) {
//        Log.d("xyl", msg);
//        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
//    }
}
