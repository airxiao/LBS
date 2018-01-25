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

    private Button btn_point;
    private boolean isStartLocation = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initOtherView();
        initContinueGDLBS();
    }

    private void initOnceGDLBS() {
        LocationProxy.get().initLocation(this, CoorType.GCJ02);
        LocationProxy.get().startLocationOnce(this);
    }

    private void initContinueGDLBS() {
        LocationProxy.get().initLocation(this, CoorType.GCJ02);
        LocationProxy.get().setLocationMode(LocMode.Device_Sensors);
        LocationProxy.get().startContinuesLoc(5 * 1000, this);
    }

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
    protected void onDestroy() {
        super.onDestroy();
        LocationProxy.get().stopContinuesLoc(this);
        LocationProxy.get().unInitLocation();
    }

    @Override
    public void locationResult(LocationInfo locationInfo) {
		if (locationInfo.getErrorCode() == LocationInfo.LOCATION_SUCCESS) {
           // ...
        }
    }

}
