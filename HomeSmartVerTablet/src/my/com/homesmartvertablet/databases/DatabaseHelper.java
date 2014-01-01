package my.com.homesmartvertablet.databases;

import java.sql.SQLException;

import my.com.homesmartvertablet.model.DeviceItem;
import my.com.homesmartvertablet.model.Login;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper{
	private static final String DATABASE_NAME = "smart_home.db";
	private static final int DATABASE_VERSION = 1;
	private Dao<DeviceItem, Integer> deviceItemDao;
	private Dao<Login, Integer> loginDao;
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
		// TODO Auto-generated method stub
		try {
			TableUtils.createTable(connectionSource, DeviceItem.class);
			TableUtils.createTable(connectionSource, Login.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion,
			int newVersion) {
		// TODO Auto-generated method stub
		try {
			TableUtils.dropTable(connectionSource, DeviceItem.class, true);
			TableUtils.dropTable(connectionSource, Login.class, true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * get device item dao
	 * @return
	 * @throws SQLException
	 */
	public Dao<DeviceItem, Integer> getDeviceItemDao() throws SQLException{
		if(deviceItemDao == null){
			deviceItemDao = getDao(DeviceItem.class);
		}
		return this.deviceItemDao;
	}
	
	/**
	 * get login dao
	 * @return
	 * @throws SQLException
	 */
	public Dao<Login, Integer> getLoginDao() throws SQLException{
		if(loginDao == null){
			loginDao = getDao(Login.class);
		}
		return this.loginDao;
	}

}
