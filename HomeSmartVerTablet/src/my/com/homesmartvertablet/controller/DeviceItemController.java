package my.com.homesmartvertablet.controller;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import my.com.homesmartvertablet.databases.DatabaseHelper;
import my.com.homesmartvertablet.model.DeviceItem;
import my.com.homesmartvertablet.model.Login;
import android.content.Context;

public class DeviceItemController {

	private static DeviceItemController deviceItemController;
	private DatabaseHelper databaseHelper;
	private Dao<DeviceItem, Integer> getDeviceItemDao;
	private Dao<Login, Integer> getLoginDao;
	private Context mContext;

	public DeviceItemController(Context context) throws SQLException {
		this.databaseHelper = new DatabaseHelper(context);
		this.getDeviceItemDao = getDeviceItemDao();
		this.getLoginDao = getLoginDao();
		this.mContext = context;
	}

	public static DeviceItemController getInstance(Context context)
			throws SQLException {
		if (deviceItemController == null) {
			deviceItemController = new DeviceItemController(context);
		}
		return deviceItemController;
	}

	/**
	 * get dao of device item
	 * 
	 * @return
	 * @throws SQLException
	 */
	private Dao<DeviceItem, Integer> getDeviceItemDao() throws SQLException {
		return this.databaseHelper.getDeviceItemDao();
	}

	/**
	 * get dao of login
	 * 
	 * @return
	 * @throws SQLException
	 */
	private Dao<Login, Integer> getLoginDao() throws SQLException {
		return this.databaseHelper.getLoginDao();
	}

	/**
	 * get all item of deivice item
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<DeviceItem> getAllDeviceItem() throws SQLException {
		return getDeviceItemDao.queryForAll();
	}

	/**
	 * get list device item of room other by RoomID
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public List<DeviceItem> getListDeviceOfRoomByRoomID(int id)
			throws SQLException {
		QueryBuilder<DeviceItem, Integer> qb = getDeviceItemDao.queryBuilder();
		qb.where().eq("roomID", id);
		return qb.query();
	}

	/**
	 * update new device item by deviceID
	 * 
	 * @param item
	 * @param ID
	 * @throws SQLException
	 */
	public void updateDeviceItemByID(DeviceItem item, int ID)
			throws SQLException {
		item.setDeviceID(ID);
		getDeviceItemDao.update(item);
	}

	/**
	 * create a new device item
	 * 
	 * @param item
	 * @throws SQLException
	 */
	public void createDeviceItem(DeviceItem item) throws SQLException {
		getDeviceItemDao.create(item);
	}

	/**
	 * delete device item by id
	 * 
	 * @param id
	 * @throws SQLException
	 */
	public void deleteDeviceItemByID(int id) throws SQLException {
		getDeviceItemDao.deleteById(id);
	}

	/**
	 * create a new user
	 * 
	 * @param item
	 * @throws SQLException
	 */
	public void createLoginUser(Login user) throws SQLException {
		getLoginDao.createOrUpdate(user);
	}

	/**
	 * get login object with username
	 */
	public List<Login> getLoginUserWithUserName(String username) {
		List<Login> accountList = null;
		try {
			accountList = getLoginDao.query(getLoginDao.queryBuilder().where()
					.eq("username", username).prepare());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return accountList;
	}

}
