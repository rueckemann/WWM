package de.workshop.fff;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
     
		/** 
		 * Button to open the map view 
		*/
	    final Button mapButton = (Button)this.findViewById(R.id.mapButton);
	    mapButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(MainActivity.this, GoogleMapActivity.class);
				startActivity(intent);
			}
	    });
    }
}