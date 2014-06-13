 package com.sanotech.dchek.fragments;

import java.util.Calendar;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.sanotech.dchek.DateTimeListener;

@SuppressLint({ "NewApi", "ValidFragment" })
public class SelectDateFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
	Context context;
	DateTimeListener dateTimeListener;
	public SelectDateFragment(Context c, DateTimeListener listener) {
		this.context =c;
		this.dateTimeListener = listener;
	}
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
	final Calendar calendar = Calendar.getInstance();
	int yy = calendar.get(Calendar.YEAR);
	int mm = calendar.get(Calendar.MONTH);
	int dd = calendar.get(Calendar.DAY_OF_MONTH);
	int hr =calendar.get(Calendar.HOUR);
	int mn =calendar.get(Calendar.MINUTE);
	// new TimePickerDialog(MainActivity.this, this, hr	, mn, false)
	
	//new DatePickerDialog(MainActivity.this, this, yy, mm, dd)
	return new DatePickerDialog(this.context, this, yy, mm, dd);
	}
	 
	public void onDateSet(DatePicker view, int yy, int mm, int dd) {
	//Toast.makeText(this.context, dd+"/"+mm+"/"+yy, Toast.LENGTH_SHORT).show();
	this.dateTimeListener.setDate(dd+"/"+(mm+1)+"/"+yy);
	}



	@Override
	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		// TODO Auto-generated method stub
		//Toast.makeText(this.context, hourOfDay+"/"+minute, Toast.LENGTH_SHORT).show();
		this.dateTimeListener.setTime(hourOfDay+"/"+minute);
	}
	}
