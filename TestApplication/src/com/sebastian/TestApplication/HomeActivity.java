package com.sebastian.TestApplication;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends Activity 
{
	Intent intent = null;
	Button texttospeech;

	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_activity);
		initialiseButtons();
	}	

	public void buttonClicked (View view)
	{
		if (view == texttospeech) {
			intent = new Intent(this, TextToSpeechActivity.class);
		}
		
		startActivity(intent);
	}
	
	private void initialiseButtons() 
	{
		texttospeech = (Button)findViewById(R.id.texttospeech);		
	}
	
}
