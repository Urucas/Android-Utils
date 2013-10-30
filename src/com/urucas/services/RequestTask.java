package com.urucas.raddio.services;

/**
* @copyright Urucas
* @license   Copyright (C) 2013. All rights reserved
* @version   Release: 1.0.0
* @link       http://urucas.com
* @developers Bruno Alassia, Pamela Prosperi
*/

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.urucas.utils.Utils;

import android.os.AsyncTask;
import android.util.Log;

public class RequestTask extends AsyncTask<String, String, String> {

	protected RequestTaskHandler rsh;
	protected JSONRequestTaskHandler jrsh;

	public RequestTask(RequestTaskHandler _rsh) {
		super();
		rsh = _rsh;
	}
	
	public RequestTask(JSONRequestTaskHandler _jrsh) {
		super();
		jrsh = _jrsh;
	}

	@Override
	protected String doInBackground(String... uri) {
		
		HttpClient httpclient = new DefaultHttpClient();
		HttpResponse response;
		String responseString = null;
		try {
			response = httpclient.execute(new HttpGet(uri[0]));
			StatusLine statusLine = response.getStatusLine();
			if(statusLine.getStatusCode() == HttpStatus.SC_OK){
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				response.getEntity().writeTo(out);
				out.close();
				responseString = out.toString();
				
				if(isCancelled()) {
					rsh.onError("Task cancel");
				}
				
			} else{
				//Closes the connection.
				response.getEntity().getContent().close();
				throw new IOException(statusLine.getReasonPhrase());
			}
		} catch (ClientProtocolException e) {
			rsh.onError(e.getMessage());
		} catch (IOException e) {
			rsh.onError(e.getMessage());		
		}
		return responseString;
	}

	@Override
	protected void onPostExecute(String result) {
		Log.i("api result", result);
		super.onPostExecute(result);
		rsh.onSuccess(result);
	}
}
