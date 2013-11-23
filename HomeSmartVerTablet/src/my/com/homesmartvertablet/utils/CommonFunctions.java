package my.com.homesmartvertablet.utils;

import android.telephony.SmsManager;
import android.util.Log;

public class CommonFunctions {
	public static void sendMess(String phoneNumber, String smsBody) {
		try {
			SmsManager smsManager = SmsManager.getDefault();
			smsManager.sendTextMessage(phoneNumber, null, smsBody, null, null);
			Log.w("PHONE", "SEND TO " + phoneNumber);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
