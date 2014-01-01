package my.com.homesmartvertablet.activity;

import java.sql.SQLException;
import java.util.List;

import my.com.homesmartvertablet.controller.DeviceItemController;
import my.com.homesmartvertablet.model.Login;

import com.example.homesmartvertablet.activity.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
	private EditText edtsUerName;
	private EditText edtPassWord;
	private Button btnLogin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		edtsUerName = (EditText)findViewById(R.id.edtUsername);
		edtPassWord = (EditText)findViewById(R.id.edtPassword);
		
		btnLogin = (Button)findViewById(R.id.buttonLogin);
		btnLogin.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String user = edtsUerName.getText().toString().trim();
				String pass = edtPassWord.getText().toString().trim();
				
				// get object have same username with input
				Login login = new Login();
				try {
					List<Login> listLogin = DeviceItemController.getInstance(LoginActivity.this).getLoginUserWithUserName(user);
					if (!listLogin.isEmpty()) {
						login = listLogin.get(0);
						if (login.password.equalsIgnoreCase(pass)) {
							startActivity(new Intent(LoginActivity.this,PhoneNumberConfigActivity.class));
						} else {
							Toast.makeText(LoginActivity.this, "Login failed!", Toast.LENGTH_SHORT).show();
						}
					}else {
						Toast.makeText(LoginActivity.this, "Login failed!", Toast.LENGTH_SHORT).show();
						return ;
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}
