package com.urucas.raddio.services;

/**
* @copyright Urucas
* @license   Copyright (C) 2013. All rights reserved
* @version   Release: 1.0.0
* @link       http://urucas.com
* @developers Bruno Alassia, Pamela Prosperi
*/

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JSONRequestTask extends RequestTask{

	public JSONRequestTask(JSONRequestTaskHandler _jrsh) {
		super(_jrsh);		
	}

	@Override
	protected void onPostExecute(String result) {
		try {
		//	Log.i("json result", result);
			JSONObject response = new JSONObject(result);
			jrsh.onSuccess(response);
			
		} catch (JSONException e) {			
			jrsh.onError(e.getMessage());
		}
	}

}
