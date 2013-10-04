package com.sebastian.TestApplication;

import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class TextToSpeechActivity extends Activity implements TextToSpeech.OnInitListener 
{
	private Button speakButton;
	private EditText textinput;
	private TextToSpeech texttospeech;
	private Spinner languagespinner;
	
	private static final String TAG = "TTS";
	
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.texttospeech_activity);
		initialiseUI();
		languagespinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());
		
		texttospeech = new TextToSpeech(this, this);
	}	

	public void onInit(int status) 
	{
		if (status == TextToSpeech.SUCCESS) {
			 
           int result = texttospeech.setLanguage(Locale.US);
 
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e(TAG, "This Language is not supported");
            } 
            else {
            	speakButton.setEnabled(true);
                speakText();
            }
 
        } else {
            Log.e(TAG, "Initilization Failed!");
        }
	}
	
	public void buttonClicked (View view)
	{
		if(view == speakButton) {
			speakText();
		}
	}
	
	private void speakText() 
	{
		String texttospeak = ""+textinput.getText();
		
		if(!texttospeak.isEmpty()) {
			texttospeech.speak(texttospeak, TextToSpeech.QUEUE_FLUSH, null);
		}		
	}
	
	private void initialiseUI() 
	{
		speakButton = (Button)findViewById(R.id.speak);
		textinput = (EditText)findViewById(R.id.textinput);
		languagespinner = (Spinner)findViewById(R.id.languagespinner);
	}
}

class CustomOnItemSelectedListener implements OnItemSelectedListener
{

	public void onItemSelected(AdapterView<?> parent, View view, int position,long id)
	{
		Toast.makeText(parent.getContext(), "Selected Country : " + 
	    parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();	
	}

	public void onNothingSelected(AdapterView<?> arg0) {}	
}
