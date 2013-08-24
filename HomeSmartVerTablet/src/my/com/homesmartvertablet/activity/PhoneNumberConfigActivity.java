package my.com.homesmartvertablet.activity;

import com.example.homesmartvertablet.activity.R;

import my.com.homesmartvertablet.utils.PreferenceHelper;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class PhoneNumberConfigActivity extends Activity implements OnClickListener{
	private EditText edtPhoneNumberConfig;
	private Button btnFinish;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_phone_number_config);
		
		edtPhoneNumberConfig = (EditText)findViewById(R.id.edt_phone_number_config);
		edtPhoneNumberConfig.addTextChangedListener(textChangeListener);
		btnFinish = (Button)findViewById(R.id.btn_finish);
		btnFinish.setOnClickListener(this);
	}
	TextWatcher textChangeListener = new TextWatcher() {
		
		@Override
		public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {
			// TODO Auto-generated method stub
		}
		
		@Override
		public void afterTextChanged(Editable arg0) {
			// TODO Auto-generated method stub
			if(edtPhoneNumberConfig.getText().toString().trim().equals(""))
				btnFinish.setEnabled(false);
			else
				btnFinish.setEnabled(true);
		}
	};
	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch (view.getId()) {
		case R.id.btn_finish:{
			// save config
			PreferenceHelper.getInstance(PhoneNumberConfigActivity.this);
			PreferenceHelper.putString("PHONE_NUMBER_DEVICE", edtPhoneNumberConfig.getText().toString().trim());
			PreferenceHelper.putInteger("CONFIG_DONE", 1);
			Intent intent = new Intent(PhoneNumberConfigActivity.this,MainActivity.class);
			startActivity(intent);
			PhoneNumberConfigActivity.this.finish();
			break;
		}
		default:
			break;
		}
	}

}
