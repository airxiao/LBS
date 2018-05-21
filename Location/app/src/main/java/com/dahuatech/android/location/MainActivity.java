package com.dahuatech.android.location;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dahuatech.android.location_lbs.export.CoorType;
import com.dahuatech.android.location_lbs.export.LocMode;
import com.dahuatech.android.location_lbs.export.LocationCallBack;
import com.dahuatech.android.location_lbs.export.LocationInfo;
import com.dahuatech.android.location_lbs.export.LocationProxy;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, LocationCallBack, RadioGroup.OnCheckedChangeListener {

    private RadioGroup radioGroup_locMode;
    private RadioButton radio_Battery_Saving;
    private RadioButton radio_Device_Sensors;
    private RadioButton radio_Hight_Accuracy;
    private Button btn_point;
    private TextView tv_content;
    private boolean isStartLocation = true;
    private LocMode defaultLocMode = LocMode.Hight_Accuracy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initContinueGDLBS();

    }

    private void initContinueGDLBS() {
        LocationProxy.get().initLocation(this, CoorType.GCJ02);
        LocationProxy.get().setLocationMode(defaultLocMode);
        LocationProxy.get().startContinuesLoc(5 * 1000, this);

        switch (defaultLocMode) {
            case Device_Sensors:
                radio_Device_Sensors.setChecked(true);
                break;

            case Battery_Saving:
                radio_Battery_Saving.setChecked(true);
                break;

            case Hight_Accuracy:
                radio_Hight_Accuracy.setChecked(true);
                break;
        }
    }

    private void initView() {
        radioGroup_locMode = (RadioGroup) findViewById(R.id.radioGroup_locMode);
        radio_Battery_Saving = (RadioButton) findViewById(R.id.radio_Battery_Saving);
        radio_Device_Sensors = (RadioButton) findViewById(R.id.radio_Device_Sensors);
        radio_Hight_Accuracy = (RadioButton) findViewById(R.id.radio_Hight_Accuracy);
        btn_point = (Button) findViewById(R.id.btn_point);
        tv_content = (TextView) findViewById(R.id.tv_content);

        btn_point.setOnClickListener(this);
        radioGroup_locMode.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        tv_content.setText("");
        switch (checkedId) {
            case R.id.radio_Battery_Saving:
                LocationProxy.get().setLocationMode(LocMode.Battery_Saving);
                break;
            case R.id.radio_Device_Sensors:
                LocationProxy.get().setLocationMode(LocMode.Device_Sensors);
                break;
            case R.id.radio_Hight_Accuracy:
                LocationProxy.get().setLocationMode(LocMode.Hight_Accuracy);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_point:
                if (isStartLocation) {
                    LocationProxy.get().stopContinuesLoc(this);
                    btn_point.setText("开始定位");
                    tv_content.setText("");
                } else {
                    LocationProxy.get().startContinuesLoc(5 * 1000, this);
                    btn_point.setText("暂停定位");
                }
                isStartLocation = !isStartLocation;
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocationProxy.get().stopContinuesLoc(this);
        LocationProxy.get().unInitLocation();
    }

    @Override
    public void locationResult(LocationInfo locationInfo) {
        if (locationInfo.getErrorCode() == LocationInfo.LOCATION_SUCCESS) {
            String content =
                    "longitude:" + locationInfo.getLongitude() + "\n" +
                            "latitude:" + locationInfo.getLatitude() + "\n" +
                            "accuracy:" + locationInfo.getAccuracy() + "\n" +
                            "country:" + locationInfo.getCountry() + "\n" +
                            "province:" + locationInfo.getProvince() + "\n" +
                            "city:" + locationInfo.getCity() + "\n" +
                            "cityCode:" + locationInfo.getCityCode() + "\n" +
                            "district:" + locationInfo.getDistrict() + "\n" +
                            "adCode:" + locationInfo.getAdCode() + "\n" +
                            "address:" + locationInfo.getAddress() + "\n" +
                            "errorCode:" + locationInfo.getErrorCode() + "\n" +
                            "errorInfo:" + locationInfo.getErrorInfo() + "\n" +
                            "locationDetail:" + locationInfo.getLocationDetail() + "\n" +
                            "isWifiAble:" + locationInfo.isWifiAble() + "\n" +
                            "gpsStatus:" + locationInfo.getGpsStatus() + "\n" +
                            "gpsSatellites:" + locationInfo.getGpsSatellites() + "\n" +
                            "provider:" + locationInfo.getProvider() + "\n" +
                            "speed:" + locationInfo.getSpeed() + "\n" +
                            "bearing:" + locationInfo.getBearing() + "\n" +
                            "poiName:" + locationInfo.getPoiName() + "\n" +
                            "time:" + locationInfo.getTime();
            tv_content.setText(content);
            Toast.makeText(this, "刷新", Toast.LENGTH_SHORT).show();
        }
    }

}
