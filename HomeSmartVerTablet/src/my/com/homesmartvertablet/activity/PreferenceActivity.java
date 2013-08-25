package my.com.homesmartvertablet.activity;

import com.example.homesmartvertablet.activity.R;

import my.com.homesmartvertablet.utils.PreferenceHelper;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.widget.EditText;

public class PreferenceActivity extends android.preference.PreferenceActivity{
	private EditTextPreference edtPhone;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.settings);
		
		PreferenceHelper.getInstance(this);
		
		edtPhone = (EditTextPreference)super.findPreference("phone_num_key");
		edtPhone.setText(PreferenceHelper.getString("PHONE_NUMBER_DEVICE", ""));
		
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		PreferenceHelper.getInstance(this);
		PreferenceHelper.putString("PHONE_NUMBER_DEVICE", edtPhone.getText().toString().trim());
		MainActivity.phoneNumberDefault = edtPhone.getText().toString().trim();
	}

}
