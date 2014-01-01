package my.com.homesmartvertablet.activity;

import java.sql.SQLException;

import com.example.homesmartvertablet.activity.R;

import my.com.homesmartvertablet.controller.DeviceItemController;
import my.com.homesmartvertablet.model.Login;
import my.com.homesmartvertablet.utils.PreferenceHelper;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class FirstScreenActivity extends Activity  implements AnimationListener{
	private TextView textLogo;
	private Animation animations;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		// create a new user
		Login newUser = new Login();
		newUser.username = getString(R.string.label_admin);
		newUser.password = getString(R.string.label_admin);
		
		try {
			DeviceItemController.getInstance(FirstScreenActivity.this).createLoginUser(newUser);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		
		PreferenceHelper.getInstance(FirstScreenActivity.this);
		if(PreferenceHelper.getInteger("CONFIG_DONE", 0) == 0){
			setContentView(R.layout.activity_first_screen);
			
			textLogo = (TextView)findViewById(R.id.text_logo);
			//load the animations
			animations = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.animation_fade_in);
			//set animation listener
			animations.setAnimationListener(this);
			new AsyncTask<Void, Void, Void>() {
	
				@Override
				protected Void doInBackground(Void... arg0) {
					// TODO Auto-generated method stub
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return null;
				}
	
				@Override
				protected void onPostExecute(Void result) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(FirstScreenActivity.this,LoginActivity.class);
					startActivity(intent);
					FirstScreenActivity.this.finish();
				}
	
				@Override
				protected void onPreExecute() {
					// TODO Auto-generated method stub
					textLogo.setAnimation(animations);
				}
				
			}.execute(new Void[]{});
		}else{
			Intent intent = new Intent(FirstScreenActivity.this,MainActivity.class);
			startActivity(intent);
			FirstScreenActivity.this.finish();
		}
	}
	@Override
	public void onAnimationEnd(Animation arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onAnimationRepeat(Animation arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onAnimationStart(Animation arg0) {
		// TODO Auto-generated method stub
		
	}

}
