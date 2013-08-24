package my.com.homesmartvertablet.activity;

import java.sql.SQLException;
import java.util.TooManyListenersException;

import my.com.homesmartvertablet.controller.DeviceItemController;
import my.com.homesmartvertablet.model.DeviceItem;

import com.example.homesmartvertablet.activity.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class ModifyDeviceItemActivity extends Activity implements OnClickListener,OnCheckedChangeListener {
	private RadioButton radio_lamp;
	private RadioButton radio_fan;
	private RadioButton radio_camera;
	private RadioButton radio_garage;
	private RadioGroup radioGroup;
	private Button btnSave,btnCancel;
	private int deviceID,devicePort,deviceStatus,deviceType,roomID;
	private String deviceName;
	private ImageButton toggleSwitch;
	private EditText edtNameDevice,edtPortDevice;
	private LinearLayout ln;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_modify_device_item);
		
		ln = (LinearLayout)findViewById(R.id.ln_status_device);
		//ln.setVisibility(View.GONE);
		radio_lamp = (RadioButton)findViewById(R.id.modify_checkbox_lamp);
		radio_fan = (RadioButton)findViewById(R.id.modify_checkbox_fan);
		radio_camera = (RadioButton)findViewById(R.id.modify_checkbox_camera);
		radio_garage = (RadioButton)findViewById(R.id.modify_checkbox_garage);
		radioGroup = (RadioGroup)findViewById(R.id.radio_group);
		btnSave = (Button)findViewById(R.id.modify_btn_save);
		btnCancel = (Button)findViewById(R.id.modify_btn_cancel);
		toggleSwitch = (ImageButton)findViewById(R.id.modify_toggle_status);
		edtNameDevice = (EditText)findViewById(R.id.modify_edt_name_device);
		edtPortDevice = (EditText)findViewById(R.id.modify_edt_port_device);
		
		
		deviceID = getIntent().getExtras().getInt("device_id");
		roomID = getIntent().getExtras().getInt("room_id");
		devicePort = getIntent().getExtras().getInt("device_port");
		deviceStatus = getIntent().getExtras().getInt("device_status");
		deviceType = getIntent().getExtras().getInt("device_type");
		deviceName = getIntent().getExtras().getString("device_name");
		
		btnSave.setOnClickListener(this);
		btnCancel.setOnClickListener(this);
		//toggleSwitch.setVisibility(View.GONE);
		toggleSwitch.setOnClickListener(this);
		radioGroup.setOnCheckedChangeListener(this);
		
		//set value of radio button;
		switch(deviceType){
			case 0:{
				radio_lamp.setChecked(true);
				break;
			}
			case 1:{
				radio_fan.setChecked(true);
				break;
			}
			case 2:{
				radio_garage.setChecked(true);
				break;
			}
			case 3:{
				radio_camera.setChecked(true);
				break;
			}
		
		}
		//set value of edit text name
		edtNameDevice.setText(deviceName);
		//set value of edit text port
		edtPortDevice.setText(devicePort +"");
		//set value of deivceStatus
		if(deviceStatus == 1){
			toggleSwitch.setImageResource(R.drawable.switch_on);
		}else{
			toggleSwitch.setImageResource(R.drawable.switch_off);
		}
	
	}
	
	private void deSelectCheck(){
		radioGroup.clearCheck();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
			case R.id.modify_btn_save:{
				DeviceItem item = new DeviceItem(roomID, edtNameDevice.getText().toString().trim(),deviceType, Integer.parseInt(edtPortDevice.getText().toString().trim()), deviceStatus);
				try {
					DeviceItemController.getInstance(ModifyDeviceItemActivity.this).updateDeviceItemByID(item, deviceID);
					Log.w("UPDATE EDIT","DONE");
					
					this.finish();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
			case R.id.modify_btn_cancel:{
				this.finish();
				break;
			}
			case R.id.modify_toggle_status:{
				deviceStatus = deviceStatus ^ 1;
				if(deviceStatus == 0){
					toggleSwitch.setImageResource(R.drawable.switch_off);
				}else{
					toggleSwitch.setImageResource(R.drawable.switch_on);
				}
				break;
			}
		}
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub
		Intent it = new Intent();
		it.putExtra("ROOM_ID", roomID);
		it.putExtra("NAME_DEVICE",  edtNameDevice.getText().toString().trim());
		it.putExtra("TYPE_DEVICE", deviceType);
		it.putExtra("PORT_DEVICE", Integer.parseInt(edtPortDevice.getText().toString().trim()));
		it.putExtra("STATUS_DEVICE", deviceStatus);
		setResult(RESULT_OK, it);
		super.finish();
		super.finish();
		
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		switch(checkedId){
			case R.id.modify_checkbox_lamp:{
				deviceType = 0;
				break;
			}
			case R.id.modify_checkbox_fan:{
				deviceType = 1;
				break;
			}
			case R.id.modify_checkbox_garage:{
				deviceType = 2;
				break;
			}
			case R.id.modify_checkbox_camera:{
				deviceType = 3;
				break;
			}
		}
		Log.w("DEVICE TYPE",deviceType +"");
	}


}
