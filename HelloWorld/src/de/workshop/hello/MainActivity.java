package de.workshop.hello;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
               
        final Button button = (Button) findViewById(R.id.myButton);  
      
	      button.setOnClickListener(new View.OnClickListener() {
	          public void onClick(View v) {
	       	    if("hi hi".equals(button.getText())) {
	          	  button.setText("click me");
	          	} else {
	          	  button.setText("hi hi");
	          	}
	          }
	      }); 
	      
        final Button set24h = (Button) findViewById(R.id.buttonSet24h);
        set24h.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	  TimePicker timePicker = (TimePicker) findViewById(R.id.myTimePicker);
            	  if(!timePicker.is24HourView()) {
            		  timePicker.setIs24HourView(true);
            		  set24h.setText("switch to AM/PM");
            	  } else {
            		  timePicker.setIs24HourView(false);
            		  set24h.setText("switch to 24h");
            	  }
            }
        });	      
	      
    }
}        
        
        
        
        
        
        
        
        
        
        
        
        

        
//        final Button set24h = (Button) findViewById(R.id.buttonSet24h);
//        set24h.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//            	  TimePicker timePicker = (TimePicker) findViewById(R.id.myTimePicker);
//            	  if(!timePicker.is24HourView()) {
//            		  timePicker.setIs24HourView(true);
//            		  set24h.setText("switch to AM/PM");
//            	  } else {
//            		  timePicker.setIs24HourView(false);
//            		  set24h.setText("switch to 24h");
//            	  }
//            }
//        });
