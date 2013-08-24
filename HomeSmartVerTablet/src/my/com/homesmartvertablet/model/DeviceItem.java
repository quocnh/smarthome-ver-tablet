package my.com.homesmartvertablet.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "device_item")
public class DeviceItem {
	
	@DatabaseField(generatedId = true)
	private int deviceID;
	@DatabaseField(canBeNull = false)
	private int roomID;
	@DatabaseField(canBeNull = false)
	private String deviceName;
	@DatabaseField(canBeNull = false)
	private int deviceType;
	@DatabaseField(canBeNull = false)
	private int devicePort;
	@DatabaseField(canBeNull = false)
	private int status;
	public DeviceItem(){
		
	}
	public DeviceItem( int roomID, String deviceName, int deviceType, int devicePort, int status) {
		// TODO Auto-generated constructor stub
		this.roomID = roomID;
		this.deviceName = deviceName;
		this.deviceType = deviceType;
		this.devicePort = devicePort;
		this.status = status;
	}
	
	public void setDeviceID(int deviceID){
		this.deviceID = deviceID;
	}
	public int getDeviceID(){
		return this.deviceID;
	}
	public void setRoomID(int roomID){
		this.roomID = roomID;
	}
	public int getRoomID(){
		return this.roomID;
	}
	public void setDeviceName(String deviceName){
		this.deviceName = deviceName;
	}
	public String getDeviceName(){
		return this.deviceName;
	}
	
	public void setDeviceType(int deviceType){
		this.deviceType = deviceType;
	}
	public int getDeviceType(){
		return this.deviceType;
	}
	
	public void setDevicePort(int devicePort){
		this.devicePort = devicePort;
	}
	public int getDevicePort(){
		return this.devicePort;
	}
	public void setStatus(int status){
		this.status = status;
	}
	public int getStatus(){
		return this.status;
	}
}
