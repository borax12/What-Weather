package com.borax.whatweather;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class WeatherActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weather);
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new WeatherFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.weather, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.change_city) {
			showInputDialog();
		}
		return super.onOptionsItemSelected(item);
	}

	private void showInputDialog() {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder= new AlertDialog.Builder(this);
		builder.setTitle("Change City");
		final EditText input= new EditText(this);
		input.setInputType(InputType.TYPE_CLASS_TEXT);
		builder.setView(input);
		
		builder.setPositiveButton("Go", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				changeCity(input.getText().toString());
			}
		});
		
		builder.show();
	}

	public void changeCity(String city) {
		WeatherFragment fragment= (WeatherFragment)getSupportFragmentManager().findFragmentById(R.id.container);
		fragment.changeCity(city);
		new CityPreference(this).setCity(city);
	}
}
