package com.sanotech.dchek;

import android.database.sqlite.SQLiteDatabase;

public class AppConstant {
	public static  SQLiteDatabase mDataBase; 
	public static final String TAB_USERS = "Users";
	// declaration of Users table field 
	public static final String USERS_USERID = "UserId";
	public static final String USERS_FIRST_NAME = "FirstName"; 
	public static final String USERS_LAST_NAME = "LastName";
	public static final String USERS_SEX = "Sex";
	public static final String USERS_DOB = "DateOfBirth";
	public static final String USERS_HEIGHT = "Height";
	public static final String USERS_WEIGHT = "Weight";
	public static final String USERS_USER_NAME = "UserName";
	public static final String USERS_EMAIL_ID = "EmailId";
	public static final String USERS_PHONE_NO = "PhoneNumber";
	public static final String USERS_MOBILE_TYPE = "MobileType";
	public static final String USERS_ALIMENT_ID = "AlimentId";
	
}
