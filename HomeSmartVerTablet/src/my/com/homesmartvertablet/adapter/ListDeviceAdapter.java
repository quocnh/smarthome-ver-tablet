package my.com.homesmartvertablet.adapter;

import java.sql.SQLException;
import java.util.List;

import com.example.homesmartvertablet.activity.R;

import my.com.homesmartvertablet.activity.MainActivity;
import my.com.homesmartvertablet.adapter.ListRoomAdapter.ViewHolder;
import my.com.homesmartvertablet.controller.DeviceItemController;
import my.com.homesmartvertablet.model.DeviceItem;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ListDeviceAdapter extends ArrayAdapter<DeviceItem>{
	
	private List<DeviceItem> listDevice;
	private Context context;
	private int resourceId;
	private int checkStatus = 0;
	private int deviceType = 0;
	private AnimationDrawable frameAnimation;
	public ListDeviceAdapter(Context context, int textViewResourceId, List<DeviceItem> list) {
		super(context, textViewResourceId, list);
		// TODO Auto-generated constructor stub
		this.listDevice = list;
		this.context = context;
		this.resourceId = textViewResourceId;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		//View v = convertView;
		final ViewHolder holder;
		
		if(convertView == null){
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(resourceId, null);

			holder = new ViewHolder();
			holder.deviceType = (ImageView) convertView.findViewById(R.id.image_device_type);
			holder.port = (TextView) convertView.findViewById(R.id.text_port);
			holder.deviceName = (TextView)convertView.findViewById(R.id.text_device_name);
			holder.btnSwitch = (ImageButton) convertView.findViewById(R.id.btn_switch);
			convertView.setTag(holder);
			
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		final DeviceItem item = getItem(position);
		// type: 0 - lamp, 1 -fan, 2-window gara, 3-canera
		checkStatus = item.getStatus(); 
		//toggle = checkStatus;
		deviceType = item.getDeviceType();
		switch(deviceType){
			case 0:{
				if(checkStatus == 0)
					holder.deviceType.setImageResource(R.drawable.lamp_off);
				else
					holder.deviceType.setImageResource(R.drawable.lamp_on);
				break;
			}
			case 1:{
				if(checkStatus == 0)
					holder.deviceType.setImageResource(R.drawable.fan_off);
				else{
					holder.deviceType.setBackgroundResource(R.drawable.fan_on_spin_animation);
					frameAnimation = (AnimationDrawable) holder.deviceType.getBackground();
					frameAnimation.start();
				}
				break;
			}
			case 2:{
				if(checkStatus == 0)
					holder.deviceType.setImageResource(R.drawable.gara_car_off);
				else
					holder.deviceType.setImageResource(R.drawable.gara_car_on);
				break;
			}
			case 3:{
				if(checkStatus == 0)
					holder.deviceType.setImageResource(R.drawable.camera_off);
				else
					holder.deviceType.setImageResource(R.drawable.camera_on);
				break;
			}
		}
		
		if(checkStatus == 0)
			holder.btnSwitch.setImageResource(R.drawable.switch_off);
		else
			holder.btnSwitch.setImageResource(R.drawable.switch_on);
		
		holder.port.setText("Port "  + item.getDevicePort());
		
		holder.deviceName.setText(item.getDeviceName());
		
		holder.btnSwitch.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				deviceType = item.getDeviceType();
				checkStatus = item.getStatus();
				checkStatus = checkStatus ^ 1; // status when press button switch
				switch(checkStatus){
					case 0:{
						holder.btnSwitch.setImageResource(R.drawable.switch_off);
						switch(deviceType){
							case 0 :{//lamp
								holder.deviceType.setImageResource(R.drawable.lamp_off);
								break;
							}
							case 1:{//fan
								holder.deviceType.setBackgroundResource(R.drawable.fan_off);
								break;
							}
							case 2:{//window
								holder.deviceType.setImageResource(R.drawable.gara_car_off);
								break;
							}
							case 3:{//camera
								holder.deviceType.setImageResource(R.drawable.camera_off);
								break;
							}
						}
						// save status of item
						break;
					}
					case 1:{
						holder.btnSwitch.setImageResource(R.drawable.switch_on);
						switch(deviceType){
							case 0 :{//lamp
								holder.deviceType.setImageResource(R.drawable.lamp_on);
								//send mess
								break;
							}
							case 1:{//fan
								holder.deviceType.setBackgroundResource(R.drawable.fan_on_spin_animation);
								frameAnimation = (AnimationDrawable) holder.deviceType.getBackground();
								frameAnimation.start();
								//send mess
								break;
							}
							case 2:{//window
								holder.deviceType.setImageResource(R.drawable.gara_car_on);
								//send mess
								break;
							}
							case 3:{//camera
								holder.deviceType.setImageResource(R.drawable.camera_on);
								//send mess
								break;
							}
							
						}
						break;
					}
				}
				//save status of device into database
				item.setStatus(checkStatus); // update new status for item
				try {
					DeviceItemController.getInstance(getContext()).updateDeviceItemByID(item, item.getDeviceID());
					Log.w("UPDATE_STATUS","DONE");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		convertView.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View arg0) {
				// TODO Auto-generated method stub
				//Toast.makeText(getContext(), item.getDeviceName(), Toast.LENGTH_SHORT).show();
				return false;
			}
		});
		
		return convertView;
	}

	public class ViewHolder{
		private ImageView deviceType;
		private TextView port;
		private TextView deviceName;
		private ImageButton btnSwitch;
	}

}
