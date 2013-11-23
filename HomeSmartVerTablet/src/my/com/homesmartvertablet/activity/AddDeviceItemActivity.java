package my.com.homesmartvertablet.activity;

import java.sql.SQLException;

import my.com.homesmartvertablet.controller.DeviceItemController;
import my.com.homesmartvertablet.model.DeviceItem;

import com.example.homesmartvertablet.activity.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

public class AddDeviceItemActivity extends Activity implements OnCheckedChangeListener,OnClickListener{
	private int typeDevice = 0, portDevice, statusDevice = 0, roomID;
	private String nameDevice;
	private EditText edtNameDevice, edtPortDevice;
	private RadioGroup radioGroup;
	private ImageButton toggleSwitch;
	private Button btnAdd, btnCancel;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_device_item);
		
		btnAdd = (Button)findViewById(R.id.add_device_actvity_btn_add);
		btnCancel = (Button)findViewById(R.id.add_device_actvity_btn_cancel);
		edtNameDevice = (EditText)findViewById(R.id.add_device_actvity_edt_name_device);
		edtPortDevice = (EditText)findViewById(R.id.add_device_actvity_edt_port_device);
		radioGroup = (RadioGroup)findViewById(R.id.add_device_actvity_radio_group);
		toggleSwitch = (ImageButton)findViewById(R.id.add_device_actvity_toggle_status);
		
		toggleSwitch.setOnClickListener(this);
		radioGroup.setOnCheckedChangeListener(this);
		btnAdd.setOnClickListener(this);
		btnCancel.setOnClickListener(this);
		
	}
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		switch(checkedId){
			case R.id.add_device_actvity_checkbox_lamp:{
				typeDevice = 0;
				break;
			}
			case R.id.add_device_actvity_checkbox_fan:{
				typeDevice = 1;
				break;
			}
			case R.id.add_device_actvity_checkbox_garage:{
				typeDevice = 2;
				break;
			}
			case R.id.add_device_actvity_checkbox_camera:{
				typeDevice = 3;
				break;
			}
			case R.id.add_device_actvity_checkbox_tv:{
				typeDevice = 4;
				break;
			}
		
		}
	}
	
	@Override
	public void finish() {
		// TODO Auto-generated method stub
		Intent it = new Intent();
		it.putExtra("ROOM_ID", roomID);
		it.putExtra("NAME_DEVICE", nameDevice);
		it.putExtra("TYPE_DEVICE", typeDevice);
		it.putExtra("PORT_DEVICE", portDevice);
		it.putExtra("STATUS_DEVICE", statusDevice);
		setResult(RESULT_OK, it);
		super.finish();
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
			case R.id.add_device_actvity_btn_add:{
				try {
					addDeviceItem();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.finish();
				break;
			}
			case R.id.add_device_actvity_btn_cancel:{
				this.finish();
				break;
			}
			case R.id.add_device_actvity_toggle_status:{
				statusDevice = statusDevice ^ 1;
				if(statusDevice == 0){
					toggleSwitch.setImageResource(R.drawable.switch_off);
				}else{
					toggleSwitch.setImageResource(R.drawable.switch_on);
				}
				break;
			}
		}
	}
	private void addDeviceItem() throws SQLException {
		// TODO Auto-generated method stub
		try{
			nameDevice = edtNameDevice.getText().toString().trim();
			portDevice = Integer.parseInt(edtPortDevice.getText().toString().trim());
			roomID = getIntent().getExtras().getInt("room_id");
			
			DeviceItem item = new DeviceItem(roomID, nameDevice, typeDevice, portDevice, statusDevice);
			DeviceItemController.getInstance(AddDeviceItemActivity.this).createDeviceItem(item);
		}catch(Exception ex){
			ex.printStackTrace();
			Toast.makeText(AddDeviceItemActivity.this, "Add device item error. Please verify your device infomations!", Toast.LENGTH_SHORT).show();
		}
	}
	

}
