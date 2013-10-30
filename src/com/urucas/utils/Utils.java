package com.urucas.utils;

/**
* @copyright Urucas
* @license   Copyright (C) 2013. All rights reserved
* @version   Release: 1.0.0
* @link       http://urucas.com
* @developers Bruno Alassia, Pamela Prosperi
*/

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.telephony.TelephonyManager;
import android.util.Patterns;
import android.util.TypedValue;
import android.widget.Toast;

public abstract class Utils {

	public static int intIDFromResource(Context context, String name, String type) {
		return context.getResources().getIdentifier(name, type, context.getPackageName());		
	}
		
	public static String stringFromResource(Context context, String name) {
		int id = context.getResources().getIdentifier(name, "string", context.getPackageName());
		return context.getResources().getString(id);
	}
	
	public static boolean isConnected(Context context) {
		ConnectivityManager conMgr = (ConnectivityManager) context.getSystemService (Context.CONNECTIVITY_SERVICE);
        if (conMgr.getActiveNetworkInfo() != null
                && conMgr.getActiveNetworkInfo().isAvailable()
                && conMgr.getActiveNetworkInfo().isConnected()) return true;
        
        return false;
	}
	
	public static String capitalize(String s) {
	    if (s!=null && s.length() > 0) {
	        return s.substring(0, 1).toUpperCase() + s.substring(1);
	    }
	    else return s;
	}
	
	public static String capitalize(String s, boolean allWords) {
		String[] words = s.split("\\s+");
		for(int i=0; i< words.length;i++) {
			words[i] = Utils.capitalize(words[i]);			
		}
		return Utils.join(words, " ");
	}
	
	public static String join(String[] s, String glue) {
		String res = "";
		for(int j=0; j<s.length;j++) {
			res += s[j];
			res += glue;
		}	
		return res;
	}
	
	public static boolean ValidPhoneNumber(String pn) {
		return pn.matches("\\d{8,}");
	}
	
	public static void Toast(Context context, String text) {	
		Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
    	toast.show();
	}
	
	public static void Toast(Context context, int resourceID) {
		Toast toast = Toast.makeText(context, resourceID, Toast.LENGTH_LONG);
    	toast.show();
	}
	
	public static void Toast(Context context, String text, int length) {
		Toast toast = Toast.makeText(context, text, length);
    	toast.show();
	}
	
	public static void Toast(Context context, int resourceID, int length) {
		Toast toast = Toast.makeText(context, resourceID, length);
    	toast.show();
	}
	
	public static void showOKAlert(Context context, String message) {
		
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		
		builder.setMessage(message)
               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    	dialog.cancel();                 
               }
         });         
		 AlertDialog alert = builder.create();
		 alert.show();		 
	}
	
	public static void showOKAlert(Context context, int messageResourceID) {
		
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		
		builder.setMessage(messageResourceID)
               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    	dialog.cancel();                 
               }
         });         
		 AlertDialog alert = builder.create();
		 alert.show();		 
	}
	
	
	public static Dialog showPreloader(Context context, String title, String message) {
		Dialog dialog = ProgressDialog.show(context, title, message, true);
    	dialog.setCancelable(true); 
    	return dialog;
	}
	
	
	public static void Log(String tag, String message) {
		android.util.Log.i(tag, message);
	}
	
	public static void Log(int number) {
		android.util.Log.i("URUCAS_DEBUGGING >>>", String.valueOf(number));
	}
	
	public static void Log(String message) {
		android.util.Log.i("URUCAS_DEBUGGING >>>", message);
	}
	
	public static void Log(float f) {
		android.util.Log.i("URUCAS_DEBUGGING >>>", String.valueOf(f));
	}
	
	public static String getRegionCodeFromSIM(Context context) {
		TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		return telephonyManager.getSimCountryIso();
	}
	
	public static String getUUID(Context context) {
		TelephonyManager tManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		return tManager.getDeviceId();
	}
	
	public static String getEmailAccount(Context context) {
		try {
		Pattern emailPattern = Patterns.EMAIL_ADDRESS; // API level 8+
		Account[] accounts = AccountManager.get(context).getAccounts();
		for (Account account : accounts) {
		    if (emailPattern.matcher(account.name).matches()) {
		        String possibleEmail = account.name;		        					
				return possibleEmail;			   
		    }
		}
		}catch(Exception e) {}
		return null;
	}
	
	public static String getNickFromEmail(String email) {
		if(email == null || email.length() == 0) return "";
		
		String[] nick = email.split("@");
		return nick.length == 0 ? null : nick[0];
		
	}
	
	public static float dp2pixel(Context context, int dp) {
		Resources r = context.getResources();
    	return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());    	
	}
	
	public static float distanceBetween2Points(float vectorX0, float vectorY0, float vectorXP, float vectorYP) {
		float x = Math.abs(vectorXP - vectorX0);
		float y = Math.abs(vectorYP - vectorY0);
		return (float) Math.sqrt((x*x) + (y*y));
	}
	
	public static Date string2Date(String sd,String formato){
		//formato = "yyyy-MM-dd'T'HH:mm:ss'Z'";
		//"Thu Jul 11 12:40:18 GMT-03:00 2013"
		//"EE MMM dd HH:mm:ss z YYYY"
		SimpleDateFormat format = new SimpleDateFormat(formato); 
		try {
		    Date date = (Date) format.parse(sd);
		    return date;
		} catch (ParseException e) {
		    // TODO Auto-generated catch block  
		    e.printStackTrace();
		}
		return null;
		
	}

}
