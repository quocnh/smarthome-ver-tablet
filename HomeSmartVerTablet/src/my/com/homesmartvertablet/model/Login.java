package my.com.homesmartvertablet.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "login")
public class Login {
	
	@DatabaseField(generatedId = true)
	public int login_id;
	@DatabaseField
	public String username;
	@DatabaseField
	public String password;

}
