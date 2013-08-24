package my.com.homesmartvertablet.controller;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import my.com.homesmartvertablet.databases.DatabaseHelper;
import my.com.homesmartvertablet.model.DeviceItem;
import android.content.Context;

public class DeviceItemController {
	
	private static DeviceItemController deviceItemController;
	private DatabaseHelper databaseHelper;
	private Dao<DeviceItem, Integer> getDeviceItemDao;
	private Context mContext;
	
	public DeviceItemController(Context context) throws SQLException {
		// TODO Auto-generated constructor stub
		this.databaseHelper = new DatabaseHelper(context);
		this.getDeviceItemDao = getDeviceItemDao();
		this.mContext = context;
	}
	public static DeviceItemController getInstance(Context context) throws SQLException{
		if(deviceItemController == null){
			deviceItemController = new DeviceItemController(context);
		}
		return deviceItemController;
	}
	private Dao<DeviceItem, Integer> getDeviceItemDao() throws SQLException {
		// TODO Auto-generated method stub
		return this.databaseHelper.getDeviceItemDao();
	}
	
	// get all item of deivice item
	public List<DeviceItem> getAllDeviceItem() throws SQLException{
		return getDeviceItemDao.queryForAll();
	}
	// get list device item of room other by RoomID
	public List<DeviceItem> getListDeviceOfRoomByRoomID(int id) throws SQLException{
		QueryBuilder<DeviceItem, Integer> qb = getDeviceItemDao.queryBuilder();
		qb.where().eq("roomID",id);
		return qb.query();
	}
	// update new device item by deviceID
	public void updateDeviceItemByID(DeviceItem item, int ID) throws SQLException{
		item.setDeviceID(ID);
		getDeviceItemDao.update(item);
	}
	// create a new device item
	public void createDeviceItem(DeviceItem item) throws SQLException{
		getDeviceItemDao.create(item);
	}
	// delete device item by id
	public void deleteDeviceItemByID(int id) throws SQLException{
		getDeviceItemDao.deleteById(id);
	}
}
